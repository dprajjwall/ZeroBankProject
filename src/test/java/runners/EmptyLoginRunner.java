package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features/EmptyLogin.feature",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports/EmptyLogin.html"},
    monochrome = true,
    tags = "@EmptyLogin" // Ensure this tag matches the feature file
)
@Test
public class EmptyLoginRunner extends AbstractTestNGCucumberTests {
}
