package action;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.fail;

public class Scroll {

    enum Position {
        TOP, BOTTOM, ONE_THIRD, TWO_THIRD, MIDDLE
    }

    @Step("scrollAmount")
    public void scrollAmount(MobileDriver<MobileElement> driver, Position start, Position end, int amount) {
        IntStream.range(0, amount).forEach(x -> {
            new TouchAction(driver)
                    .longPress(getPositionCoordinates(driver, start))
                    .moveTo(getPositionCoordinates(driver, end))
                    .release()
                    .perform();
        });
    }

    private static PointOption getPositionCoordinates(MobileDriver<MobileElement> driver, Position position) {
        Dimension dimension = driver.manage().window().getSize();
        switch (position) {
            case TOP:
                return new PointOption().withCoordinates(dimension.getWidth() / 2, 0);
            case ONE_THIRD:
                return new PointOption().withCoordinates(dimension.getWidth() / 2, Double.valueOf(dimension.getHeight() * .33).intValue());
            case MIDDLE:
                return new PointOption().withCoordinates(dimension.getWidth() / 2, dimension.getHeight() / 2);
            case TWO_THIRD:
                return new PointOption().withCoordinates(dimension.getWidth() / 2, Double.valueOf(dimension.getHeight() * .66).intValue());
            case BOTTOM:
                return new PointOption().withCoordinates(dimension.getWidth() / 2, dimension.getHeight() - 1);
            default:
                fail("The position passed in is not valid.");
        }
        return null;
    }

    public static void scrollTerms(MobileDriver<MobileElement> driver, String uiAutomatorScroll, String uiAutomatorWait) {

        try {
//            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingToEnd(25)");
            //driver.findElementByAndroidUIAutomator(uiAutomatorScroll);
            System.out.println("Debug: Passed the fling scroll event.");
//            completed = true;
        } catch (InvalidSelectorException e) {
            //Due to appium expecting an object back from the above find element I have encapsulated it to prevent further issues.
            System.out.println("Caught InvalidSelectorException");
        } catch (Exception e) {
            System.out.println("Caught general exception");
        }
    }

