package de.gfu.vaadin.selenium.vaadin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;

import static de.gfu.vaadin.selenium.tools.SeleniumTools.waiting;
import static java.util.Optional.ofNullable;

/**
 *
 * Created by MBecker on 07.10.2017.
 */
public class ComboBox extends RemoteWebElement {

    private final RemoteWebDriver driver;
    private final By by;

    public ComboBox(RemoteWebDriver driver) {
        this.driver = driver;
        this.by = null;
    }

    public ComboBox(RemoteWebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    public void select(String text) {

        for (WebElement elem : findItems()) {
            if (elem.getText().contains(text)) {
                elem.click();
                break;
            }
        }

    }

    public void select(int index) {

        int counter = 1;

        for (WebElement elem : findItems()) {
            if (index == counter++) {
                elem.click();
                break;
            }
        }

    }

    private List<WebElement> findItems() {
        final By toFind = ofNullable(this.by).orElse(By.className("v-filterselect"));

        waiting(driver).until(toFind::findElement)
                .findElement(By.className("v-filterselect-button"))
                .click();

        return waiting(driver).until(d -> d.findElement(By.cssSelector(".v-filterselect-suggestmenu")))
                .findElements(By.cssSelector(".gwt-MenuItem"));
    }

}
