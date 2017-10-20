package de.gfu.vaadin.selenium.pages;

import de.gfu.vaadin.selenium.vaadin.ComboBox;
import de.gfu.vaadin.selenium.vaadin.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * Created by MBecker on 07.10.2017.
 */
public class DemoPage {

    private static final String URL = "demo";

    private By earlyLearningsComboBox = By.id("early-learnings");
    private By earlyLearningLabel = By.id("early-learning-label");

    private final RemoteWebDriver driver;


    public DemoPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public ComboBox getEarlyLearningsComboBox() {
        return new ComboBox(driver, earlyLearningsComboBox);
    }

    public Label getLabel() {
        return new Label(driver, earlyLearningLabel);
    }

    public DemoPage navigateTo() {
        driver.navigate().to(driver.getCurrentUrl() + URL);
        return this;
    }
}
