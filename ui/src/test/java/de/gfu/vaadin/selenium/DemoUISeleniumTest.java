package de.gfu.vaadin.selenium;

import de.gfu.vaadin.selenium.pages.DemoPage;
import org.junit.*;
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

        final DemoPage demoPage = new DemoPage(driver)
                .navigateTo();

        assertEquals("", demoPage.getLabel().getText());

        demoPage.getEarlyLearningsComboBox().select("Katze");

        assertEquals("Katze", demoPage.getLabel().getText());

    }

    @BeforeClass
    public static void setUpDriver() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C://Tools//Selenium//Mozilla//0.18-win32//geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void tearDownDriver() throws Exception {
        driver.close();
    }
}
