package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features/Statement.feature", // Path to feature files
    glue = "stepDefinitions",                 // Package for step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags = "@download"
)
@Test
public class StatementRunner extends AbstractTestNGCucumberTests {
}