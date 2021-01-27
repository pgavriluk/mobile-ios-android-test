package screenfactory.screens;

import driver.DriverFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class SelectLuggageScreen {

    private final MobileDriver<MobileElement> driver;

    public SelectLuggageScreen(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Tap Done Luggage")
    public SearchScreen tapDoneLuggage() {
        if (DriverFactory.isIos()) {

        } else {

        }

        return new SearchScreen(driver);
    }

    @Step("Tap Back Luggage")
    public void tapBackLuggage() {

    }

    public SelectLuggageScreen selectLuggageSkis(int skis) {
        return selectLuggage(skis, 0, false);
    }

    public SelectLuggageScreen selectLuggageGolfBags(int golfBags) {
        return selectLuggage(0, golfBags, false);
    }

    public SelectLuggageScreen selectLuggage(int skis, int golfBags) {
        return selectLuggage(skis, golfBags, false);
    }

    @Step("Select Luggage - skis: '{0}', golfBags '{1}', oversizedLuggage '{2}'")
    public SelectLuggageScreen selectLuggage(int skis, int golfBags, boolean oversizedLuggage) {
        setSkis(skis);
        setGolfBags(golfBags);
        setOversizedLuggage(oversizedLuggage);

        tapDoneLuggage();

        return this;
    }

    private void setSkis(int skis) {

    }

    private void setGolfBags(int golfBags) {

    }

    private void setOversizedLuggage(boolean oversizedLuggage) {

    }

}
