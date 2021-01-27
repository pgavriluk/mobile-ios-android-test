package action;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Loading {

    private final static int CLICKABLE_WAIT = 10;
    private final static int VISIBLE_WAIT = 30;
    private final static int INVISIBLE_WAIT = 120;

    public static void wait(long duration, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static MobileElement tappable(MobileDriver<MobileElement> driver, By by) {
        return (MobileElement) new WebDriverWait(driver, CLICKABLE_WAIT)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public static MobileElement tappable(MobileDriver<MobileElement> driver, MobileElement element) {
        return (MobileElement) new WebDriverWait(driver, CLICKABLE_WAIT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static MobileElement tappable(MobileDriver<MobileElement> driver, MobileElement element, By ext) {
        WebDriverWait wait = new WebDriverWait(driver, CLICKABLE_WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
        return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(element.findElement(ext)));
    }

    public static MobileElement visible(MobileDriver<MobileElement> driver, By by) {
        try {
            return (MobileElement) new WebDriverWait(driver, VISIBLE_WAIT)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException ex) {
            return null;
        }

    }

    public static MobileElement visible(MobileDriver<MobileElement> driver, MobileElement element) {
        return (MobileElement) new WebDriverWait(driver, VISIBLE_WAIT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static MobileElement visible(MobileDriver<MobileElement> driver, MobileElement element, By ext) {
        WebDriverWait wait = new WebDriverWait(driver, VISIBLE_WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element.findElement(ext)));
    }

    public static boolean invisible(MobileDriver<MobileElement> driver, By by) {
        return new WebDriverWait(driver, INVISIBLE_WAIT)
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static MobileElement present(MobileDriver<MobileElement> driver, By by) {
        return (MobileElement) new WebDriverWait(driver, VISIBLE_WAIT)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void present(MobileDriver<MobileElement> driver, MobileElement em, By by) {
        new WebDriverWait(driver, VISIBLE_WAIT)
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(em, by));
    }

}
