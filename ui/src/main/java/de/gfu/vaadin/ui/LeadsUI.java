package de.gfu.vaadin.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.LeadsService;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.LeadsGridComponent;

import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;

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
        final LeadsGridComponent leadsGridComponent =
                new LeadsGridComponent(new LeadsService());

        Panel panel = new Panel();
        panel.addStyleName(CENTER_PANEL);
        panel.setContent(leadsGridComponent);
        panel.setHeight(80, Unit.PERCENTAGE);
        panel.setWidth(80, Unit.PERCENTAGE);

        VerticalLayout verticalLayout = new VerticalLayout(panel);

        final Button detachButton = new Button("Detach", e -> {
            panel.setContent(null);
        });

        final Button gc = new Button("GC", e -> System.gc());

        verticalLayout.addComponents(detachButton, gc);

        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    @Override
    public void close() {
        super.close();
        System.out.println("closing " + this);
    }

    @Override
    public void detach() {
        super.detach();
        System.out.println("detaching " + this);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalizing " + this);
    }

    @WebServlet(urlPatterns = "/leads/*", name = "LeadsServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LeadsUI.class, productionMode = true, heartbeatInterval = 5)
    public static class LeadsUIServlet extends VaadinServlet {}

    @WebServlet(urlPatterns = "/VAADIN/*", name = "VaadinBootstrapServlet")
    public static class VaadinBootstrapServlet extends VaadinServlet {}

}
