package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features/TransferFund.feature", // Path to feature files
    glue = "stepDefinitions",                 // Package for step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags = "@Fund"
)
@Test
public class TransferFundRunner extends AbstractTestNGCucumberTests {
}

