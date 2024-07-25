package ivangka.core;

import java.util.Date;

public class Transaction {

    final private double amount;
    final private String memo;
    final private Date timestamp;

    public Transaction(double amount, String memo) {

        // inits
        this.amount = amount;
        this.memo = memo;
        this.timestamp = new Date();

    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return String.format("%s : %s : $%.02f", this.timestamp.toString(), this.memo, this.amount);
    }

}
