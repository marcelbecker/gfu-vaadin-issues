package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.Navigator;

/**
 * Created by mbecker on 29.07.2016.
 */
public class ViewControllerInit {

    public static void initViewController(Navigator navigator) {
        ViewController.registerViews(navigator);
    }
}
