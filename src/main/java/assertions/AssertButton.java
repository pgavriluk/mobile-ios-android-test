package assertions;

import action.Loading;
import action.Scroll;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertButton {

    private static final String BUTTON_WITH_TEXT_XPATH = "//*[contains(@text, '%s')]";

    @Step("Assert Button If exists")
    public void assertButtonIfExists(MobileDriver<MobileElement> driver, String buttonName) {
        By by = MobileBy.xpath(String.format(BUTTON_WITH_TEXT_XPATH, buttonName));

        assertNotNull(Loading.visible(driver, by));
    }

    @Step("Scroll And Assert Button If Exists")
    public void scrollAndAssertButtonIfExists(MobileDriver<MobileElement> driver, String buttonName) {
        By by = MobileBy.xpath(String.format(BUTTON_WITH_TEXT_XPATH, buttonName));
        Scroll.scrollTextIntoView(driver, buttonName);

        assertNotNull(Loading.visible(driver, by));
    }
}
