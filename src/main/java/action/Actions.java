package action;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Actions {

    private final static int TAP_RETRY = 3;
    private final static int TYPE_RETRY = 3;
    private final static int TEXT_RETRY = 3;

    public static void hideKeyboard(MobileDriver<MobileElement> driver) {
        driver.hideKeyboard();
    }

    public static void tap(MobileDriver<MobileElement> driver, By by) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, by);
                driver.findElement(by).click();
                return;
            } catch (Exception e) {
                if (++count == TAP_RETRY)
                    throw e;
            }
        }

    }

    public static void tap(MobileDriver<MobileElement> driver, MobileElement element) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, element);
                element.click();
                return;
            } catch (Exception e) {
                if (++count == TAP_RETRY)
                    throw e;
            }
        }

    }

    public static void tap(MobileDriver<MobileElement> driver, MobileElement element, By ext) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, element, ext);
                element.findElement(ext).click();
                return;
            } catch (Exception e) {
                if (++count == TAP_RETRY)
                    throw e;
            }
        }
    }

    public static void tapFromTheListOfElements(MobileDriver<MobileElement> driver, By by, int number) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, by);
                //driver.findElements(by).get(number).click();
                return;
            } catch (Exception e) {
                if (++count == TAP_RETRY) {
                    throw e;
                }
                Loading.wait(1, TimeUnit.SECONDS);
            }
        }

    }

    public static void tapFromTheListOfElements(MobileDriver<MobileElement> driver, By by, String text) {
        int count = 0;

        while (true) {
            try {
                Loading.present(driver, by);
                List<MobileElement> elements = driver.findElements(by);
                elements.forEach(mobileElement -> {
                    if (mobileElement.getText().equalsIgnoreCase(text)) {
                        mobileElement.click();
                    }
                });
                return;
            } catch (Exception e) {
                if (++count == TAP_RETRY) {
                    throw e;
                }
                Loading.wait(1, TimeUnit.SECONDS);
            }
        }

    }

    public static void type(MobileDriver<MobileElement> driver, By by, String text) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, by);
                driver.findElement(by).clear();
                driver.findElement(by).sendKeys(text);
                return;
            } catch (Exception e) {
                if (++count == TYPE_RETRY)
                    throw e;
            }
        }

    }

    public static void type(MobileDriver<MobileElement> driver, MobileElement element, String text) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, element);
                element.clear();
                element.sendKeys(text);
                return;
            } catch (Exception e) {
                if (++count == TYPE_RETRY)
                    throw e;
            }
        }

    }

    public static void type(MobileDriver<MobileElement> driver, MobileElement element, By ext, String text) {
        int count = 0;

        while (true) {
            try {
                Loading.tappable(driver, element);
                Loading.tappable(driver, element.findElement(ext));
                element.findElement(ext).clear();
                element.findElement(ext).sendKeys(text);
                return;
            } catch (Exception e) {
                if (++count == TYPE_RETRY)
                    throw e;
            }
        }

    }

    public static String getText(MobileDriver<MobileElement> driver, By by) {
        int count = 0;

        while (true) {
            try {
                Loading.present(driver, by);
                return driver.findElement(by).getText();
            } catch (Exception e) {
                if (++count == TEXT_RETRY)
                    throw e;
            }
        }
    }

    public static String getText(MobileElement element) {
        int count = 0;

        while (true) {
            try {
                return element.getText();
            } catch (Exception e) {
                if (++count == TEXT_RETRY)
                    throw e;
            }
        }
    }

    public static String getText(MobileDriver<MobileElement> driver, MobileElement element, By ext) {
        int count = 0;

        while (true) {
            try {
                Loading.present(driver, element, ext);
                return element.findElement(ext).getText();
            } catch (Exception e) {
                if (++count == TEXT_RETRY)
                    throw e;
            }
        }
    }

    public static List<MobileElement> getListOfElements(MobileDriver<MobileElement> driver, By by) {
        int count = 0;

        while (true) {
            try {
                Loading.present(driver, by);
                return driver.findElements(by);
            } catch (Exception e) {
                if (++count == TEXT_RETRY)
                    throw e;
            }
        }
    }

    public static String isChecked(MobileDriver<MobileElement> driver, By by) {
        int count = 0;

        while (true) {
            try {
                Loading.present(driver, by);
                return driver.findElement(by).getAttribute("checked");
            } catch (Exception e) {
                if (++count == TEXT_RETRY)
                    throw e;
            }
        }
    }
}