    public static void scrollForward(MobileDriver<MobileElement> driver) {
        try {
            if (DriverFactory.isIos()) {
                RemoteWebElement element = driver.findElement(MobileBy.xpath("//*"));
                String elementId = element.getId();
                HashMap<String, String> scrollObject = new HashMap<>();
                scrollObject.put("element", elementId);
                scrollObject.put("direction", "down");
                ((IOSDriver<MobileElement>) driver).executeScript("mobile:scroll", scrollObject);
            } else {
                ((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(false).instance(0)).scrollForward()");
            }
        } catch (InvalidSelectorException ignored) {
        }

    }

    public static void flingToEnd(MobileDriver<MobileElement> driver) {
        try {
            if (!DriverFactory.isIos()) {
                ((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingToEnd(500)");
            }
        } catch (InvalidSelectorException ignored) {
        }

    }

    public static void scrollTextIntoView(MobileDriver<MobileElement> driver, String text) {
        scrollTextIntoView(driver, text, false);
    }

    public static void scrollTextIntoView(MobileDriver<MobileElement> driver, String text, boolean scrollable) {
        int count = 0;
        try {
            while (true) {
                try {
                    if (DriverFactory.isIos()) {
                        RemoteWebElement element = driver.findElement(MobileBy.xpath("//*"));
                        String elementId = element.getId();
                        HashMap<String, String> scrollObject = new HashMap<>();
                        scrollObject.put("element", elementId);
                        scrollObject.put("predicateString", "label == '" + text + "'");
                        ((IOSDriver<MobileElement>) driver).executeScript("mobile:scroll", scrollObject);
                    } else {
                        String script = String.format("new UiScrollable(new UiSelector().scrollable(%b).instance(0)).scrollTextIntoView(\"%s\")", scrollable, text);
                        ((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(script);
                    }
                    return;
                } catch (NoSuchElementException ex) {
                    if (count++ == 10) {
                        throw ex;
                    }
                    Loading.wait(1, TimeUnit.SECONDS);
                }
            }

        } catch (InvalidSelectorException ignored) {
        }
    }

    public static void scroll(MobileDriver<MobileElement> driver, int startX, int startY, int endX, int endY) {

        TouchAction touchAction = new TouchAction(driver);

        touchAction.longPress(PointOption.point(startX, startY))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .perform();
    }

    public static void scrollDown(MobileDriver<MobileElement> driver) {
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Starting y location set to 80% of the height (near bottom)
        int startY = (int) (dimension.height * 0.8);
        //Ending y location set to 20% of the height (near top)
        int endY = (int) (dimension.height * 0.2);
        //x position set to mid-screen horizontally
        int startX = dimension.width / 2;

        scroll(driver, startX, startY, startX, endY);
    }

    public static void scrollDownHalfScreen(MobileDriver<MobileElement> driver) {
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Starting y location set to 80% of the height (near bottom)
        int startY = (int) (dimension.height * 0.5);
        //Ending y location set to 20% of the height (near top)
        int endY = (int) (dimension.height * 0.2);
        //x position set to mid-screen horizontally
        int startX = dimension.width / 2;

        scroll(driver, startX, startY, startX, endY);
    }

    public static void scrollDownFromElement(MobileDriver<MobileElement> driver, By by) {
        //Get position of the element
        Point point = driver.findElement(by).getLocation();
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Ending y location set to 15% of the height (near top)
        int endY = (int) (dimension.height * 0.15);

        scroll(driver, point.getX(), point.getY(), point.getX(), endY);
    }

    public static void scrollDownFromElement(MobileDriver<MobileElement> driver, MobileElement element) {
        //Get position of the element
        Point point = element.getCenter();
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Ending y location set to 15% of the height (near top)
        int endY = (int) (dimension.height * 0.15);

        scroll(driver, point.getX(), point.getY(), point.getX(), endY);
    }

    public static void scrollUpFromElement(MobileDriver<MobileElement> driver, MobileElement element) {
        //Get position of the element
        Point point = element.getCenter();
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Ending y location set to 85% of the height (near top)
        int endY = (int) (dimension.height * 0.85);

        scroll(driver, point.getX(), point.getY(), point.getX(), endY);
    }

    public static void scrollOnlyHalfScreenDownFromElement(MobileDriver<MobileElement> driver, By by) {
        //Get position of the element
        Point point = driver.findElement(by).getLocation();
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Ending y location set to 50% of the height
        int endY = (int) (dimension.height * 0.50);

        scroll(driver, point.getX(), point.getY(), point.getX(), endY);
    }

    public static void swipeLeft(MobileDriver<MobileElement> driver) {
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Starting x location set to 95% of the width (near right)
        int startX = (int) (dimension.width * 0.95);
        //Ending x location set to 5% of the width (near left)
        int endX = (int) (dimension.width * 0.05);
        //y position set to mid-screen vertically
        int startY = dimension.height / 2;

        scroll(driver, startX, startY, endX, startY);
    }

    public static void swipeLeftFromElement(MobileDriver<MobileElement> driver, By by) {
        //Get position of the element
        Point point = driver.findElement(by).getLocation();
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Ending x location set to 5% of the width (near left)
        int endX = (int) (dimension.width * 0.05);

        scroll(driver, point.getX(), point.getY(), endX, point.getY());
    }

    public static void swipeRight(MobileDriver<MobileElement> driver) {
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Starting x location set to 5% of the width (near left)
        int startX = (int) (dimension.width * 0.05);
        //Ending x location set to 95% of the width (near right)
        int endX = (int) (dimension.width * 0.95);
        //y position set to mid-screen vertically
        int startY = dimension.height / 2;

        scroll(driver, startX, startY, endX, startY);
    }

    public static void scrollUp(MobileDriver<MobileElement> driver) {
        //The viewing size of the device
        Dimension dimension = driver.manage().window().getSize();

        //Starting y location set to 20% of the height (near bottom)
        int startY = (int) (dimension.height * 0.2);
        //Ending y location set to 80% of the height (near top)
        int endY = (int) (dimension.height * 0.8);
        //x position set to mid-screen horizontally
        int startX = dimension.width / 2;

        scroll(driver, startX, startY, startX, endY);
    }

}
