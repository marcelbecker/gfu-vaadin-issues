package de.gfu.vaadin.example;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

import static java.util.Optional.ofNullable;

/**
 *
 * Created by MBecker on 31.10.2017.
 */
public class AnsprachetextUI extends UI {
    @Override
    protected void init(VaadinRequest request) {


        final AnsprachetextComponent ansprachetextComponent =
                new AnsprachetextComponent();

        final String text = request.getParameter("text");
        ansprachetextComponent
                .setValue(ofNullable(text)
                        .orElseGet(AnsprachetextComponent::defaultPresetValue));

        final Label label = new Label();

        ansprachetextComponent.onValueChange(label::setValue);

        setContent(new VerticalLayout(
                ansprachetextComponent,
                label
        ));


    }

    @WebServlet(urlPatterns = "/ansprachetext/*", name = "AnsprachetextUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AnsprachetextUI.class, productionMode = true)
    public static class AnsprachetextUIServlet extends VaadinServlet {}
}
