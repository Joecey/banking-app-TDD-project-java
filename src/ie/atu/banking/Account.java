package ie.atu.banking;

import ie.atu.banking.exceptions.InsufficientFundsException;
import ie.atu.banking.exceptions.LoanOverpaymentException;

// Represents a single bank account with account holder name, balance, and loan amount
public class Account {
    private String accountHolder; // Name of the account holder
    private double balance;       // Current account balance
    private double loan;          // Outstanding loan amount

    // Constructor to create a new account
    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.loan = 0;
    }

    // Getter for the account holder's name
    public String getAccountHolder() {
        return accountHolder;
    }

    // Getter for the account balance
    public double getBalance() {
        return balance;
    }

    // Getter for the loan amount
    public double getLoan() {
        return loan;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money from the account (only if balance is sufficient)
    // On success, return the new balance of the account
    public double withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance)
            throw new InsufficientFundsException("Cannot perform withdrawal due to insufficient funds");
        balance -= amount;
        return balance;
    }

    // Method to approve a loan for the account
    public void approveLoan(double amount) {
        loan += amount;
    }

    // Method to repay a part of the loan (only if amount <= loan)
    public double repayLoan(double amount) {
        if (amount > loan)
            throw new LoanOverpaymentException("Current amount will overpay loan"); // Repayment exceeds loan
        loan -= amount;

        // return remaining loan
        return loan;
    }
}
