import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount for deposit. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance. You can withdraw up to " + balance);
        } else {
            System.out.println("Invalid amount for withdrawal. Please enter a positive value.");
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(double initialBalance) {
        bankAccount = new BankAccount(initialBalance);
    }

    public void displayOptions() {
        System.out.println("\nATM Options:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);
        double amount;

        switch (option) {
            case 1:
                System.out.println("Your current balance is: " + bankAccount.getBalance());
                break;
            case 2:
                System.out.print("Enter the amount to deposit: ");
                amount = scanner.nextDouble();
                bankAccount.deposit(amount);
                break;
            case 3:
                System.out.print("Enter the amount to withdraw: ");
                amount = scanner.nextDouble();
                bankAccount.withdraw(amount);
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

public class ATMMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your initial balance: ");
        double initialBalance = scanner.nextDouble();

        ATM atm = new ATM(initialBalance);

        while (true) {
            atm.displayOptions();
            System.out.print("Enter your choice (1-4): ");
            int option = scanner.nextInt();
            atm.processOption(option);
        }
    }
}
