package pageObjects;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.DriverManager;
import utilities.ScreenshotUtil;
import utilities.SeleniumUtils;


public class PayBill {
   WebDriver driver;
   WebDriverWait wait;
   
   public PayBill(WebDriver driver) {
	   this.driver = driver;
	   this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(id = "sp_payee") WebElement payee;
   @FindBy(id = "sp_account") WebElement account;
   @FindBy(id = "sp_amount") WebElement amount;
   @FindBy(id = "sp_date") WebElement date;
   @FindBy(id = "sp_description") WebElement description;
   @FindBy(id = "alert_content") WebElement successAlert;
   @FindBy(id = "pay_saved_payees") WebElement proceed;  
   @FindBy(linkText = "Add New Payee") WebElement addNewPayeeTab;
   
   public void selectpayee(String payeename) {
	    SeleniumUtils.waitForElementVisible(payee, driver);
	    SeleniumUtils.selectDropdownByVisibleText(payee, payeename);
	}
	
	public void selectAccount(String accountName) {
		SeleniumUtils.waitForElementVisible(account, driver);
		SeleniumUtils.selectDropdownByVisibleText(account, accountName);
	}
	
	public void enterAmount(String amountValue) {
       SeleniumUtils.sendKeys(amount, amountValue, driver);
    }

    public void clearAmountField() {
        SeleniumUtils.waitForElementVisible(amount, driver);
        amount.clear();
    }

    public String getErrorMessage() {
    	SeleniumUtils.waitForElementVisible(amount, driver);
        return amount.getAttribute("validationMessage");
    }
	
	public void enterDate(String dateValue) {
		SeleniumUtils.sendKeys(date, dateValue, driver);
	}
	
	public void pay() {
		 WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("pay_saved_payees")));
		 proceed.click();
	}
	public void enterDescription(String desc) {
		SeleniumUtils.sendKeys(description, desc, driver);
	}
	public String message() {
		By successAlertBy = By.id("alert_content");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(successAlertBy));
	    WebElement successAlertElement = driver.findElement(successAlertBy);
	    return successAlertElement.getText();
	}
	
	public AddNewPayee addnew() {
		addNewPayeeTab.click();
		return new AddNewPayee(driver);
	}
}
