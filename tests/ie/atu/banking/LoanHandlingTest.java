package ie.atu.banking;

import org.junit.jupiter.api.*;
import ie.atu.banking.exceptions.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanHandlingTest {
    // Make a static BankingApp that can be accessed by all tests
    // This is static as no test instance has been created
    public static BankingApp bankingApp;

    // Creating banking app with initial accounts
    @BeforeAll
    public static void createAccountClassForTests() throws Exception {
        System.out.println("Running account management tests...");
        Account account1 = new Account("Example1", 10000);
        Account account2 = new Account("Example2", 20000);

        Account[] initialAccounts = new Account[]{account1, account2};
        bankingApp = new BankingApp(initialAccounts);
    }

    @Test
    @Order(1)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void addLoanToAnAccount() {

    }
    @Test
    @Order(2)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void repayLoanForAccount() {

    }

//     After all the tests, check to see if the account size and total bank deposit size is as expected
    @AfterAll
    public static void afterAllTests() {
        assertEquals(2, bankingApp.getNumberOfAccounts());
        assertEquals(33000, bankingApp.getTotalDeposits());

    }
}