package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features/SessionExpiry.feature",
    glue = "stepDefinitions",                 
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags = "@expire"
)
@Test
public class SessionExpiryRunner extends AbstractTestNGCucumberTests {
}