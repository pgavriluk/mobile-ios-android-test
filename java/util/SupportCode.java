package util;

import action.Actions;
import action.Scroll;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SupportCode {

    private final MobileDriver<MobileElement> driver;

    public SupportCode(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Select Api Environment '{0}'")
    public void selectApiEnv(String env) {
        System.out.println("Selecting env: " + env);
        By apiEnv = MobileBy.AccessibilityId(env);
        Scroll.scrollDownFromElement(driver, MobileBy.AccessibilityId("CORPORATE"));
        Actions.tap(driver, apiEnv);
        Actions.tap(driver, MobileBy.AccessibilityId("doneButton"));
    }
}
