package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AccountSummary;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.OnlineBankingLink;

public class AccountSummaryItemsSteps {
	
	WebDriver driver;
    LoginPage loginpage;
    OnlineBankingLink banking;
    AccountSummary summary;
    MainPage mainpage;
    
    @Given("User logs into Zero Bank for Account Summary")
    public void loginwebpage() {
    	WebDriverManager.edgedriver().setup();
    	driver = new EdgeDriver();
    	driver.manage().window().maximize();
    	driver.get("http://zero.webappsecurity.com/login.html");
    	
    	loginpage = new LoginPage(driver);

        loginpage.enterUsername("username");
        loginpage.enterPassword("password");

        loginpage.clickSignIn();

        try {
            System.out.println("Waiting for manual action (Captcha, 2FA, etc.)...");
            Thread.sleep(10000);  // Wait for 10 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("index.html"), "Login failed! Not redirected to homepage.");
    	
    }
    
    @When("User navigates to the Account Summary page")
    public void navigate_AccountSummary() {
        mainpage = new MainPage(driver);
        banking = mainpage.click_page();
        Assert.assertNotNull(banking, "Failed to click on the Online Banking link!");
        
        summary = banking.navigate_accountsummary();
    }
    
    @Then("User should see all expected account types")
    public void verify_account_types() {
    	assertTrue(summary.verify(),"Items are not visible");
    	driver.quit();
    }

}
