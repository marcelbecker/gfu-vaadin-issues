package de.gfu.vaadin.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Entry point for configuration properties.
 *
 *
 * Created by MBecker on 07.10.2017.
 */
public class Config {


    public static final FirefoxSetup firefoxSetup = new FirefoxSetup();


    static final String BASE_URL = "http://localhost:38080/";


    /**
     * Firefox setup, provides a working {@link FirefoxDriver}, ready for use in
     * tests. Tests are responsible for closing the driver.
     */
    public static class FirefoxSetup {
        static {
            // Hier wird der Gecko-Driver für Firefox referenziert.
            System.setProperty("webdriver.gecko.driver",
                    absolutePath("/drivers/mozilla/0.18-win32/geckodriver.exe"));
        }

        public FirefoxDriver newDriver() {
            final FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.get(BASE_URL);
            return firefoxDriver;
        }
    }


    static String absolutePath(String relativePath) {
        try {
            return new File(Config.class
                    .getResource(relativePath).toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
