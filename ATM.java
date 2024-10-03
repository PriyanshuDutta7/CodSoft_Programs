import java.util.Scanner;

public class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor to initialize ATM with a bank account
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the ATM interface
    public void start() {
        boolean exit = false;

        System.out.println("Welcome to the ATM!");

        while (!exit) {
            // Display menu options
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            // Get user input
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check balance
                    System.out.printf("Your current balance is: $%.2f%n", account.checkBalance());
                    break;
                case 2:
                    // Deposit money
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    // Withdraw money
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    // Exit the ATM
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    // Invalid input
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount userAccount = new BankAccount(500.00); // Initial balance of $500
        // Create an ATM instance and start the ATM interface
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
