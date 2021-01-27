package runtime;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import util.Configuration;
import util.EmulatorManager;
import util.ScreenshotUtil;

import java.lang.reflect.Field;

public class CustomAfterTestExecution implements AfterTestExecutionCallback {

    private static final Object LOCK = new Object();

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        synchronized (LOCK) {
            Object test = extensionContext.getRequiredTestInstance();
            Field field = test.getClass().getDeclaredField("driver");
            field.setAccessible(true);
            MobileDriver<MobileElement> driver = (MobileDriver) field.get(test);

            if (extensionContext.getExecutionException().isPresent()) {
                ScreenshotUtil.screenshot(driver, extensionContext.getRequiredTestMethod().getName());
            }

            if (Configuration.getProperty("RUNNING_MODE").equalsIgnoreCase("sauce")) {
                DriverFactory.setSauceLabsJobName(driver, extensionContext.getDisplayName() + " - " + (DriverFactory.isIos() ? "iOS" : "Android"));
                DriverFactory.setSauceLabsJobStatus(driver, extensionContext.getExecutionException().isPresent());
            } else {
                String emulator = ((AppiumDriver<MobileElement>) driver).getCapabilities().getCapability("emulator") + ":" +
                        ((AppiumDriver<MobileElement>) driver).getCapabilities().getCapability("udid");
                EmulatorManager.getEmulatorQueue().add(emulator);
            }
        }
    }
}
