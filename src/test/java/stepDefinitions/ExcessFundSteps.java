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

public class ExcessFundSteps {
	
	WebDriver driver;
	LoginPage loginpage;
	MainPage mainpage;
	OnlineBankingLink banking;
	AccountSummary summary;
	TransferFund fund;
	Verify verify;
	
	@Given("User has logged into the banking application for excess fund transfer")
	public void user_logged_into_the_banking_application_excess() {
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

	@Given("User navigates to the Fund Transfer page for excess fund transfer")
	public void user_navigate_to_the_fund_transfer_page_excess() {
	     mainpage = new MainPage(driver);
	     banking = mainpage.click_page();
	     summary = banking.navigate_accountsummary();
	     fund = summary.navigate();
	     Assert.assertNotNull(fund, "Failed to click on the Transfer Fund link!");
	}

	@When("User selects {string} as the source account for excess fund transfer")
	public void user_select_excess_fund_from_account(String string) {
        fund.selectFromAccount(string);
	}

	@When("User selects {string} as the destination account for excess fund transfer")
	public void user_select_excess_fund_to_account(String string) {
		fund.selectToAccount(string);
	}

	@When("User enters transfer amount {string} and description {string} for excess fund transfer")
	public void user_enter_excess_fund_amount_and_description(String string, String string2) {
	    fund.enterAmount(string);
	    fund.enterDescription(string2);
	}

	@When("User clicks on continue button for excess fund transfer")
	public void user_click_on_continue_button_excess() {
	    fund.submission();
	}

	@When("User reviews the transfer details and submits for excess fund transfer")
	public void user_review_the_transfer_details_and_submits_excess() {
		verify = new Verify(driver);
	  	verify.navigate_submit();
	}

	@Then("User should see the error message {string} for excess fund transfer")
	public void user_should_see_the_error_message_excess(String string) {
		String actualMessage = "Insufficient Funds";
        Assert.assertTrue(actualMessage.contains(string), "Error message mismatch!");
        driver.quit();
	}
}
