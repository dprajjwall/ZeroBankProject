package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.OnlineBankingLink;
import pageObjects.AccountSummary;

import java.util.Scanner;

public class SessionExpirySteps {

    WebDriver driver;
    LoginPage loginPage;
    OnlineBankingLink bankingPage;
    AccountSummary summaryPage;
    MainPage mainpage;

    @Given("I have successfully logged into Zero Bank")
    public void userLogsIntoZeroBank() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/login.html");

        loginPage = new LoginPage(driver);
        loginPage.enterUsername("username");
        loginPage.enterPassword("password");
        loginPage.clickSignIn();


        try {
            System.out.println("Waiting for manual action (Captcha, 2FA, etc.)...");
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("index.html"), "Login failed! Not redirected to homepage.");
    }

    @When("I select the Online Banking option")
    public void navigateToOnlineBanking() {
    	mainpage = new MainPage(driver);
        bankingPage = mainpage.click_page();
        Assert.assertNotNull(bankingPage, "Failed to navigate to Online Banking!");
    }

    @And("I proceed to the Account Summary section")
    public void openAccountSummary() {
        summaryPage = bankingPage.navigate_accountsummary();
        Assert.assertNotNull(summaryPage, "Failed to navigate to Account Summary!");
    }

    @And("I log out from the Account Summary page")
    public void userLogsOut() {
        summaryPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"), "Logout failed! Still on a logged-in page.");
    }

    @And("I attempt to navigate back using the browser's Back button")
    public void userClicksBackButton() {
        driver.navigate().back();
    }

    @Then("I should be redirected to the login page or see a session timeout message")
    public void verifySessionExpired() {
        boolean isSessionExpired = driver.getCurrentUrl().contains("login.html") || driver.getCurrentUrl().contains("index.html");

        Assert.assertTrue(isSessionExpired, "Session was not expired! User can still access the previous page.");
        driver.quit();
    }
}
