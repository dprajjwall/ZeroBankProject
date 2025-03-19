package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utilities.SeleniumUtils.waitForElementVisible;

import java.time.Duration;

import static utilities.SeleniumUtils.selectDropdownByVisibleText;
import static utilities.SeleniumUtils.sendKeys;
import static utilities.SeleniumUtils.clickElement;


public class TransferFund {

	WebDriver driver;
	WebDriverWait wait;
	   
	public TransferFund(WebDriver driver) {
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "tf_fromAccountId") WebElement fromAccount;
	@FindBy(id = "tf_toAccountId") WebElement toAccount;
	@FindBy(id = "tf_amount") WebElement Amount;
	@FindBy(id="tf_description") WebElement description;
	@FindBy(id = "btn_submit") WebElement submit;
	
	public void selectFromAccount(String accountName) {
	    waitForElementVisible(fromAccount, driver);
	    selectDropdownByVisibleText(fromAccount, accountName);
	}
	
	public void selectToAccount(String accountName) {
		waitForElementVisible(toAccount, driver);
		selectDropdownByVisibleText(toAccount, accountName);
	}
	
	public void enterAmount(String amountValue) {
        sendKeys(Amount, amountValue, driver);
    }
	
	public void enterDescription(String descriptionText) {
        sendKeys(description, descriptionText, driver);
    }
	

	public void submission() {
	    submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_submit")));
		submit.click();
	}
	
	
}


