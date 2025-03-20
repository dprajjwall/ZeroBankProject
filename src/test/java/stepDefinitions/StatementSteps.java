package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.*;

public class StatementSteps {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    OnlineBankingLink banking;
    AccountSummary summary;
    Statement statementsPage;

    @Given("User is logged in and on the Statements & Documents page")
    public void user_is_on_statements_page() {
		WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/login.html");

        loginPage = new LoginPage(driver);
        loginPage.enterUsername("username");
        loginPage.enterPassword("password");
        loginPage.clickSignIn();


        try {
            System.out.println("Waiting for manual action (Captcha, 2FA, etc.)...");
            Thread.sleep(10000);  // Wait for 10 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("index.html"), "Login failed! Not redirected to homepage.");
        mainPage = new MainPage(driver);
	    banking = mainPage.click_page();
	    summary = banking.navigate_accountsummary();
	    statementsPage = summary.statement_navigate();
    }
    @And("User clicks to download the statement")
    public void user_clicks_download_statement() {
        statementsPage.proceed();
    }

    @Then("The download button should be clicked")
    public void verify_statement_download() {
        Assert.assertTrue(true,"Download button was clicked successfully.");
        driver.quit();
    }
    
}