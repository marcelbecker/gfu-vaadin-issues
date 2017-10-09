package de.gfu.vaadin.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.*;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.LeadsGridComponent;

import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;
import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;

/**
 *
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
@Push(PushMode.AUTOMATIC)
public class LeadsUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

//        addStyleName(VACATION_BACKGROUND);

        Panel panel = new Panel();
        panel.addStyleName(CENTER_PANEL);
        panel.setContent(new LeadsGridComponent());
        panel.setSizeUndefined();

        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    @WebServlet(urlPatterns = "/leads/*", name = "LeadsServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LeadsUI.class, productionMode = true)
    public static class LeadsUIServlet extends VaadinServlet {}

    @WebServlet(urlPatterns = "/VAADIN/*", name = "VaadinBootstrapServlet")
    public static class VaadinBootstrapServlet extends VaadinServlet {}

}
