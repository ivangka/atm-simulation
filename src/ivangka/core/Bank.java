package ivangka.core;

import ivangka.exceptions.LoginAlreadyExistsException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Bank {

    final private String name;
    private String algorithm;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name, String algorithm) {

        // inits
        this.name = name;
        this.algorithm = algorithm;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();

    }

    public User createNewUser(String firstName, String lastName, String login, String pin) {
        return new User(firstName, lastName, this, login, pin);
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

    public void checkLoginExists(String login) throws LoginAlreadyExistsException {

        // throw the exception if a user with the login already exists bank
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                throw new LoginAlreadyExistsException(String.format("A user with the login \"%s\" already exists. " +
                        "Please choose another one.", login));
            }
        }

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

        // generate hash for the entered pin
        byte[] pinHash = generateHash(pin, "MD5");

        // pin check
        if (MessageDigest.isEqual(user.getPinHash(), pinHash)) {
            return user;
        }

        return null;
    }

    public byte[] generateHash(String message, String algorithm) {

        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            hash = md.digest(message.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return hash;
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
