package de.gfu.vaadin.ui.uis;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import de.gfu.vaadin.ui.views.ViewController;

import javax.servlet.annotation.WebServlet;

/**
 * Created by mbecker on 23.09.2016.
 */
@Theme("mytheme")
@Widgetset("com.vaadin.DefaultWidgetSet")
public class GuestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator = new Navigator(this, this);
        ViewController.registerViewsAt(navigator);

        setReadOnly(true);
    }

    @WebServlet(urlPatterns = "/*", name = "GuestServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GuestUI.class, productionMode = false)
    public static class GuestServlet extends VaadinServlet {

    }
}
