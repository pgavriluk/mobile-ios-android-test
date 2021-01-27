package screenfactory.screens;

import driver.DriverFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class SelectPassengersScreen {

    private final MobileDriver<MobileElement> driver;

    public SelectPassengersScreen(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Tap Next Passengers")
    public SelectLuggageScreen tapNextPassengers() {
        if (DriverFactory.isIos()) {

        } else {

        }

        return new SelectLuggageScreen(driver);
    }

    public SelectPassengersScreen selectPassengers(int adults) {
        return selectPassengers(adults, 0, 0, 0);
    }

    public SelectPassengersScreen selectPassengers(int adults, int children) {
        return selectPassengers(adults, children, 0, 0);
    }

    public SelectPassengersScreen selectPassengers(int adults, int children, int infants) {
        return selectPassengers(adults, children, infants, 0);
    }

    @Step("Select Passengers - adults: '{0}', children: '{1}', infants: '{2}', pets: '{3}'")
    public SelectPassengersScreen selectPassengers(int adults, int children, int infants, int pets) {
        if (DriverFactory.isIos()) {

        } else {

        }

        return this;
    }

    private void setAdults(int adults) {

    }

    private void setChildren(int children) {

    }

    private void setInfants(int infants) {

    }

    private void setPets(int pets) {

    }

}
