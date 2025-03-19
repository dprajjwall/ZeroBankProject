package stepDefinitions;

import static org.testng.Assert.assertTrue;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.*;

public class AddNewPayeeSteps {
    WebDriver driver;
    LoginPage loginpage;
    MainPage mainpage;
    OnlineBankingLink banking;
    AccountSummary summary;
    PayBill bill;
    AddNewPayee addNewPayee;

    @Given("User has logged into the banking application and navigates to Add New Payee page")
    public void user_logs_in_and_navigates_to_add_new_payee_page() {
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
        addNewPayee = bill.addnew();
    }

    @When("User enters {string} as the payee name")
    public void user_enters_payee_name(String payeeName) {
        addNewPayee.enterPayeeName(payeeName);
    }

    @And("User enters {string} as the payee address")
    public void user_enters_payee_address(String payeeAddress) {
        addNewPayee.enterPayeeAddress(payeeAddress);
    }

    @And("User enters {string} as the payee account")
    public void user_enters_payee_account(String payeeAccount) {
        addNewPayee.enterPayeeAccount(payeeAccount);
    }

    @And("User enters {string} as the payee details")
    public void user_enters_payee_details(String payeeDetails) {
        addNewPayee.enterPayeeDetails(payeeDetails);
    }

    @And("User clicks the Add button")
    public void user_clicks_add_button() {
        addNewPayee.clickAddPayee();
    }

    @Then("User should see a new payee confirmation message {string}")
    public void user_should_see_new_payee_confirmation_message(String expectedMessage) {
        String actualMessage = addNewPayee.getSuccessMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected success message was not displayed!");
        
        driver.quit();
    }

}
