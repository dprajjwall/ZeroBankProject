package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import utilities.ScreenshotUtil;
import java.util.Scanner;
import static org.testng.Assert.*;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("User opens the Zero Bank login page")
    public void user_opens_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/login.html");
        loginPage = new LoginPage(driver);
    }

    @When("User enters valid username {string} and password {string}")
    public void user_enters_valid_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("Clicks on the Sign In button")
    public void user_clicks_sign_in() {
        loginPage.clickSignIn();
    }

    @Then("User should be redirected to the Account Summary page")
    public void verify_account_summary_page() {
    	  try {
              System.out.println("Waiting for manual action (Captcha, 2FA, etc.)...");
              Thread.sleep(10000);  // Wait for 10 seconds (adjust as needed)
          } catch (InterruptedException e) {
              e.printStackTrace();
          }  


        ScreenshotUtil.captureScreenshot(driver, "SuccessfulLogin");

        // Verify if login was successful
        assertTrue(driver.getTitle().contains("Zero"), "Login failed!");
        System.out.println("Login Successful!");

        driver.quit();
    }
}
