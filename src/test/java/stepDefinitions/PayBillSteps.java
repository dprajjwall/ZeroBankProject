package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.*;

public class PayBillSteps {
	WebDriver driver;
	LoginPage loginpage;
	MainPage mainpage;
	OnlineBankingLink banking;
	AccountSummary summary;
	PayBill bill;
	Verify verify;
	
	
	 @Given("User has logged into the banking application and navigates to Pay Bill page")
	    public void user_logs_in_and_navigates_to_pay_bill_page() {
		 WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
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
	        mainpage = new MainPage(driver);
		    banking = mainpage.click_page();
		    summary = banking.navigate_accountsummary();
		    bill = summary.paybill_navigate();	        
	 }
	 @When("User selects {string} as the payee")
	    public void user_selects_payee(String payee) {
	        bill.selectpayee(payee);
	   }

	 @And("User selects {string} as the account")
	    public void user_selects_account(String account) {
	        bill.selectAccount(account);
	    }

	 @And("User enters amount {string} and date {string}")
	    public void user_enters_amount_and_date(String amount, String date) {
	        bill.enterAmount(amount);
	        bill.enterDate(date);
	    }

	    @And("User enters {string} in description")
	    public void user_enters_description(String description) {
	        bill.enterDescription(description);
	    }

	    @And("User clicks on pay button for bill payment")
	    public void user_clicks_on_pay_button() {
	        bill.pay();
	    }

	    @Then("User should see the success message {string}")
	    public void user_should_see_success_message(String expectedMessage) {
	        assertEquals(expectedMessage, bill.message());
	        driver.quit();
	    }
	    

}
