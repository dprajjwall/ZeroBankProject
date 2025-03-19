package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Logout {
    WebDriver driver;

    public Logout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "logout_link") 
    WebElement logoutButton;

    @FindBy(xpath = "//h3[contains(text(),'Log in to ZeroBank')]") 
    WebElement loginPageHeader;

    public void clickLogout() {
        logoutButton.click();
    }

    public void verifyLogoutSuccess() {
        Assert.assertTrue(loginPageHeader.isDisplayed(), "Logout failed! User is not on the login page.");
    }
}
