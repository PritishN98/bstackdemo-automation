package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read configuration settings from config.properties.
 */
public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Could not load config.properties, using fallback defaults: " + e.getMessage());
            properties = new Properties();
            properties.setProperty("url", "https://bstackdemo.com/");
            properties.setProperty("browser", "chrome");
            properties.setProperty("headless", "false");
            properties.setProperty("implicitWait", "10");
            properties.setProperty("explicitWait", "15");
            properties.setProperty("validUsername", "demouser");
            properties.setProperty("validPassword", "testingisfun99");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getUrl() {
        return properties.getProperty("url", "https://bstackdemo.com/");
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicitWait", "15"));
    }

    public static String getValidUsername() {
        return properties.getProperty("validUsername", "demouser");
    }

    public static String getValidPassword() {
        return properties.getProperty("validPassword", "testingisfun99");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalidUsername", "invaliduser");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalidPassword", "wrongpass123");
    }
}
