package de.gfu.vaadin.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.gfu.vaadin.theme.MyTheme;

import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;
import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;

/**
 *
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
public class LoginUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        addStyleName(VACATION_BACKGROUND);

        Panel panel = new Panel();
        panel.addStyleName(CENTER_PANEL);
        panel.setContent(new Label("Hier kommt noch was hin :)"));
        panel.setSizeUndefined();

        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    @WebServlet(urlPatterns = "/login/*", name = "LoginServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = true, heartbeatInterval = 15)
    public static class LoginUIServlet extends VaadinServlet {}

    @WebServlet(urlPatterns = "/VAADIN/*", name = "VaadinBootstrapServlet")
    public static class VaadinBootstrapServlet extends VaadinServlet {}

}
