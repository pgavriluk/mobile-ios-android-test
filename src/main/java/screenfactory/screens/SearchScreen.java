package screenfactory.screens;

import driver.DriverFactory;
import enums.Airport;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SearchScreen {

    private final MobileDriver<MobileElement> driver;

    private static final By ONE_WAY = MobileBy.id(DriverFactory.getAppPackage() + ":id/oneWay");

    public SearchScreen(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }


    @Step("Tap One-Way")
    public void tapOneWay() {

    }

    @Step("Tap Round-Trip")
    public void tapRoundTrip() {

    }

    @Step("Tap Multi-Leg")
    public void tapMultiLeg() {

    }

    @Step("Tap One Leg Departure")
    public SearchScreen tapOneLegDeparture() {
        if (DriverFactory.isIos()) {

        } else {

        }

        return this;
    }

    @Step("Tap Round Trip Departure")
    public void tapRoundTripDeparture() {

    }

    @Step("Tap Multi-Leg Departure")
    public void tapMultiLegDeparture() {

    }

    @Step("Search Flights - Tap Add Leg")
    public void tapAddLeg() {

    }

    @Step("Search Flights - Tap Return Date")
    public void tapReturn() {

    }

    @Step("Search Flights - Tap One Leg Arrival")
    public void tapOneLegArrival() {

    }

    @Step("Search Flights - Tap Round Trip Arrival")
    public void tapRoundTripArrival() {

    }

    @Step("Search Flights - Tap Multi-Leg Arrival")
    public void tapMultiLegArrival() {

    }

    @Step("Search Flights - Tap Date")
    public void tapDate() {

    }

    @Step("Search Flights - Tap Round Trip Departure Date")
    public void tapRoundTripDepartureDate() {

    }

    @Step("Search Flights - Tap Multi-Leg Date")
    public void tapMultiLegDate() {

    }

    @Step("Search Flights - Tap Round Trip Return Date")
    public void tapRoundTripReturnDate() {

    }

    @Step("Search Flights - Tap Time")
    public void tapTime() {

    }

    @Step("Search Flights - Tap Passenger")
    public void tapPassenger() {

    }

    @Step("Search Flights - Tap Luggage")
    public void tapLuggage() {

    }

    @Step("Tap Search")
    public SearchScreen tapSearch() {
        if (DriverFactory.isIos()) {

        } else {

        }

        return this;
    }

    @Step("Search Flights - Tap Multi Leg Search")
    public void tapMultiLegSearch() {

    }

    @Step("Search Flights - Tap OK")
    public void tapOk() {
        if (DriverFactory.isIos()) {

        } else {

        }
    }

    @Step("Set Airport '{0}'")
    public SearchScreen setAirport(Airport airport) {
        if (DriverFactory.isIos()) {

        } else {

        }

        return this;
    }

}
