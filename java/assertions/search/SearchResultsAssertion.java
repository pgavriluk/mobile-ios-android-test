package assertions.search;

import action.Scroll;
import assertions.AssertButton;
import assertions.AssertText;
import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchResultsAssertion {

    private final MobileDriver<MobileElement> driver;

    public SearchResultsAssertion(MobileDriver<MobileElement> driver) {
        this.driver = driver;
    }

    @Step("Assert 'Search Results' Text")
    public void assertSearchResultsText() {
        if (DriverFactory.isIos()) {
            assertEquals("Search Results", driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Search Results'`]")).getText());
        } else {
            new AssertText().assertThatTextDisplays(driver, "Search Results");
        }
    }

    @Step("Assert Search Results Button '{0}'")
    public void assertSearchResultsButton(String buttonName) {
        if (DriverFactory.isIos()) {
            Scroll.scrollForward(driver);
            assertEquals(buttonName, driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == '" + buttonName + "'`]")).getText());
        } else {
            new AssertButton().scrollAndAssertButtonIfExists(driver, buttonName);
        }
    }
}
