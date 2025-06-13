# ATM Simulation

ATM Simulation is a console application implemented in Java. It allows users to authorize, create accounts, deposit and withdraw funds, transfer money between accounts, and view transaction history. The program provides basic functionality to manage bank accounts through a text-based menu.

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

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
