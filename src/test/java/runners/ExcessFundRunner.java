package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/features/ExcessFund.feature", 
    glue = "stepDefinitions",                
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags = "@excess"
)
@Test
public class ExcessFundRunner extends AbstractTestNGCucumberTests {
}

