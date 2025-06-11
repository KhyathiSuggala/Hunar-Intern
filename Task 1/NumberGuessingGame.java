// Step 1: Game Initialization
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        
        // Step 1: Game Initialization
        Random random = new Random();                          // Create Random object
        int targetNumber = random.nextInt(100) + 1;            // Random number between 1 to 100

        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.println("-------------------------------------------");
        System.out.println("🎯 Rules of the Game:");
        System.out.println("1️⃣ Guess a number between 1 and 100.");
        System.out.println("2️⃣ You'll get hints if your guess is too high or too low.");
        System.out.println("3️⃣ Keep trying until you guess the correct number.");
        System.out.println("-------------------------------------------");

        // Step 2: User Input
        Scanner scanner = new Scanner(System.in);
        int userGuess;
        int attempts = 0;

        // Step 5: Looping Structure
        while (true) {
            System.out.print("\n👉 Enter your guess (1 to 100): ");

            // Handle invalid input
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            userGuess = scanner.nextInt();
            attempts++;

            // Step 3: Guess Validation
            if (userGuess < 1 || userGuess > 100) {
                System.out.println("❗ Please enter a number within the range (1 to 100).");
                continue;
            }

            // Step 4: Game Logic
            if (userGuess == targetNumber) {
                // Step 6: Winning Condition
                System.out.println("\n🎉 Congratulations! You guessed the correct number: " + targetNumber);
                System.out.println("✅ Total attempts: " + attempts);
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("📉 Too low! Try again.");
            } else {
                System.out.println("📈 Too high! Try again.");
            }
        }

        scanner.close();
        System.out.println("\nThanks for playing the game! 🥳");
    }
}
