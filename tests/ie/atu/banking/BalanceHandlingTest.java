package ie.atu.banking;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testAnotherTest() {
        // ACTUAL TESTS DOWN HERE
        String expected = "Hello World2";
        String actual = "Hello World2";
        assertEquals(expected, actual);
    }
}