package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Get current timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define destination path
            String filePath = "screenshots/" + testName + "_" + timestamp + ".png";

            // Save screenshot
            FileUtils.copyFile(srcFile, new File(filePath));

            System.out.println("✅ Screenshot saved at: " + filePath);
            return filePath; // Return the saved file path

        } catch (IOException e) {
            System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null; // Return null if screenshot saving fails
        }
    }
}
