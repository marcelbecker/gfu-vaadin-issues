package de.gfu.vaadin.extensions;

import com.google.gwt.user.client.ui.Label;

/**
 *
 * Created by MBecker on 06.10.2017.
 */
public class MyWidget extends Label {
    public static final String CLASSNAME = "mywidget";

    public MyWidget() {
        setStyleName(CLASSNAME);
        setText("This is MyWidget");
    }
}
