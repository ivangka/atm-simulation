package ivangka.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Bank {

    final private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name) {

        // inits
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();

    }

    public String generateNewUserUUID(User user) {

        // generate unique UUID
        String uuid;
        do {
            uuid = UUID.randomUUID().toString().replace("-", "");
        } while (this.users.contains(user));

        return uuid;
    }

    public long generateNewAccountID() {

        Random rand = new Random(47);
        boolean unique;
        long id;

        // generate unique ID
        do {
            id = rand.nextLong((long) Math.pow(10, 10));
            unique = true;
            for (Account a : accounts) {
                if (a.getID() == id) {
                    unique = false;
                    break;
                }
            }
        } while (!unique);

        return id;
    }

    public User authorization(String login, String pin) {

        // search for a user with the login
        User user = null;
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                user = u;
                break;
            }
        }
        if (user == null) return null;

        // set MD5 hash for the entered pin
        byte[] pinHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // pin check
        if (MessageDigest.isEqual(user.getPinHash(), pinHash)) {
            return user;
        }

        return null;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

}
