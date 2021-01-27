package assertions;

import action.Actions;
import action.Loading;
import action.Scroll;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertText {

    private static final String TEXT_XPATH = "//android.widget.TextView[contains(@text, \"%s\")]";

    @Step("Assert That Text Displays")
    public void assertThatTextDisplays(MobileDriver<MobileElement> driver, String text) {
        Allure.step("Assert That Text Displays: " + text);
        By by = MobileBy.xpath(String.format(TEXT_XPATH, text));
        Scroll.scrollTextIntoView(driver, text, true);
        Loading.visible(driver, by);

        assertTrue(Actions.getText(driver, by).trim().contains(text));
    }

}
