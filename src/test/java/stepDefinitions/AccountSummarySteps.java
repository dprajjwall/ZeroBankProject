package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import pageObjects.OnlineBankingLink;
import pageObjects.AccountSummary;
import pageObjects.MainPage;

import java.util.Scanner;

public class AccountSummarySteps {

    WebDriver driver;
    LoginPage loginpage;
    OnlineBankingLink banking;
    AccountSummary summary;
    MainPage mainpage;

    @Given("I am logged in to Zero Bank")
    public void navigateToMainPageAndLogin() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
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

    @When("I click on the Online Banking link")
    public void clickOnOnlineBankingLink() {
        mainpage = new MainPage(driver);
        banking = mainpage.click_page();
        Assert.assertNotNull(banking, "Failed to click on the Online Banking link!");
    }

    @And("I navigate to the Account Summary page")
    public void navigateToAccountSummary() {
        summary = banking.navigate_accountsummary();

        String actualTitle = summary.getPageTitle();
        String expectedTitle = "Zero - Account Summary";

        System.out.println("Actual Page Title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Account Summary page title does not match!");
        driver.quit();
    }
}
