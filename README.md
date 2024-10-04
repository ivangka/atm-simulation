# ATM Simulation

An ATM simulation project designed to emulate the core functionalities of a real-world automated teller machine (ATM). This project includes essential features such as account management, user authentication, and transaction handling. Users can perform various actions, including depositing and withdrawing money, checking account balances, and viewing transaction histories.

## Features

- **Account Management:** Create and manage bank accounts.
- **User Authentication:** Secure login system with unique user identification.
- **Multi-account Support:** Manage multiple accounts for a single user, allowing flexible control over different financial assets.
- **Transaction Handling:** Track and manage transactions.
- **Deposit and Withdrawal:** Perform deposit and withdrawal operations.
- **Balance Inquiry:** Check account balances.
- **Transaction History:** View the history of all transactions.

## Project Structure

- **`ATM` class:** Manages user input and ATM functions.
- **`Bank` class:** Handles user accounts and login processes.
- **`User` class:** Stores user information and manages accounts.
- **`Account` class:** Keeps track of account details and balance.
- **`Transaction` class:** Records transaction details.
- **`LoginAlreadyExistsException` class:** Handles errors for duplicate user logins.

## Installation

To use this project, you need to have Java Development Kit (JDK) installed. You can compile and run the program using the following commands:

```bash
# Compile the program
javac -d bin src/ivangka/core/*.java src/ivangka/exceptions/*.java

# Run the program
java -cp bin ivangka.core.ATM
```
