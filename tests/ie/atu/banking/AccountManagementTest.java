package ie.atu.banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AccountManagementTest {
    // Make a static account that can be accessed by all tests
    // This is static as no test instance has been created
    private static Account account;

    // Update the account variable with an account that will be used for all the BankingApp tests
    @BeforeAll
    public static void createAccountClassForTests() throws Exception {
        System.out.println("Running account management tests...");
        account = new Account("Example Name", 10000);
    }

    @Test
    void testAnotherTest() {

        // ACTUAL TESTS DOWN HERE
        String expected = "Hello World2";
        String actual = "Hello World2";
        assertEquals(expected, actual);
    }
}