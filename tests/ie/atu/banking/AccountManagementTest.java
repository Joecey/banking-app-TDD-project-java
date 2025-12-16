package ie.atu.banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AccountManagementTest {
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
    void findingAccountAndGettingDetails() {
        Account foundAccount;
        foundAccount = bankingApp.findAccount("Example1");
        assertEquals("Example1", foundAccount.getAccountHolder());
        assertEquals(10000, foundAccount.getBalance());

        foundAccount = bankingApp.findAccount("Example2");
        assertEquals("Example2", foundAccount.getAccountHolder());
        assertEquals(20000, foundAccount.getBalance());

        assertEquals(2, bankingApp.getNumberOfAccounts());
    }

    @Test
    void addingAccount() {
        bankingApp.addAccount("John Doe", 3000);
        Account foundAccount = bankingApp.findAccount("John Doe");
        assertEquals("John Doe", foundAccount.getAccountHolder());
        assertEquals(3000, foundAccount.getBalance());
    }

    @Test
    void accountThatDoesNotExist() {
        Account foundAccount;
        foundAccount = bankingApp.findAccount("This person does not exist");
        assertNull(foundAccount);
    }

    // After all the tests, check to see if the account size and total bank deposit size is as expected
    @AfterAll
    public static void afterAllTests() {
        assertEquals(3, bankingApp.getNumberOfAccounts());
        assertEquals(33000, bankingApp.getTotalDeposits());

    }
}