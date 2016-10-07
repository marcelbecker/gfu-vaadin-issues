package de.gfu.vaadin.authentication.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import de.gfu.vaadin.authentication.view.LoginView;
import de.gfu.vaadin.authentication.view.RegisterView;
import de.gfu.vaadin.persistence.DefaultDataSetup;
import de.gfu.vaadin.theme.MyTheme;

import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;

/**
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
@Widgetset("com.vaadin.DefaultWidgetSet")
public class AuthUI extends UI {

    static {
        DefaultDataSetup.withDefaultData();
    }

    @Override
    protected void init(VaadinRequest request) {

        Navigator navigator = new Navigator(this, this);
        navigator.addView("login", LoginView.class);
        navigator.addView("register", RegisterView.class);
        navigator.setErrorView(LoginView.class);

        setNavigator(navigator);

        addStyleName(VACATION_BACKGROUND);

        setContent(new LoginView());
    }

    @WebServlet(urlPatterns = "/auth/*", name = "AuthServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AuthUI.class, productionMode = false)
    public static class AuthUIServlet extends VaadinServlet {
    }

}
