package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Configuration {

    private static final String FILE_LOCATION = "src/main/resources/config.properties";
    private static Properties prop = new Properties();

    public static synchronized String getProperty(String key) {
        if (prop.size() == 0) {
            InputStream input = null;
            try {
                input = new FileInputStream(FILE_LOCATION);

                prop.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return prop.getProperty(key);
    }

    public static synchronized BlockingQueue<String> getListOfEmulators() {
        InputStream input = null;
        try {
            input = new FileInputStream(FILE_LOCATION);

            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String emulatorStr = prop.getProperty("ANDROID_EMULATORS");

        return new LinkedBlockingQueue<>(Arrays.asList(emulatorStr.split(";")));

    }
}
