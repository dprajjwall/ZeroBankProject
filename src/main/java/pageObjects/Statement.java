package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;


public class Statement {
     
	WebDriver driver;
	WebDriverWait wait;
	
	public Statement(WebDriver driver) {
	   this.driver = driver;
	   this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   PageFactory.initElements(driver, this);
	   
	}
	
	public void proceed() {
		WebElement statementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='os_2012']/table/tbody/tr/td[1]/a")));
		statementLink.click();
	}
}
