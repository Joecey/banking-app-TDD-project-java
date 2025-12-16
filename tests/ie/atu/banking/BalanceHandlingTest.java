package ie.atu.banking;

import ie.atu.banking.exceptions.AccountDoesNotExist;
import ie.atu.banking.exceptions.IncorrectFundParameters;
import ie.atu.banking.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// This is required so that the tests are tested one after the other and not in parallel
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BalanceHandlingTest {
    // Make a static BankingApp that can be accessed by all tests
    // This is static as no test instance has been created
    public static BankingApp bankingApp;

    // Creating banking app with initial accounts
    @BeforeAll
    public static void createAccountClassForTests() throws Exception {
        Account account1 = new Account("Example1", 10000);
        Account account2 = new Account("Example2", 20000);

        Account[] initialAccounts = new Account[]{account1, account2};
        bankingApp = new BankingApp(initialAccounts);
    }

    // Testing scenarios for depositing money to an account
    @Test
    @Order(1)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void depositMoneyToAnAccount() throws AccountDoesNotExist, IncorrectFundParameters {
        assertEquals(11000, bankingApp.depositToAccountHolder("Example1", 1000));
        assertEquals(31000, bankingApp.getTotalDeposits());
        assertEquals(25000, bankingApp.depositToAccountHolder("Example2", 5000));
        assertEquals(36000, bankingApp.getTotalDeposits());
        assertThrows(AccountDoesNotExist.class, () -> {
            bankingApp.depositToAccountHolder("This account does not exist", 1000);
        });
        assertThrows(IncorrectFundParameters.class, () -> {
            bankingApp.depositToAccountHolder("Example1", -1000);
        });

    }

    // Testing scenarios for withdrawing money from an account
    @ParameterizedTest
    @Order(2)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @CsvSource({
            "Example1, 10000, 1000",
            "Example2, 20000, 5000"
    })
    public void withdrawMoneyFromAnAccount(String accountHolder, double withdrawAmount, double expectedBalance)
            throws AccountDoesNotExist, IncorrectFundParameters, InsufficientFundsException {
        assertEquals(expectedBalance, bankingApp.withdrawToAccountHolder(accountHolder, withdrawAmount));
    }

    // Testing exception scenarios for withdrawing money from an account
    @Test
    @Order(3)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void withdrawMoneyFromAnAccountException() {
        // Test withdrawing from non-existent account
        assertThrows(AccountDoesNotExist.class, () -> {
            bankingApp.withdrawToAccountHolder("Non-existent Account", 1000);
        }, "Should throw AccountDoesNotExist for non-existent account");

        // Test withdrawing zero or negative amount
        assertThrows(IncorrectFundParameters.class, () -> {
            bankingApp.withdrawToAccountHolder("Example1", 0);
        }, "Should throw IncorrectFundParameters for zero amount");

        assertThrows(IncorrectFundParameters.class, () -> {
            bankingApp.withdrawToAccountHolder("Example1", -100);
        }, "Should throw IncorrectFundParameters for negative amount");

        // Test withdrawing more than account balance
        assertThrows(InsufficientFundsException.class, () -> {
            bankingApp.withdrawToAccountHolder("Example1", 10000000);
        }, "Should throw InsufficientFundsException when withdrawing more than account balance");
    }

    @AfterAll
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public static void afterAllTests() {
        assertEquals(2, bankingApp.getNumberOfAccounts());
        assertEquals(6000, bankingApp.getTotalDeposits());

    }
}