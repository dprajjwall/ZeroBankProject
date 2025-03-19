package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.SeleniumUtils;

public class AddNewPayee {
   WebDriver driver;
   WebDriverWait wait;
   PayBill bill;

   public AddNewPayee(WebDriver driver) {
       this.driver = driver;
       this.bill = bill;
       this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       PageFactory.initElements(driver, this);
   }

   // Page Elements
   @FindBy(id = "np_new_payee_name") WebElement newPayeeName;
   @FindBy(id = "np_new_payee_address") WebElement newPayeeAddress;
   @FindBy(id = "np_new_payee_account") WebElement newPayeeAccount;
   @FindBy(id = "np_new_payee_details") WebElement newPayeeDetails;
   @FindBy(id = "add_new_payee") WebElement addPayeeButton;

   By successMessage = By.cssSelector(".alert-success");

   // Separate Functions for Each Field
   public void enterPayeeName(String name) {
	   SeleniumUtils.waitForElementVisible(newPayeeName, driver);
	   SeleniumUtils.sendKeys(newPayeeName, name, driver);
   }

   public void enterPayeeAddress(String address) {
	   SeleniumUtils.waitForElementVisible(newPayeeAddress, driver);
	   SeleniumUtils.sendKeys(newPayeeAddress, address, driver);
   }

   public void enterPayeeAccount(String account) {
	   SeleniumUtils.waitForElementVisible(newPayeeAccount, driver);
	   SeleniumUtils.sendKeys(newPayeeAccount, account, driver);
   }

   public void enterPayeeDetails(String details) {
	   SeleniumUtils.waitForElementVisible(newPayeeDetails, driver);
	   SeleniumUtils.sendKeys(newPayeeDetails, details, driver);
   }

   public void clickAddPayee() {
       wait.until(ExpectedConditions.elementToBeClickable(addPayeeButton));
       addPayeeButton.click(); 
   }
   
   public String getSuccessMessage() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
       return bill != null ? bill.message() : driver.findElement(successMessage).getText();
   }
   
   public String getFieldValidationMessage() {
	    return newPayeeName.getAttribute("validationMessage");
	}

}
