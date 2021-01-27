package driver;


import data.enums.Emulators;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.Configuration;
import util.EmulatorManager;
import utils.AllureEnvironmentWriter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class DriverFactory {

    private static final Object LOCK = new Object();
    private static final String RUNNING_MODE = Configuration.getProperty("RUNNING_MODE");
    private static final String PLATFORM = System.getenv("PLATFORM") != null ? System.getenv("PLATFORM") : Configuration.getProperty("PLATFORM");
    private static final String SAUCE_USERNAME = Configuration.getProperty("SAUCE_USERNAME");
    private static final String SAUCE_ACCESS_KEY = Configuration.getProperty("SAUCE_ACCESS_KEY");
    private static String platformVersion;
    private static final String HTTP = "http://";
    private static final String WD_HUB = "/wd/hub";
    private static final String SAUCELABS_URL = "http://%s:%s@ondemand.saucelabs.com/wd/hub";
    private static final String DEFAULT_APPIUM_SERVER = "127.0.0.1:4723";
    @Getter
    private static final String appPackage = Configuration.getProperty("APP_PACKAGE");
    @Getter
    private static boolean isIos = false;

    @NotNull
    public static MobileDriver<MobileElement> getNewDriver() throws MalformedURLException {
        synchronized (LOCK) {
            String emulator = EmulatorManager.getEmulator();

            if (RUNNING_MODE.equalsIgnoreCase("sauce")) {
                return getSauceDriver(emulator);
            } else {
                return getLocalDriver(emulator);
            }
        }
    }

    @NotNull
    private static MobileDriver<MobileElement> getSauceDriver(String emulator) throws MalformedURLException {
        DesiredCapabilities caps;
        String appiumServer = String.format(SAUCELABS_URL, SAUCE_USERNAME, SAUCE_ACCESS_KEY);
        if (PLATFORM.equalsIgnoreCase("ios")) {
            isIos = true;
            platformVersion = System.getenv("PLATFORM_VERSION") != null ? System.getenv("PLATFORM_VERSION") : Configuration.getProperty("PLATFORM_VERSION_IOS");
            caps = getIosSauceLabsCapabilities(emulator);
            System.out.println("Running on: '" + emulator + "' with Operating System: " + platformVersion);
            addEnvPropertiesToAllureReport(emulator);
            return new IOSDriver<>(new URL(appiumServer), caps);
        } else {
            platformVersion = System.getenv("PLATFORM_VERSION") != null ? System.getenv("PLATFORM_VERSION") : Configuration.getProperty("PLATFORM_VERSION_ANDROID");
            caps = getAndroidSauceLabsCapabilities(emulator);
            System.out.println("Running on: '" + emulator + "' with Operating System: " + platformVersion);
            addEnvPropertiesToAllureReport(emulator);
            return new AndroidDriver<>(new URL(appiumServer), caps);
        }
    }

    @NotNull
    private static MobileDriver<MobileElement> getLocalDriver(String emulator) throws MalformedURLException {
        DesiredCapabilities caps;
        String appiumServer = HTTP + getAppiumServer() + WD_HUB;
        if (PLATFORM.equalsIgnoreCase("ios")) {
            isIos = true;
            caps = getLocalCapabilities(emulator);
            addEnvPropertiesToAllureReport(emulator);
            return new IOSDriver<>(new URL(appiumServer), caps);
        } else {
            caps = getLocalCapabilities(emulator);
            addEnvPropertiesToAllureReport(emulator);
            return new AndroidDriver<>(new URL(appiumServer), caps);
        }
    }

    private static String getAppiumServer() {
        String appiumServer = System.getenv("APPIUM_SERVER");

        if (appiumServer == null) {
            log.warn("APPIUM_SERVER param is not set. Using a default APPIUM_SERVER: " + DEFAULT_APPIUM_SERVER);
            appiumServer = DEFAULT_APPIUM_SERVER;
        } else {
            log.warn("APPIUM_SERVER is: " + appiumServer);
        }

        return appiumServer;
    }

    private static DesiredCapabilities getLocalCapabilities(String emulator) {
        String appLocation = Configuration.getProperty("APP_PATH");

        File app = new File(appLocation);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        System.out.println("Running with emulator: " + emulator);

        capabilities.setCapability(MobileCapabilityType.UDID, emulator.split(":")[1]);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulator.split(":")[0]);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("appPackage", Configuration.getProperty("APP_PACKAGE"));
        capabilities.setCapability("appActivity", Configuration.getProperty("APP_ACTIVITY"));
        capabilities.setCapability("emulator", emulator.split(":")[0]);

        // Prevent UIAutomator from ending on scroll
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        return capabilities;
    }

    private static DesiredCapabilities getAndroidSauceLabsCapabilities(String emulator) {
        String deviceEnum = emulator.replace(" ", "_").toUpperCase().trim();

        assertTrue(EnumUtils.isValidEnum(Emulators.class, deviceEnum), "Illegal/Unsupported Emulator: '" + emulator + "'");
        if (!Emulators.valueOf(deviceEnum).getOperatingSystems().contains(platformVersion.trim())) {
            platformVersion = Emulators.valueOf(deviceEnum).getOperatingSystems().get(Emulators.valueOf(deviceEnum).getOperatingSystems().size() - 1);
        }

        DesiredCapabilities caps = DesiredCapabilities.android();

        caps.setCapability(MobileCapabilityType.APPIUM_VERSION, Configuration.getProperty("APPIUM_VERSION_ANDROID"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, emulator);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
        caps.setCapability(MobileCapabilityType.APP, Configuration.getProperty("SAUCE_APP_ANDROID"));
        caps.setCapability("deviceOrientation", Configuration.getProperty("DEVICE_ORIENTATION"));
        caps.setCapability("browserName", "");
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", Configuration.getProperty("APP_ACTIVITY"));
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("useJSONSource", true);

        return caps;
    }

    private static DesiredCapabilities getIosSauceLabsCapabilities(String emulator) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, Configuration.getProperty("APPIUM_VERSION_IOS"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulator);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.APP, Configuration.getProperty("SAUCE_APP_IOS"));
        capabilities.setCapability("deviceOrientation", Configuration.getProperty("DEVICE_ORIENTATION"));
        capabilities.setCapability("build", "WheelsUp_iOS_Local_Test");
        capabilities.setCapability("timeZone", "New_York");

        /* A Selenium crash might cause a session to hang indefinitely.
         * Below is the max time allowed to wait for a Selenium command*/
        capabilities.setCapability("maxDuration", 4200);
        capabilities.setCapability("commandTimeout", 300);

        /* How long can the browser wait for a new command */
        capabilities.setCapability("idleTimeout", 90);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        return capabilities;
    }

    public static void setSauceLabsJobName(MobileDriver<MobileElement> driver, String name) {
        if (RUNNING_MODE.equalsIgnoreCase("sauce")) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + name);
        }
    }

    public static void setSauceLabsJobStatus(MobileDriver<MobileElement> driver, boolean isFailed) {
        if (RUNNING_MODE.equalsIgnoreCase("sauce")) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (isFailed ? "failed" : "passed"));
        }
    }

    public static String getSessionId(MobileDriver<MobileElement> driver) {
        return ((AppiumDriver<MobileElement>) driver).getSessionId().toString();
    }

    private static void addEnvPropertiesToAllureReport(String emulator) {
        AllureEnvironmentWriter.addValue("Platform", PLATFORM);
        AllureEnvironmentWriter.addValue("Emulator", emulator);
        AllureEnvironmentWriter.addValue("Platform Version", platformVersion);
        AllureEnvironmentWriter.writeToFile();
    }
}
