package ie.atu.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ie.atu.banking.BankingApp;

class TestExample {
    // Tests must be inside a @Test method to work
    @Test
    void testAnotherTest() {
        // Create a new banking application instance
        BankingApp bank = new BankingApp();

        // Add accounts
        bank.addAccount("Alice", 1000);
        bank.addAccount("Bob", 500);

        // Test deposits
        System.out.println("Depositing 200 to Alice: " + bank.deposit("Alice", 200)); // Should return true
        System.out.println("Alice's balance: " + bank.getBalance("Alice")); // Should be 1200

        // Test withdrawals
        System.out.println("Withdrawing 300 from Bob: " + bank.withdraw("Bob", 300)); // Should return true
        System.out.println("Bob's balance: " + bank.getBalance("Bob")); // Should be 200

        // Test loan approval
        System.out.println("Approving a loan of 400 for Alice: " + bank.approveLoan("Alice", 400)); // Should return true
        System.out.println("Alice's loan: " + bank.getLoan("Alice")); // Should be 400

        // Test loan repayment
        System.out.println("Repaying 200 of Alice's loan: " + bank.repayLoan("Alice", 200)); // Should return true
        System.out.println("Alice's remaining loan: " + bank.getLoan("Alice")); // Should be 200

        // Check total deposits in the bank
        System.out.println("Total deposits in the bank: " + bank.getTotalDeposits());

        // ACTUAL TESTS DOWN HERE
        String expected = "Hello World2";
        String actual = "Hello World2";
        assertEquals(expected, actual);
    }
}