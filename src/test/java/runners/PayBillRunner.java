package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@CucumberOptions(
	    features = "src/test/resources/features/PayBill.feature",
	    glue = "stepDefinitions",                
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    monochrome = true,
	    tags = "@Bill"
	)

@Test
public class PayBillRunner extends AbstractTestNGCucumberTests {
}