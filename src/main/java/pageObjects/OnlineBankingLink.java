package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnlineBankingLink {
   WebDriver driver;
   
   public OnlineBankingLink(WebDriver driver) {
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(css = "#account_summary_link") WebElement accountsummary;
   
   public AccountSummary navigate_accountsummary() {
	   accountsummary.click();
	   return new AccountSummary(driver);
   }
}
