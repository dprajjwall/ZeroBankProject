package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import io.cucumber.messages.types.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import utilities.ScreenshotUtil;
import static org.testng.Assert.*;
import java.io.File;
public class InvalidLoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("User opens the Zero Bank login page for Invalid Login")
    public void user_opens_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/login.html");
        loginPage = new LoginPage(driver);
    }

    @When("User enters invalid username {string} and password {string} for Invalid Login")
    public void user_enters_invalid_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("Clicks on the Sign In button for Invalid Login")
    public void user_clicks_sign_in() {
        loginPage.clickSignIn();
    }

    @Then("User should see an error message {string} for Invalid Login")
    public void verify_error_message(String expectedMessage) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "InvalidLogin");

        if (screenshotPath != null && new File(screenshotPath).exists()) {
            System.out.println("Screenshot successfully saved. Test Passed!");
        } else {
            fail("Screenshot not found. Test Failed!");
        }

        driver.quit();
    }

}
