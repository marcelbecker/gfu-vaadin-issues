package de.gfu.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.persistence.Database;
import de.gfu.vaadin.ui.views.ViewControllerInit;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.vaadin.DefaultWidgetSet")
public class MainUI extends UI {

    private Database database;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);

        database = new Database();
        User defaultUser = new User();
        defaultUser.setLoginName("mb");
        defaultUser.setPassword("mb");
        database.users.save(defaultUser);

        ViewControllerInit.initViewController(navigator);
    }

    public static Database getDatabase() {
        return get().database;
    }

    public static MainUI get() {
        return ((MainUI)UI.getCurrent());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
