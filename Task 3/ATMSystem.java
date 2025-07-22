import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean authenticate(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

public class ATMSystem {

    // Step 2: Account Management using HashMap
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adding some sample accounts
        accounts.put("12345", new Account("12345", "1111", 5000.0));
        accounts.put("67890", new Account("67890", "2222", 10000.0));

        System.out.println("🏧 Welcome to Java ATM Simulator");

        // Step 1: User Authentication
        System.out.print("🔐 Enter your account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("🔐 Enter your PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(accountNumber, pin)) {
            System.out.println("✅ Login successful!\n");
            showMainMenu(accountNumber);
        } else {
            System.out.println("❌ Invalid account number or PIN.");
        }

        System.out.println("👋 Thank you for using Java ATM.");
    }

    private static boolean authenticateUser(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        return account != null && account.authenticate(pin);
    }

    // Step 3: Main Menu
    private static void showMainMenu(String accountNumber) {
        int choice;
        do {
            System.out.println("\n🔘 Main Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Balance Inquiry");
            System.out.println("4. Exit");
            System.out.print("👉 Enter your choice: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // clear invalid input
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleWithdraw(accountNumber);
                    break;
                case 2:
                    handleDeposit(accountNumber);
                    break;
                case 3:
                    handleBalanceInquiry(accountNumber);
                    break;
                case 4:
                    System.out.println("🚪 Exiting... Have a great day!");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please select 1–4.");
            }

        } while (choice != 4);
    }

    // Step 4: Withdrawal
    private static void handleWithdraw(String accountNumber) {
        Account account = accounts.get(accountNumber);
        System.out.print("💵 Enter amount to withdraw: ");

        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("❌ Please enter a positive amount.");
            } else if (account.withdraw(amount)) {
                System.out.println("✅ Withdrawal successful. Current Balance: ₹" + account.getBalance());
            } else {
                System.out.println("❌ Insufficient balance!");
            }
        } else {
            System.out.println("❌ Invalid input! Please enter a valid number.");
            scanner.next(); // clear invalid input
        }
    }

    // Step 5: Deposit
    private static void handleDeposit(String accountNumber) {
        Account account = accounts.get(accountNumber);
        System.out.print("💰 Enter amount to deposit: ");

        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("❌ Please enter a positive amount.");
            } else {
                account.deposit(amount);
                System.out.println("✅ Deposit successful. Current Balance: ₹" + account.getBalance());
            }
        } else {
            System.out.println("❌ Invalid input! Please enter a valid number.");
            scanner.next(); // clear invalid input
        }
    }

    // Step 6: Balance Inquiry
    private static void handleBalanceInquiry(String accountNumber) {
        Account account = accounts.get(accountNumber);
        System.out.println("📄 Your current balance is: ₹" + account.getBalance());
    }
}
