package ie.atu.banking;

import ie.atu.banking.exceptions.AccountDoesNotExist;
import ie.atu.banking.exceptions.IncorrectFundParameters;
import ie.atu.banking.exceptions.InsufficientFundsException;
import ie.atu.banking.exceptions.LoanOverpaymentException;

import java.util.ArrayList;
import java.util.List;

/**
 * This program simulates a simple banking application. It allows:
 * - Adding new accounts with an initial deposit.
 * - Depositing and withdrawing money from accounts.
 * - Approving and repaying loans for account holders.
 * - Tracking the total deposits available in the bank.
 * <p>
 * The program uses a list of Account objects to manage account data.
 */
public class BankingApp {
    // List to store all accounts in the banking application
    private List<Account> accounts;
    private double totalDeposits; // Tracks total deposits in the bank

    // Constructor to initialize the banking application
    // If there are initialAccounts, create the bank with these accounts loaded
    public BankingApp(Account[] initialAccounts) {
        System.out.println("Initializing BankingApp with " + initialAccounts.length + " accounts");
        if (initialAccounts.length == 0) {
            accounts = new ArrayList<>();
            totalDeposits = 0;
        } else {
            totalDeposits = 0;
            for (Account acc : initialAccounts) {
                System.out.println("Account: " + acc.getAccountHolder() + " with balance: " + acc.getBalance());
                totalDeposits += acc.getBalance();
            }
            accounts = new ArrayList<>(List.of(initialAccounts));
            System.out.println("Total deposits after initialization: " + totalDeposits);
        }
    }

    public BankingApp() {
        accounts = new ArrayList<>();
        totalDeposits = 0;
    }

    /**
     * Helper method to find an account by account holder's name.
     *
     * @param accountHolder The name of the account holder.
     * @return The Account object if found, otherwise null.
     */
    protected Account findAccount(String accountHolder) {
        for (Account account : accounts) {
            if (account.getAccountHolder().equals(accountHolder)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Returns the number of accounts in the banking application.
     *
     * @return The number of accounts in the banking application.
     */
    protected int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Adds a new account with an initial deposit.
     *
     * @param accountHolder  The name of the new account holder.
     * @param initialDeposit The initial deposit amount.
     */
    public void addAccount(String accountHolder, double initialDeposit) {
        accounts.add(new Account(accountHolder, initialDeposit));
        totalDeposits += initialDeposit;
    }

    /**
     * Deposits money into an account.
     *
     * @param accountHolder The name of the account holder.
     * @param amount        The deposit amount.
     * @return The new account balance after the deposit
     * @throws AccountDoesNotExist     if the account does not exist
     * @throws IncorrectFundParameters if the amount is not positive
     */
    public double depositToAccountHolder(String accountHolder, double amount) {
        Account account = findAccount(accountHolder);
        if (account == null) throw new AccountDoesNotExist("This account does not exist");
        if (amount <= 0) throw new IncorrectFundParameters("Incorrect parameters for deposit");
        account.deposit(amount);
        totalDeposits += amount;
        return account.getBalance();
    }

    /**
     * Withdraws money from an account.
     *
     * @param accountHolder The name of the account holder.
     * @param amount        The withdrawal amount.
     * @return The new account balance after the withdrawal
     * @throws AccountDoesNotExist        if the account does not exist
     * @throws IncorrectFundParameters    if the amount is not positive
     * @throws InsufficientFundsException if the account has insufficient funds
     */
    public double withdrawToAccountHolder(String accountHolder, double amount)
            throws AccountDoesNotExist, InsufficientFundsException, IncorrectFundParameters {
        Account account = findAccount(accountHolder);
        if (account == null) throw new AccountDoesNotExist("This account does not exist");
        if (amount <= 0) throw new IncorrectFundParameters("Incorrect parameters for withdrawal");
        // attempt to withdraw, handling errors as needed
        try {
            account.withdraw(amount);
            totalDeposits -= amount;
            return account.getBalance();
        } catch (InsufficientFundsException e) {
            throw new InsufficientFundsException("Insufficient funds to complete withdrawal");
        }
    }

    /**
     * Approves a loan for an account holder.
     *
     * @param accountHolder The name of the account holder.
     * @param loanAmount    The loan amount.
     * @return The updated Account object after loan approval
     * @throws AccountDoesNotExist     if the account does not exist
     * @throws IncorrectFundParameters if the loan amount exceeds available deposits
     */
    public Account approveLoanForAccountHolder(String accountHolder, double loanAmount) throws AccountDoesNotExist, IncorrectFundParameters {
        Account account = findAccount(accountHolder);
        if (account == null) throw new AccountDoesNotExist("This account does not exist");
        if (loanAmount > totalDeposits) throw new IncorrectFundParameters("Incorrect parameters for loan approval");
        account.approveLoan(loanAmount);
        totalDeposits -= loanAmount;
        return account;
    }

    /**
     * Repays a part of the loan for an account holder.
     *
     * @param accountHolder The name of the account holder.
     * @param amount        The repayment amount.
     * @return The remaining loan amount after repayment
     * @throws AccountDoesNotExist      if the account does not exist
     * @throws IncorrectFundParameters  if the amount is not positive
     * @throws LoanOverpaymentException if the repayment amount exceeds the loan balance
     */
    public double repayLoanForAccountHolder(String accountHolder, double amount) throws AccountDoesNotExist, IncorrectFundParameters, LoanOverpaymentException {
        Account account = findAccount(accountHolder);
        if (account == null) throw new AccountDoesNotExist("This account does not exist");
        if (amount <= 0) throw new IncorrectFundParameters("Incorrect parameters for loan repayment");

        // attempt to repay loan, handle errors as needed
        try {
            account.repayLoan(amount);
            totalDeposits += amount;
            return account.getLoan();
        } catch (LoanOverpaymentException e) {
            throw new LoanOverpaymentException("Overpayment for loan detected");
        }

    }

    /**
     * Gets the total deposits available in the bank.
     *
     * @return The total deposits.
     */
    public double getTotalDeposits() {
        return totalDeposits;
    }

    /**
     * Gets the balance of a specific account holder.
     *
     * @param accountHolder The name of the account holder.
     * @return The account balance if the account exists, otherwise null.
     */
    public Double getBalanceOfAccountHolder(String accountHolder) {
        Account account = findAccount(accountHolder);
        return account != null ? account.getBalance() : null;
    }

    /**
     * Gets the loan amount of a specific account holder.
     *
     * @param accountHolder The name of the account holder.
     * @return The current loan amount if the account exists, otherwise null.
     */
    public Double getLoanOfAccountHolder(String accountHolder) {
        Account account = findAccount(accountHolder);
        return account != null ? account.getLoan() : null;
    }
}

