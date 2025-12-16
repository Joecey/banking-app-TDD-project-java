package ie.atu.banking;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Here, we select all the test classes we want to run
@Suite
@SelectClasses({
        AccountTest.class,
        AccountManagementTest.class,
        BankInformationTest.class,
        LoanHandlingTest.class,
        BalanceHandlingTest.class,

})

// This blank class is just needed for the testSuite to start running
public class BankingAppTestSuite {

}