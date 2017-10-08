package de.gfu.vaadin.selenium.vaadin;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static de.gfu.vaadin.selenium.tools.SeleniumTools.waiting;

/**
 *
 * Created by MBecker on 07.10.2017.
 */
public class Label {


    private final RemoteWebDriver driver;
    private final By by;

    public Label(RemoteWebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    public String getText() {
        return waiting(driver).until(d -> d.findElement(by)).getText();
    }

}
