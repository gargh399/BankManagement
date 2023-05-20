import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    String name;
    long accountNumber;
    float balance;
}

public class Main {
    static List<Account> accounts = new ArrayList<>();

    public static void loadAccounts() {
        try {
            Scanner inFile = new Scanner(new File("accounts.txt"));

            while (inFile.hasNext()) {
                Account account = new Account();
                account.name = inFile.next();
                account.accountNumber = inFile.nextLong();
                account.balance = inFile.nextFloat();
                accounts.add(account);
            }

            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: accounts.txt");
        }
    }

    public static void saveAccounts() {
        try {
            PrintWriter outFile = new PrintWriter(new FileWriter("accounts.txt"));

            for (Account account : accounts) {
                outFile.println(account.name + " " + account.accountNumber + " " + account.balance);
            }

            outFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving accounts.");
        }
    }

    public static void createAccount() {
        Scanner input = new Scanner(System.in);
        Account account = new Account();

        System.out.println();
        System.out.print("Enter account holder's name: ");
        account.name = input.nextLine();

        System.out.print("Enter account number: ");
        account.accountNumber = input.nextLong();

        for (Account acc : accounts) {
            if (acc.accountNumber == account.accountNumber) {
                System.out.println("Sorry, this account already exists!");
                return;
            }
        }

        System.out.print("Enter initial balance: ");
        account.balance = input.nextFloat();

        accounts.add(account);
        saveAccounts();

        System.out.println("Account created successfully!\n");
    }

    public static void deposit() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();
        System.out.print("Enter amount to deposit: ");
        float amount = input.nextFloat();

        boolean found = false;
        for (Account account : accounts) {
            if (account.accountNumber == accNumber) {
                account.balance += amount;
                found = true;
                break;
            }
        }

        if (found) {
            saveAccounts();
            System.out.println("Amount deposited successfully!");
        } else {
            System.out.println("Account not found!");
        }
        System.out.println();
    }

    public static void withdraw() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();
        System.out.print("Enter amount to withdraw: ");
        float amount = input.nextFloat();

        boolean found = false;
        for (Account account : accounts) {
            if (account.accountNumber == accNumber) {
                if (account.balance >= amount) {
                    account.balance -= amount;
                    found = true;
                    break;
                } else {
                    System.out.println("Insufficient balance!");
                }
            }
        }

        if (found) {
            saveAccounts();
            System.out.println("Amount withdrawn successfully!");
        } else {
            System.out.println("Account not found!");
        }
        System.out.println();
    }

    public static void displayBalance() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();
        input.nextLine(); // Clear the newline character from the input buffer

        boolean found = false;
        for (Account account : accounts) {
            if (account.accountNumber == accNumber) {
                System.out.println("Account Holder Name: " + account.name);
                System.out.println("Account Number: " + account.accountNumber);
                System.out.println("Balance: " + account.balance);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account not found!");
        }

        System.out.println();
    }

    public static void closeAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();

        boolean found = false;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accountNumber == accNumber) {
                accounts.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            saveAccounts();
            System.out.println("Account closed successfully!");
        } else {
            System.out.println("Account not found!");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        loadAccounts();

        int choice;

        Scanner input = new Scanner(System.in);
        do {
            clearConsole();
            System.out.println("Bank Account Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Close Account");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    displayBalance();
                    break;
                case 5:
                    closeAccount();
                    break;
                case 0:
                    System.out.println("Thank you for using the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println("\nPress Enter to continue...");
            input.nextLine(); // Clear the newline character from the input buffer
            input.nextLine();
        } while (choice != 0);
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error occurred while clearing console.");
        }
    }
}
