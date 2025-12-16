package ie.atu.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Here, we select all the test classes we want to run
@Suite
@SelectClasses({
        TestExample.class,
        TestExample2.class
})

// This blank class is just needed for the testSuite to start running
public class BankingAppTestSuite {

}