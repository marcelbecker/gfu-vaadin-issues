package de.gfu.vaadin.ui.uis;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Page;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.layout.MainLayout;
import de.gfu.vaadin.ui.views.ViewController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.application.SessionObjects.currentUser;
import static de.gfu.vaadin.ui.uis.MediaQueryActivation.DEFAULT_VIEWPORT_CONFIGURATION;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme(MyTheme.NAME)
@Viewport(DEFAULT_VIEWPORT_CONFIGURATION)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        if (! currentUser().isPresent()) {
            Page.getCurrent().setLocation("/login/");
            close();
            return;
        }


        /**
         * Initialize the main layout.
         */
        MainLayout mainLayout = new MainLayout();
        setContent(mainLayout);


        /**
         * Initialize the navigator.
         */
        Navigator navigator = new Navigator(this, (ViewDisplay) mainLayout);
        setNavigator(navigator);


        ViewController.registerViewsAt(navigator);

    }

    public static MainUI mainUI() {
        return ((MainUI)UI.getCurrent());
    }

    @WebServlet(urlPatterns = "/app/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();
//            getService().addSessionInitListener(this::onSessionInit);
        }

        private void onSessionInit(SessionInitEvent sessionInitEvent) {
            sessionInitEvent.getSession().addBootstrapListener(new MediaQueryActivation());
        }
    }
}
