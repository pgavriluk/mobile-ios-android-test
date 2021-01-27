package screenfactory.screens;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginScreen {

    private final MobileDriver<MobileElement> driver;

    private static final By EMAIL = MobileBy.xpath("//*[contains(@text, 'Email')]");


    public LoginScreen(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Type Email '{0}'")
    public LoginScreen typeEmail(String email) {
        if (DriverFactory.isIos()) {
            //Actions.type(driver, MobileBy.AccessibilityId("login_email"), email);
        } else {
            //Actions.type(driver, EMAIL, email);
        }

        return this;
    }

    @Step("Type Password '{0}'")
    public LoginScreen typePassword(String password) {
        if (DriverFactory.isIos()) {

        } else {

        }

        return this;
    }


    @Step("Tap Sign In Button")
    public MainScreen tapSignIn() {
        if (DriverFactory.isIos()) {

        } else {

        }

        return new MainScreen(driver);
    }

}
