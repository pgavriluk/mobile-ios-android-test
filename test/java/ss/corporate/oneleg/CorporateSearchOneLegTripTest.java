package ss.corporate.oneleg;

import api.AcceptTerms;
import api.CreateMember;
import assertions.search.SearchResultsAssertion;
import driver.DriverFactory;
import enums.AccountType;
import enums.Airport;
import global.entity.Member;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import runtime.CustomAfterTestExecution;
import runtime.CustomBeforeTestExecution;
import screenfactory.screens.CalendarScreen;
import screenfactory.screens.LoginScreen;
import screenfactory.screens.SearchScreen;
import util.Configuration;
import util.SupportCode;

import java.net.MalformedURLException;

@Tag("ss")
@Feature("Single Search")
@Story("Corporate Search One Leg Trip Test")
@ExtendWith({CustomBeforeTestExecution.class, CustomAfterTestExecution.class})
public class CorporateSearchOneLegTripTest {

    private static MobileDriver<MobileElement> driver;
    private final CreateMember createMember = new CreateMember();
    private final AcceptTerms acceptTerms = new AcceptTerms();
    private LoginScreen loginScreen;
    private Member member;


    @BeforeEach
    public void beforeEachTest() throws MalformedURLException {
        driver = DriverFactory.getNewDriver();
        driver.closeApp();
        driver.launchApp();

        if (DriverFactory.isIos()) {
            SupportCode supportCode = new SupportCode(driver);
            supportCode.selectApiEnv("STAGINGMODE");
        } else {
            //setEnvInApp();
        }

        member = createMember.createMember(AccountType.CORPORATE);
        acceptTerms.acceptTerms(member);
        acceptTerms.acceptGDPR(member);

        loginScreen = new LoginScreen(driver);
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            try {
                driver.removeApp(Configuration.getProperty("APP_PACKAGE"));
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Corporate Search One Leg Trip KTEB To KBOS Test")
    public void corporateSearchOneLegTripKTEBToKBOSTest() {
        SearchScreen searchScreen = loginScreen.typeEmail(member.getEmail())
                .typePassword(member.getPassword())
                .tapSignIn()
                .tapSearchFlightsButton();

        searchScreen.tapOneLegDeparture()
                .setAirport(Airport.KTEB)
                .setAirport(Airport.KBOS);

        CalendarScreen calendarScreen = new CalendarScreen(driver);
        calendarScreen.chooseDepartureDate()
                .selectPassengers(2)
                .tapNextPassengers()
                .tapDoneLuggage()
                .tapSearch()
                .tapOk();


        SearchResultsAssertion searchResultsAssertion = new SearchResultsAssertion(driver);
        searchResultsAssertion.assertSearchResultsText();
        searchResultsAssertion.assertSearchResultsButton("HOT FLIGHTS");
        searchResultsAssertion.assertSearchResultsButton("SHARED FLIGHTS");
    }

}
