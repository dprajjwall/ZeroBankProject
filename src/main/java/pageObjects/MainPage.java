package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "onlineBankingMenu") 
    WebElement onlineBankingLink;

    @FindBy(xpath = "(//li[@class='dropdown'])[2]/a") 
    WebElement usernameDropdown;

    @FindBy(id = "logout_link")  // Replace with actual ID from the screenshot if different
    WebElement logoutButton;

    public OnlineBankingLink click_page() {
        onlineBankingLink.click();
        return new OnlineBankingLink(driver);
    }

    public void clickLogout() {
        usernameDropdown.click();
        logoutButton.click();
    }
}
