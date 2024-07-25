package ivangka.core;

import ivangka.exceptions.LoginAlreadyExistsException;

import java.util.Locale;
import java.util.Scanner;

public class ATM {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        sc.useLocale(Locale.US);

        Bank bank = new Bank("Digital Native Bank");
        User user = null;

        try {
            user = new User("John", "Smith", bank, "john-smith", "0000");
        } catch (LoginAlreadyExistsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        new Account("Saving account", user, bank);

        User authorizedUser;
        while (true) {
            authorizedUser = authorization(bank, sc);
            userMenu(bank, authorizedUser, sc);
        }

    }

    public static User authorization(Bank bank, Scanner sc) {

        String login;
        String pin;
        User authorizedUser;

        do {

            System.out.print("\nEnter your login: ");
            login = sc.nextLine();
            System.out.print("Enter your pin: ");
            pin = sc.nextLine();

            // try authorization
            authorizedUser = bank.authorization(login, pin);

            // if failed to log in
            if (authorizedUser == null) {
                System.out.println("\nInvalid login or pin. Try again.");
            }

        } while (authorizedUser == null);

        // when managed to log in
        return authorizedUser;
    }

    public static void userMenu(Bank bank, User user, Scanner sc) {

        System.out.printf("\n\n\nWelcome to %s, %s.", bank.getName(), user.getFirstName());

        // show all accounts of the user
        user.showAccountsInfo();

        int choice;
        while (true) {

            System.out.println("\nWhat would you like to do?");

            System.out.println("  1) Deposit");
            System.out.println("  2) Withdraw");
            System.out.println("  3) Transfer");
            System.out.println("  4) Show transaction history");
            System.out.println("  5) Quit");

            System.out.printf("\nEnter number of the operation (1-5): ");
            choice = sc.nextInt();
            System.out.println();

            switch (choice) {

                case 1:
                    deposit(user);
                    break;
                case 2:
                    withdraw(user);
                    break;
                case 3:
                    transfer(user);
                    break;
                case 4:
                    user.showTransactionHistory();
                    break;
                case 5:
                    sc.nextLine();
                    System.out.println("You have successfully logged out of your account.\n\n");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.\n");

            }

            user.showAccountsInfo();

        }

    }

    public static void deposit(User user) {

        // select the account to deposit to
        int inAccountNumber;
        do {
            System.out.printf("Select the account number (1-%d): ", user.getAccounts().size());
            inAccountNumber = sc.nextInt();
            if (inAccountNumber < 0 || inAccountNumber > user.getAccounts().size()) {
                System.out.println("\nInvalid choice. Try again.\n");
            }
        } while (inAccountNumber < 0 || inAccountNumber > user.getAccounts().size());

        Account inAccount = user.getAccounts().get(inAccountNumber - 1);

        // select amount
        double amount;
        do {
            System.out.print("Enter the deposit amount: $");
            amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("\nInvalid choice. Try again.\n");
            }
        } while (amount <= 0);

        inAccount.increaseBalance(amount);
        user.addTransaction(new Transaction(amount, "Deposit to \"" + inAccount.getName() + "\""));

        System.out.println("\nDeposit completed successfully.");

    }

    public static void withdraw(User user) {

        // if total balance of user equals 0
        if (user.getTotalBalance() == 0) {
            System.out.println("You have no funds in any account. Make a deposit.");
            return;
        }

        // select the account to withdraw from
        int fromAccountNumber;
        do {
            System.out.printf("Select the account number (1-%d): ", user.getAccounts().size());
            fromAccountNumber = sc.nextInt();
            if (fromAccountNumber < 0 || fromAccountNumber > user.getAccounts().size()) {
                System.out.println("\nInvalid choice. Try again.\n");
            } else if (user.getAccounts().get(fromAccountNumber - 1).getBalance() == 0) {
                System.out.println("There are no funds in the account. Please choose another one.\n");
            }
        } while (fromAccountNumber < 0 || fromAccountNumber > user.getAccounts().size() ||
                user.getAccounts().get(fromAccountNumber - 1).getBalance() == 0);

        Account fromAccount = user.getAccounts().get(fromAccountNumber - 1);

        // select amount
        double amount;
        do {
            System.out.printf("Enter the withdraw amount (max. $%.02f): $", fromAccount.getBalance());
            amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("\nInvalid choice. Try again.\n");
            } else if (amount > fromAccount.getBalance()) {
                System.out.println("\nInsufficient funds. Try again.\n");
            }
        } while (amount <= 0 || amount > fromAccount.getBalance());

        fromAccount.reduceBalance(amount);
        user.addTransaction(new Transaction(amount, "Withdraw from \"" + fromAccount.getName() + "\""));

        System.out.println("\nWithdraw completed successfully.");

    }

    public static void transfer(User user) {

        // if total balance of user equals 0
        if (user.getTotalBalance() == 0) {
            System.out.println("You have no funds in any account. Make a deposit.");
            return;
        }

        if (user.getAccounts().size() < 2) {
            System.out.println("Transfer is not possible.");
            System.out.println("You must have at least two accounts to complete a transaction.");
            return;
        }

        // select the account to transfer from
        int fromAccountNumber;
        do {
            System.out.printf("Select the account number (1-%d) to transfer from: ", user.getAccounts().size());
            fromAccountNumber = sc.nextInt();
            if (fromAccountNumber < 0 || fromAccountNumber > user.getAccounts().size()) {
                System.out.println("\nInvalid choice. Try again.\n");
            } else if (user.getAccounts().get(fromAccountNumber - 1).getBalance() == 0) {
                System.out.println("There are no funds in the account. Please choose another one.\n");
            }
        } while (fromAccountNumber < 0 || fromAccountNumber > user.getAccounts().size() ||
                user.getAccounts().get(fromAccountNumber - 1).getBalance() == 0);

        Account fromAccount = user.getAccounts().get(fromAccountNumber - 1);

        // select the account to transfer to
        int inAccountNumber;
        do {
            System.out.printf("Select the account number (1-%d) to transfer to: ", user.getAccounts().size());
            inAccountNumber = sc.nextInt();
            if (inAccountNumber < 0 || inAccountNumber > user.getAccounts().size()) {
                System.out.println("\nInvalid choice. Try again.\n");
            }
        } while (inAccountNumber < 0 || inAccountNumber > user.getAccounts().size());

        Account inAccount = user.getAccounts().get(inAccountNumber - 1);

        // select amount
        double amount;
        do {
            System.out.printf("Enter the transfer amount (max. $%.02f): $", fromAccount.getBalance());
            amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("\nInvalid choice. Try again.\n");
            } else if (amount > fromAccount.getBalance()) {
                System.out.println("\nInsufficient funds. Try again.\n");
            }
        } while (amount <= 0 || amount > fromAccount.getBalance());

        fromAccount.reduceBalance(amount);
        inAccount.increaseBalance(amount);
        user.addTransaction(new Transaction(amount, "Transfer from \"" + fromAccount.getName()
                + "\" to \"" + inAccount.getName() + "\""));

        System.out.println("\nTransfer completed successfully.");

    }

}
