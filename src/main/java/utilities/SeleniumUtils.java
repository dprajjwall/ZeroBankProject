package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtils {

    private static final long TIMEOUT_SECONDS = 10;

    // Waits until the element is visible on the page
    public static void waitForElementVisible(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Waits until the element is clickable and clicks it
    public static void clickElement(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Clears the input field and sends the provided text
    public static void sendKeys(WebElement element, String text, WebDriver driver) {
        waitForElementVisible(element, driver);
        element.clear();
        element.sendKeys(text);
    }

    // Selects a dropdown option by its visible text
    public static void selectDropdownByVisibleText(WebElement dropdownElement, String visibleText) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    // Waits until the element contains specific text (optional helper)
    public static void waitForTextPresent(WebElement element, String text, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
