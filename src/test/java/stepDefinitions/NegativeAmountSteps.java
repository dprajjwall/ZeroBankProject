package stepDefinitions;

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.*;

public class NegativeAmountSteps {
	
	WebDriver driver;
	LoginPage loginpage;
	MainPage mainpage;
	OnlineBankingLink banking;
	AccountSummary summary;
	TransferFund fund;
	Verify verify;
	
	@Given("User has logged into the banking application for negative amount transfer")
	public void user_logged_into_the_banking_application_negative_amount() {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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

	@Given("User navigates to the Fund Transfer page for negative amount transfer")
	public void user_navigate_to_the_fund_transfer_page_negative_amount() {
	     mainpage = new MainPage(driver);
	     banking = mainpage.click_page();
	     summary = banking.navigate_accountsummary();
	     fund = summary.navigate();
	     Assert.assertNotNull(fund, "Failed to click on the Transfer Fund link!");
	}

	@When("User selects {string} as the source account for negative amount transfer")
	public void user_select_negative_amount_from_account(String string) {
        fund.selectFromAccount(string);
	}

	@When("User selects {string} as the destination account for negative amount transfer")
	public void user_select_negative_amount_to_account(String string) {
		fund.selectToAccount(string);
	}

	@When("User enters negative transfer amount {string} and description {string}")
	public void user_enter_negative_amount_and_description(String amount, String description) {
	    fund.enterAmount(amount);
	    fund.enterDescription(description);
	}

	@When("User clicks on continue button for negative amount transfer")
	public void user_click_on_continue_button_negative_amount() {
	    fund.submission();
	    verify = new Verify(driver);
	  	verify.navigate_submit();
	}

	@Then("User should see the error message {string} for negative amount transfer")
	public void user_should_see_the_error_message_negative_amount(String expectedMessage) {
		String actualMessage = verify.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Error message mismatch!");
        
        driver.quit();
	}
}
