package de.gfu.vaadin.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.gfu.vaadin.theme.MyTheme;

import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;

/**
 *
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
public class DemoUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

//        addStyleName(VACATION_BACKGROUND);

        final Label label = new Label("");
        label.setId("early-learning-label");

        final ComboBox<String> comboBox = new ComboBox<>("ComboBox");
        comboBox.setId("early-learnings");
        comboBox.setItems("Mama", "Papa", "Auto", "Ball", "Katze", "Hund");
        comboBox.addSelectionListener(s -> label.setValue(s.getValue()));


        Panel panel = new Panel();
        panel.addStyleName(CENTER_PANEL);
        panel.setSizeUndefined();
        panel.setContent(new VerticalLayout(
                comboBox,
                label
        ));

        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    @WebServlet(urlPatterns = "/demo/*", name = "DemoServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoUI.class, productionMode = true)
    public static class DemoUIServlet extends VaadinServlet {}

    @WebServlet(urlPatterns = "/VAADIN/*", name = "VaadinBootstrapServlet")
    public static class VaadinBootstrapServlet extends VaadinServlet {}

}
