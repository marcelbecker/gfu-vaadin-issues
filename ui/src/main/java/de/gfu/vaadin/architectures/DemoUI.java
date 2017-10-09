package de.gfu.vaadin.architectures;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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

//        final de.gfu.vaadin.architectures.passiveview.MeterView passiveView =
//                new de.gfu.vaadin.architectures.passiveview.MeterView();
//
//        final de.gfu.vaadin.architectures.supervisingcontroller.MeterView supervisingController =
//                new de.gfu.vaadin.architectures.supervisingcontroller.MeterView();

        final MeterView naiveView = new MeterView();

        final Panel panel = new Panel("Zählerstände");
        panel.setContent(naiveView);
        panel.addStyleName(MyTheme.CssClass.CENTER_PANEL);
        panel.setSizeUndefined();
        panel.setHeight(100, Unit.PERCENTAGE);

        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }


    @WebServlet(urlPatterns = "/architectures/*", name = "DemoServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoUI.class, productionMode = false, heartbeatInterval = 300)
    public static class DemoServlet extends VaadinServlet {}


}
