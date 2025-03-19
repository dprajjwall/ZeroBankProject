package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSummary {
	WebDriver driver;
	
	public AccountSummary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
        return driver.getTitle();
    }
	
	@FindBy(xpath = "//h2[normalize-space()='Cash Accounts']")
    private WebElement cashAccounts;

    @FindBy(xpath = "//h2[normalize-space()='Investment Accounts']")
    private WebElement investmentAccounts;

    @FindBy(xpath = "//h2[normalize-space()='Credit Accounts']")
    private WebElement creditAccounts;

    @FindBy(xpath = "//h2[normalize-space()='Loan Accounts']")
    private WebElement loanAccounts;
    
    @FindBy(id="transfer_funds_tab")
    private WebElement transferfund;
    
    @FindBy(id = "pay_bills_tab") WebElement payBill;
    
    @FindBy(id = "online_statements_tab") WebElement statement;
    
    @FindBy(xpath = "(//li[@class='dropdown'])[2]/a") 
    WebElement usernameDropdown;
    
    @FindBy(id = "logout_link")
    WebElement logoutButton;
    
    public boolean verify() {
    	return cashAccounts.isDisplayed() && investmentAccounts.isDisplayed()
                && creditAccounts.isDisplayed() && loanAccounts.isDisplayed();
    }
    
    public TransferFund navigate() {
    	transferfund.click();
    	return new TransferFund(driver);
    }
    
    public PayBill paybill_navigate() {
    	payBill.click();
    	return new PayBill(driver);
    }
    
    public Statement statement_navigate() {
    	statement.click();
    	return new Statement(driver);
    }
    
    public void clickLogout() {
        usernameDropdown.click();
        logoutButton.click();
    }
	
}
