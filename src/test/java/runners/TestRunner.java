package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "stepDefinitions",                 
    plugin = {"pretty", "html:target/cucumber-reports.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = true
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}
