package stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.*;

public class WithoutPayeeSteps {
    WebDriver driver;
    LoginPage loginpage;
    MainPage mainpage;
    OnlineBankingLink banking;
    AccountSummary summary;
    PayBill bill;
    AddNewPayee addNewPayee;

    @Given("User is logged in and on the Add New Payee page")
    public void user_is_logged_in_and_on_add_new_payee_page() {
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

        mainpage = new MainPage(driver);
        banking = mainpage.click_page();
        summary = banking.navigate_accountsummary();
        bill = summary.paybill_navigate();
        addNewPayee = bill.addnew();
    }

    @When("User leaves the {string} field empty")
    public void user_leaves_field_empty(String fieldName) {
        if (fieldName.equalsIgnoreCase("Payee Name")) {
            addNewPayee.clickAddPayee(); // Click add without entering name
        }
    }

    @And("User clicks the {string} button")
    public void user_clicks_the_button(String button) {
        if (button.equalsIgnoreCase("Add")) {
            addNewPayee.clickAddPayee();
        }
    }

    @Then("User should see an error message {string} without payee")
    public void user_should_see_error_message_without_payee(String expectedMessage) {
        String actualMessage = addNewPayee.getFieldValidationMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Expected error message was not displayed!");
        driver.quit();
    }
}
