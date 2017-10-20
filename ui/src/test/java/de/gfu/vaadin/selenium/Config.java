package de.gfu.vaadin.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.net.URISyntaxException;

/**
 *
 * Created by MBecker on 07.10.2017.
 */
public class Config {

    public static final String BASE_URL = "http://localhost:38080/";
    public static final FirefoxSetup firefoxSetup = new FirefoxSetup();

    public static class FirefoxSetup {
        static {
            // Hier wird der Gecko-Driver für Firefox referenziert.
            System.setProperty("webdriver.gecko.driver",
                    absolutePath("/drivers/mozilla/0.18-win32/geckodriver.exe"));
        }

        public FirefoxDriver newDriver() {
            return new FirefoxDriver();
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
