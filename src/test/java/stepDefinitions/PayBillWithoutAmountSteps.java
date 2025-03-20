package stepDefinitions;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.*;

public class PayBillWithoutAmountSteps {
    WebDriver driver;
    LoginPage loginpage;
    MainPage mainpage;
    OnlineBankingLink banking;
    AccountSummary summary;
    PayBill bill;

    @Given("User is on the Pay Bill page")
    public void user_is_on_the_pay_bill_page() {
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

    @When("User selects {string} as the payee without entering amount")
    public void user_selects_payee_without_amount(String payee) {
        bill.selectpayee(payee);
    }

    @And("User selects {string} as the account without entering amount")
    public void user_selects_account_without_amount(String account) {
        bill.selectAccount(account);
    }

    @And("User leaves the amount field empty")
    public void user_leaves_amount_field_empty() {
        bill.clearAmountField();
    }

    @And("User clicks on the pay button")
    public void user_clicks_on_pay_button() {
        bill.pay();
    }

    @Then("User should see an error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        assertEquals(expectedMessage, bill.getErrorMessage());
        driver.quit();
    }
}
