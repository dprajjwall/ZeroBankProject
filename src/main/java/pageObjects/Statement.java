package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Statement {
     
	WebDriver driver;
	WebDriverWait wait;
	
	public Statement(WebDriver driver) {
	   this.driver = driver;
	   this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   PageFactory.initElements(driver, this);
	   
	}
	@FindBy(css = "a[href*='statement']") 
	WebElement statementLink;;
	
	public void proceed() {
		statementLink.click();
	}
}
