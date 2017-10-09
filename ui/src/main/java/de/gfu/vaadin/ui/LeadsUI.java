package de.gfu.vaadin.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.LeadsService;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.LeadsGridComponent;

import javax.servlet.ServletException;
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
        panel.setWidth(50, Unit.PERCENTAGE);


        final Button detachButton = new Button("Detach", e -> {
            panel.setContent(null);
        });

        final Button gc = new Button("GC", e -> System.gc());

        final HorizontalLayout buttons = new HorizontalLayout(detachButton, gc);
        final VerticalLayout centralLayout = new VerticalLayout(buttons, panel);

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

    @WebServlet(urlPatterns = "/leads/*", name = "LeadsServlet", asyncSupported = true)
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
