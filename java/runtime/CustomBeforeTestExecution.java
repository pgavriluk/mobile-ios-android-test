package runtime;

import driver.DriverFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;

public class CustomBeforeTestExecution implements BeforeTestExecutionCallback {

    private static final Object LOCK = new Object();

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        synchronized (LOCK) {
            Object test = extensionContext.getRequiredTestInstance();
            Field field = test.getClass().getDeclaredField("driver");
            field.setAccessible(true);
            MobileDriver<MobileElement> driver = (MobileDriver) field.get(test);

            DriverFactory.setSauceLabsJobName(driver, extensionContext.getDisplayName() + " - " + (DriverFactory.isIos() ? "iOS" : "Android"));
        }
    }
}
