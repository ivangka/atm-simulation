# ATM Simulation

An ATM simulation project designed to emulate the core functionalities of a real-world automated teller machine (ATM). This project includes essential features such as account management, secure user authentication, and detailed transaction handling. It supports multiple users and accounts, allowing for a realistic simulation of banking operations. Users can perform various actions, including depositing and withdrawing money, checking account balances, and viewing transaction histories.


## Features

- **Account Management:** Create and manage bank accounts.
- **User Authentication:** Secure login system with unique user identification.
- **Bank Operations:** Manage multiple users and accounts within the bank.
- **Transaction Handling:** Track and manage transactions.
- **Deposit and Withdrawal:** Perform deposit and withdrawal operations.
- **Balance Inquiry:** Check account balances.
- **Transaction History:** View the history of all transactions.

## Project Structure

- **`ATM` class:** Provides the main interface for ATM operations including user authentication and transaction handling.
- **`Bank` class:** Manages the collection of users and accounts, and handles account creation and user registration.
- **`User` class:** Handles user information, authentication, and account association.
- **`Account` class:** Manages account-related operations such as balance management and account information.
- **`Transaction` class:** Represents a financial transaction, including the amount and type of transaction.
- **`LoginAlreadyExistsException` class:** Custom exception to handle duplicate login attempts.

## Installation

> [!NOTE]
> To use this project, you need to have Java Development Kit (JDK) installed. You can compile and run the program using the following commands:

```bash
# Compile the program
javac -d bin src/ivangka/core/*.java src/ivangka/exceptions/*.java

# Run the program
java -cp bin ivangka.core.ATM
```
