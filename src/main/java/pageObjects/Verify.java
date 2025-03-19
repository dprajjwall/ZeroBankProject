package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Verify {
   WebDriver driver;
   
   
   public Verify(WebDriver driver) {
	   this.driver = driver;
       PageFactory.initElements(driver, this);
   }
   
   @FindBy(id = "btn_submit") WebElement submit;
   @FindBy(css = ".alert-success") WebElement successAlert;
   
   public void navigate_submit() {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_submit")));
	   submit.click();
   }
   public String getSuccessMessage() {
       return successAlert.getText();
   }
}
