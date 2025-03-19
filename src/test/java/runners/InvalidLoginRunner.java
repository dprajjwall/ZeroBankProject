package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;
@CucumberOptions(
	    features = "src/test/resources/features/invalid.feature",
	    glue = "stepDefinitions",
	    plugin = {"pretty", "html:target/cucumber-reports/InvalidLogin.html"},
	    monochrome = true,
	    tags = "@InvalidLogin" // Run only scenarios with this tag
	)
	@Test
	public class InvalidLoginRunner extends AbstractTestNGCucumberTests {
	}
