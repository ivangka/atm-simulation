package ivangka.core;

import ivangka.exceptions.LoginAlreadyExistsException;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String firstName;
    private String lastName;
    final private Bank bank;
    final private String uuid;
    private String login;
    private byte[] pinHash;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private double totalBalance;

    public User(String firstName, String lastName, Bank bank, String login, String pin) {

        // checking if an account with the same login exists in the bank
        try {
            bank.checkLoginExists(login);
        } catch (LoginAlreadyExistsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // inits
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank = bank;
        this.login = login;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.totalBalance = 0;

        // generate hash
        this.pinHash = bank.generateHash(pin, "MD5");

        // generate UUID for the user
        this.uuid = bank.generateNewUserUUID(this);

        bank.addUser(this);

        new Account("Master account", this, bank);

    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void showAccountsInfo() {

        System.out.println("\n\nYour accounts:");
        for (Account a : accounts) {
            System.out.printf("  %d) %d : %s : $%.02f\n", accounts.indexOf(a) + 1,
                    a.getID(), a.getName(), a.getBalance());
        }

    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void showTransactionHistory() {

        if (transactions.isEmpty()) {
            System.out.println("You have not made any transactions.");
            return;
        }

        System.out.println("Transaction history:");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }

    }

    public void increaseTotalBalance(double amount) {
        this.totalBalance += amount;
    }

    public void reduceTotalBalance(double amount) {
        this.totalBalance += amount;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return Objects.equals(uuid, user.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLogin() {
        return this.login;
    }

    public byte[] getPinHash() {
        return this.pinHash;
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    public double getTotalBalance() {
        return this.totalBalance;
    }

}

