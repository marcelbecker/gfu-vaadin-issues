package de.gfu.vaadin.selenium.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * Created by MBecker on 07.10.2017.
 */
public class SeleniumTools {

    public static WebDriverWait waiting(WebDriver driver) {
        return new WebDriverWait(driver, 10);
    }

}
