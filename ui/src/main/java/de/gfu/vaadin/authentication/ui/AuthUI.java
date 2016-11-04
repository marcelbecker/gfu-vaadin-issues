package de.gfu.vaadin.authentication.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
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
@PreserveOnRefresh
public class AuthUI extends UI {

    static {
        DefaultDataSetup.withDefaultUser();
    }

    @Override
    protected void init(VaadinRequest request) {
        System.out.println("INIT");
        Navigator navigator = new Navigator(this, this);
        navigator.addView("login", LoginView.class);
        navigator.addView("register", RegisterView.class);
        navigator.setErrorView(LoginView.class);

        setNavigator(navigator);

        addStyleName(VACATION_BACKGROUND);

        Panel panel = new Panel();
        panel.addStyleName(MyTheme.CssClass.CENTER_PANEL);
        panel.setContent(new Label("Hier kommt noch was hin :)"));
        panel.setSizeUndefined();

        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        setContent(verticalLayout);
//        setContent(new LoginView());
    }

    @Override
    public void close() {
        super.close();
        System.out.println("CLOSE");
    }

    @Override
    public void detach() {
        super.detach();
        System.out.println("DETACH");
    }

    @Override
    protected void refresh(VaadinRequest request) {
        super.refresh(request);
        System.out.println("REFRESH");
    }

    @WebServlet(urlPatterns = "/login/*", name = "AuthServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AuthUI.class, productionMode = true, heartbeatInterval = 15)
    public static class AuthUIServlet extends VaadinServlet {
    }

}
