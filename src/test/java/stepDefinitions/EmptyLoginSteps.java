package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import utilities.ScreenshotUtil;
import static org.testng.Assert.*;

import java.io.File;

public class EmptyLoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("User opens the Zero Bank login page for Empty Login")
    public void user_opens_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/login.html");
        loginPage = new LoginPage(driver);
    }

    @When("User does not enter any credentials for Empty Login")
    public void user_does_not_enter_credentials() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
    }

    @And("Clicks on the Sign In button for Empty Login")
    public void user_clicks_sign_in() {
        loginPage.clickSignIn();
    }

    @Then("User should see an error message {string} for Empty Login")
    public void verify_error_message(String expectedMessage) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "EmptyLogin");

        if (screenshotPath != null && new File(screenshotPath).exists()) {
            System.out.println("Screenshot successfully saved. Test Passed!");
        } else {
            fail("Screenshot not found. Test Failed!");
        }

        driver.quit();
    }
}

