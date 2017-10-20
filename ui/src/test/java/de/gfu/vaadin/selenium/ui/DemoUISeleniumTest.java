package de.gfu.vaadin.selenium.ui;

import de.gfu.vaadin.selenium.Config;
import de.gfu.vaadin.selenium.pages.DemoPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

/**
 *
 * Created by MBecker on 07.10.2017.
 */
public class DemoUISeleniumTest {

    private static FirefoxDriver driver;

    @Test
    public void test() throws Exception {


        // todo: Hierhin kommt der Test


    }

    @BeforeClass
    public static void setUpDriver() throws Exception {
        driver = Config.firefoxSetup.newDriver();
    }

    @AfterClass
    public static void tearDownDriver() throws Exception {
        driver.close();
    }
}
