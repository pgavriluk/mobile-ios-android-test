package screenfactory.screens;

import action.Scroll;
import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

public class CalendarScreen {

    private final MobileDriver<MobileElement> driver;
    private static final By CALENDAR = MobileBy.className("androidx.recyclerview.widget.RecyclerView");

    public CalendarScreen(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Choose Departure Date")
    public SelectPassengersScreen chooseDepartureDate() {
        if (DriverFactory.isIos()) {
            return chooseIosDepartureDate();
        } else {
            return chooseAndroidDepartureDate();
        }
    }

    public SelectPassengersScreen chooseIosDepartureDate() {
        Scroll.scrollDownHalfScreen(driver);

        return new SelectPassengersScreen(driver);
    }

    @NotNull
    private SelectPassengersScreen chooseAndroidDepartureDate() {

        return new SelectPassengersScreen(driver);
    }

    @Step("Choose Return Date")
    public void chooseReturnDate() {

    }

    @Step("Tap Next")
    private void tapNext() {

    }

}
