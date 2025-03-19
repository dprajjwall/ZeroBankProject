package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features/Logout.feature", // Path to feature files
    glue = "stepDefinitions",                 // Package for step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = true,
    tags = "@logout"
)
@Test
public class LogoutRunner extends AbstractTestNGCucumberTests {
}

