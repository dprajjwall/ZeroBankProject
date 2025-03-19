package stepDefinitions;

import java.util.Scanner;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import pageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LogoutSteps {

    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;

    @Given("User is logged into the Zero Bank application")
    public void user_is_logged_into_the_zero_bank_application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/login.html");

        loginPage = new LoginPage(driver);
        loginPage.enterUsername("username");
        loginPage.enterPassword("password");
        loginPage.clickSignIn();

        handleManualAuthentication();

        mainPage = new MainPage(driver);
    }

    private void handleManualAuthentication() {
    	try {
    	    System.out.println("Waiting for manual action (Captcha, 2FA, etc.)...");
    	    Thread.sleep(10000);  // Wait for 10 seconds (adjust if needed)
    	} catch (InterruptedException e) {
    	    e.printStackTrace();
    	}
    }

    @When("User clicks on the logout button")
    public void user_clicks_on_the_logout_button() {
        mainPage.clickLogout();
    }

    @Then("User should be logged out and redirected to the login page")
    public void user_should_be_logged_out_and_redirected_to_the_login_page() {
        String expectedUrl = "http://zero.webappsecurity.com/index.html";
        String actualUrl = driver.getCurrentUrl();
        
        Assert.assertEquals("Logout failed. User is not redirected correctly.", expectedUrl, actualUrl);
        
        driver.quit();
    }
}
