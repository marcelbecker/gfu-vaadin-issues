package de.gfu.vaadin.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.LeadsService;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.LeadsGridComponent;
import de.gfu.vaadin.widgets.DisableOnClickButtonExtension;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;

/**
 *
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
@Push(PushMode.AUTOMATIC)
@Widgetset("de.gfu.vaadin.widgets.DisableOnClickButtonExtension")
public class LeadsUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

//        addStyleName(VACATION_BACKGROUND);
        final LeadsGridComponent leadsGridComponent =
                new LeadsGridComponent(new LeadsService());

        Panel panel = new Panel();
        panel.addStyleName(CENTER_PANEL);
        panel.setContent(leadsGridComponent);
        panel.setSizeUndefined();


        final Button detachButton = new Button("Detach", e -> {
            panel.setContent(null);
        });

        final Button extendMeButton = new Button("Extend me!");
        new DisableOnClickButtonExtension("Hallo Welt!", 13)
                .extend(extendMeButton);

        final Button gc = new Button("GC", e -> System.gc());

        final HorizontalLayout buttons =
                new HorizontalLayout(detachButton, gc, extendMeButton);
        buttons.setSizeUndefined();

        final VerticalLayout centralLayout = new VerticalLayout(buttons, panel);
        centralLayout.setWidth(80, Unit.PERCENTAGE);
        centralLayout.setExpandRatio(buttons, 0);
        centralLayout.setExpandRatio(panel, 10);

        VerticalLayout verticalLayout = new VerticalLayout(centralLayout);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(centralLayout, Alignment.MIDDLE_CENTER);

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

    @WebServlet(urlPatterns = "/leads/*", name = "LeadsServlet", asyncSupported = true
//    , initParams = @WebInitParam(name = "widgetset", value = "de.gfu.vaadin.widgets.DisableOnClickButtonExtension")
)
    @VaadinServletConfiguration(ui = LeadsUI.class, productionMode = true, heartbeatInterval = 5)
    public static class LeadsUIServlet extends VaadinServlet {
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.getSession().setMaxInactiveInterval(10);
            super.service(request, response);
        }
    }

    @WebServlet(urlPatterns = "/VAADIN/*", name = "VaadinBootstrapServlet")
    public static class VaadinBootstrapServlet extends VaadinServlet {}

}
