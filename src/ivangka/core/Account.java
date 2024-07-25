package ivangka.core;

public class Account {

    private String name;
    final private User holder;
    final private Bank bank;
    final private long id;
    private double balance;

    public Account(String name, User holder, Bank bank) {

        //inits
        this.name = name;
        this.holder = holder;
        this.bank = bank;
        this.balance = 0;
        this.id = bank.generateNewAccountID();

        holder.addAccount(this);
        bank.addAccount(this);

    }

    public void increaseBalance(double amount) {
        this.balance += amount;
        this.holder.increaseTotalBalance(amount);
    }

    public void reduceBalance(double amount) {
        this.balance -= amount;
        this.holder.reduceTotalBalance(amount);
    }

    public long getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

}
