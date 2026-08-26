import java.util.Scanner;
class BankAccount {
    private int accountNumber;
    private double balance;
    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        if (initialBalance >= 0.0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }
    public boolean withdraw(double amount) {
        if (amount > 0.0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }
    public void run() {
        int choice = 0;
        System.out.println("=== WELCOME TO DECODELABS ATM SYSTEM ===");
        do {
            showMenu();
            choice = getValidatedIntInput();
            switch (choice) {
                case 1:
                    checkBalanceFlow();
                    break;
                case 2:
                    depositFlow();
                    break;
                case 3:
                    withdrawFlow();
                    break;
                case 4:
                    System.out.println("\nThank you for banking with DecodeLabs. Card Ejected. Goodbye!");
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid option. Please select between 1 and 4.");
            }
            System.out.println();
        } while (choice != 4);
    }
    private void showMenu() {
        System.out.println("\n--- ATM Main Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    private int getValidatedIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("[ERROR] Invalid input. Please enter a valid number: ");
            scanner.next(); // Clear invalid token from the buffer
        }
        return scanner.nextInt();
    }
    private double getValidatedDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("[ERROR] Invalid format. Please enter a numerical amount: ");
            scanner.next(); // Clear invalid token
        }
        return scanner.nextDouble();
    }
    private void checkBalanceFlow() {
        System.out.println("\n--- Balance Inquiry ---");
        double currentBalance = account.getBalance();
        System.out.printf("Current Account Balance: $%.2f\n", currentBalance);
    }
    private void depositFlow() {
        System.out.println("\n--- Cash Deposit ---");
        System.out.print("Enter amount to deposit: $");
        double amount = getValidatedDoubleInput();
        if (account.deposit(amount)) {
            System.out.printf("Transaction Successful! Deposited: $%.2f\n", amount);
            System.out.printf("Updated Balance: $%.2f\n", account.getBalance());
        } else {
            System.out.println("[REJECTED] Deposit amount must be greater than zero.");
        }
    }
    private void withdrawFlow() {
        System.out.println("\n--- Cash Withdrawal ---");
        System.out.print("Enter amount to withdraw: $");
        double amount = getValidatedDoubleInput();
        if (amount <= 0) {
            System.out.println("[REJECTED] Withdrawal amount must be greater than zero.");
            return;
        }
        if (account.withdraw(amount)) {
            System.out.printf("Transaction Successful! Please take your cash: $%.2f\n", amount);
            System.out.printf("Remaining Balance: $%.2f\n", account.getBalance());
        } else {
            System.out.println("[REJECTED] Insufficient funds or invalid amount.");
        }
    }
}
public class DecodeLabs_Java_P3 {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(1001592, 500.00);
        ATM decodedAtm = new ATM(myAccount);
        decodedAtm.run();
    }
}
