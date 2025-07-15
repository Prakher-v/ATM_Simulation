import java.util.*;

public class ATM_Simulation {

    private double balance;
    private String pin;
    private final ArrayList<String> transactionHistory;

    public ATM_Simulation(String pin) {
        this.balance = 0.0;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    private boolean verifyPin(Scanner scanner) {
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();
        return this.pin.equals(enteredPin);
    }

    public void inquireBalance() {
        System.out.printf("Current Balance: $%.2f%n", balance);
    }

    public void withdrawCash(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a valid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("Withdrawal Successful.");
        }
    }

    public void depositCash(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a valid amount.");
        } else {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposit Successful.");
        }
    }

    public void changePin(String oldPin, String newPin) {
        if (this.pin.equals(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            System.out.println("Transaction History:");
            for (String record : transactionHistory) {
                System.out.println("  " + record);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===================================");
        System.out.println("Welcome to the ATM Simulator");
        System.out.println("===================================");
        System.out.print("Set your 4-digit PIN: ");
        String pin = scanner.nextLine();

        ATM_Simulation atm = new ATM_Simulation(pin);

        while (true) {
            System.out.println("\n---------- ATM MENU ----------");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    if (atm.verifyPin(scanner)) {
                        atm.inquireBalance();
                    } else {
                        System.out.println("Incorrect PIN.");
                    }
                    break;
                case 2:
                    if (atm.verifyPin(scanner)) {
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        atm.withdrawCash(withdrawAmount);
                    } else {
                        System.out.println("Incorrect PIN.");
                    }
                    break;
                case 3:
                    if (atm.verifyPin(scanner)) {
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        atm.depositCash(depositAmount);
                    } else {
                        System.out.println("Incorrect PIN.");
                    }
                    break;
                case 4:
                    System.out.print("Enter current PIN: ");
                    String oldPin = scanner.nextLine();
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.nextLine();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    if (atm.verifyPin(scanner)) {
                        atm.displayTransactionHistory();
                    } else {
                        System.out.println("Incorrect PIN.");
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
