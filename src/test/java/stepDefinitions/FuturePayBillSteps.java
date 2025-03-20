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

public class FuturePayBillSteps {
    WebDriver driver;
    LoginPage loginpage;
    MainPage mainpage;
    OnlineBankingLink banking;
    AccountSummary summary;
    PayBill bill;

    @Given("User has logged in and accessed the Bill Payment page")
    public void user_logs_in_and_accesses_bill_payment_page() {
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

        mainpage = new MainPage(driver);
        banking = mainpage.click_page();
        summary = banking.navigate_accountsummary();
        bill = summary.paybill_navigate();
    }

    @When("User selects {string} as the payee for future bill")
    public void user_selects_payee_for_future_bill(String payee) {
        bill.selectpayee(payee);
    }

    @And("User selects {string} as the account to be debited")
    public void user_selects_account(String account) {
        bill.selectAccount(account);
    }

    @And("User enters amount {string} and picks a future date {string}")
    public void user_enters_amount_and_future_date(String amount, String futureDate) {
        bill.enterAmount(amount);
        bill.enterDate(futureDate);
    }

    @And("User enters {string} in the description")
    public void user_enters_description(String description) {
        bill.enterDescription(description);
    }

    @And("User clicks on the pay button to schedule payment")
    public void user_clicks_on_pay_button() {
        bill.pay();
    }

    @Then("User should see a confirmation message {string}")
    public void user_should_see_success_message(String expectedMessage) {
        assertEquals(expectedMessage, bill.message());
        driver.quit();
    }
}
