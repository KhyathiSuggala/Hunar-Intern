import java.util.Scanner;

public class TicTacToe {

    // Step 1: Game Initialization
    static char[][] board = {
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' }
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎮 Welcome to Tic Tac Toe!");
        System.out.println("----------------------------------");
        System.out.println("🎯 Rules:");
        System.out.println("1. This is a 2-player game: X and O.");
        System.out.println("2. Take turns placing your mark on a 3x3 board.");
        System.out.println("3. First to get 3 in a row (row/column/diagonal) wins!");
        System.out.println("4. Enter your move using row and column (1 to 3).");

        boolean playAgain;
        do {
            resetBoard();
            boolean gameEnded = false;

            // Step 4: Turn-Based System
            while (!gameEnded) {
                printBoard();

                // Step 2: Player Input
                int row, col;
                while (true) {
                    System.out.print("Player " + currentPlayer + ", enter your row (1-3): ");
                    row = scanner.nextInt() - 1;

                    System.out.print("Player " + currentPlayer + ", enter your column (1-3): ");
                    col = scanner.nextInt() - 1;

                    if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                        if (board[row][col] == ' ') {
                            board[row][col] = currentPlayer;
                            break;
                        } else {
                            System.out.println("❗ Cell already taken. Choose another one.");
                        }
                    } else {
                        System.out.println("❌ Invalid input! Row and Column must be between 1 and 3.");
                    }
                }

                // Step 3 & 5: Game Logic + Winning Condition
                if (checkWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("🏆 Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("🤝 It's a tie!");
                    gameEnded = true;
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            // Step 7: Restart Option
            System.out.print("🔁 Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("👋 Thanks for playing Tic Tac Toe!");
        scanner.close();
    }

    // Step 6: Display Board
    public static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check for win
    public static boolean checkWinner(char player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (
                (board[i][0] == player && board[i][1] == player && board[i][2] == player) || 
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            ) {
                return true;
            }
        }

        // Diagonals
        if (
            (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        ) {
            return true;
        }

        return false;
    }

    // Check if board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    // Reset board for new game
    public static void resetBoard() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }
}
