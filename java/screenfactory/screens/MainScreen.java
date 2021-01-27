package screenfactory.screens;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainScreen {

    private final MobileDriver<MobileElement> driver;
    private static final By SEARCH_FLIGHTS = MobileBy.id(DriverFactory.getAppPackage() + ":id/button1");


    public MainScreen(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Tap Search Flights Button")
    public SearchScreen tapSearchFlightsButton() {
        if (DriverFactory.isIos()) {

        } else {

        }

        return new SearchScreen(driver);
    }

    @Step("Tap The Hamburger Menu Icon")
    public void tapTheHamburgerMenuIcon() {

    }


}
