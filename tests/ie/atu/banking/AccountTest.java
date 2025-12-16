package ie.atu.banking;

import ie.atu.banking.exceptions.InsufficientFundsException;
import ie.atu.banking.exceptions.LoanOverpaymentException;
import org.junit.jupiter.api.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    // Create an initial account which wil be updated as needed
    private Account testAccount;

    // Now create a @BeforeEach and @AfterEach which will update in between tests to ensure we have a
    // fresh account everytime
    @BeforeEach
    void setUp() {
        testAccount = new Account("Example Name", 10000);
    }

    @AfterEach
    void cleanUp() {
        testAccount = null;
    }

    // Now we are going to test each of the methods provided by the account class
    // Get account holder name
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void getAccountHolder() {
        String expectedName = "Example Name";
        assertEquals(expectedName, testAccount.getAccountHolder());
        assertEquals(10000, testAccount.getBalance());

    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void accountBalanceAndExceptions() throws InsufficientFundsException {
        double accountBalance = 10000;
        assertEquals(accountBalance, testAccount.getBalance());

        accountBalance += 5000;
        testAccount.deposit(5000);
        assertEquals(accountBalance, testAccount.getBalance());

        accountBalance -= 14000;
        assertEquals(accountBalance, testAccount.withdraw(14000));

        assertThrows(InsufficientFundsException.class, () -> {
            testAccount.withdraw(2000);
        });
    }

    @Test
    void accountLoanAndExceptions() throws LoanOverpaymentException {
        testAccount.approveLoan(12000);
        assertEquals(12000, testAccount.getLoan());
        assertEquals(1000, testAccount.repayLoan(11000));
        assertThrows(LoanOverpaymentException.class, () -> {
            testAccount.repayLoan(2000);
        });

    }


}