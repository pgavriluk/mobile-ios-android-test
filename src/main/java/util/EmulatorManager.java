package util;

import lombok.Getter;

import java.util.concurrent.BlockingQueue;

public class EmulatorManager {

    private static final String RUNNING_MODE = Configuration.getProperty("RUNNING_MODE");
    private static final String PLATFORM = System.getenv("PLATFORM") != null ? System.getenv("PLATFORM") : Configuration.getProperty("PLATFORM");
    @Getter
    private static BlockingQueue<String> emulatorQueue = null;
    private static boolean isFirst = true;

    public static String getEmulator() {
        if (RUNNING_MODE == null || !RUNNING_MODE.equalsIgnoreCase("sauce")) {
            if(isFirst){
                emulatorQueue = Configuration.getListOfEmulators();
                isFirst = false;
            }
            String emulator = emulatorQueue.poll();
            while (emulator == null) {
                emulator = emulatorQueue.poll();
                try {
                    Thread.sleep(10000);
                    System.out.println("Waiting for the first released emulator...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return emulator;
        } else {
            if (PLATFORM.equalsIgnoreCase("ios")) {
                return System.getenv("SAUCE_DEVICE_NAME") != null ? System.getenv("SAUCE_DEVICE_NAME") : Configuration.getProperty("SAUCE_DEVICE_NAME_IOS");
            } else {
                return System.getenv("SAUCE_DEVICE_NAME") != null ? System.getenv("SAUCE_DEVICE_NAME") : Configuration.getProperty("SAUCE_DEVICE_NAME_ANDROID");
            }
        }
    }
}
