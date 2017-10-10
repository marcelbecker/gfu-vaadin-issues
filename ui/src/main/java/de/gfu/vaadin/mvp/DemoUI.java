package de.gfu.vaadin.mvp;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.theme.MyTheme;

import javax.servlet.annotation.WebServlet;

/**
 *
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
public class DemoUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        final User user = new User();
        final UserFormComponent content = new UserFormComponent(user, new Backend());
        final UserDisplay userDisplay = new UserDisplay(user);

        VerticalLayout verticalLayout = new VerticalLayout(content, userDisplay, new MobileTextField());
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }


    @WebServlet(urlPatterns = "/mvp/*", name = "DemoServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoUI.class, productionMode = false, heartbeatInterval = 300)
    public static class DemoServlet extends VaadinServlet {}


}
