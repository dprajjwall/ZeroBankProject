package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user_login") WebElement txtUsername;
    @FindBy(id = "user_password") WebElement txtPassword;
    @FindBy(name = "submit") WebElement btnSignIn;
    @FindBy(css = ".alert-error") WebElement errorMessage;

    public void enterUsername(String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickSignIn() {
        btnSignIn.click();
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "Error message not found!";
        }
    }
}
