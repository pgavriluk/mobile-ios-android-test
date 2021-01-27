package util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    @Attachment(value = "{testName} - screenshot")
    public static byte[] screenshot(WebDriver driver, String testName) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
