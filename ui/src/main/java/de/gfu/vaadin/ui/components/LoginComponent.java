package de.gfu.vaadin.ui.components;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;

/**
 * Created by MBecker on 28.09.2017.
 */
public class LoginComponent extends CustomComponent {

    private final Panel panel;

    public LoginComponent() {

        // disable default full size
        setSizeUndefined();


        final TextField loginField = new TextField("Anmeldename");


        final PasswordField passwordField = new PasswordField("Passwort");


        final Button loginButton = new Button("Anmelden");
        loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);


        final Button registerButton = new Button("Registrieren");
        registerButton.addStyleName(ValoTheme.BUTTON_LINK);


        final FormLayout formLayout = new FormLayout(loginField, passwordField,
                new HorizontalLayout(loginButton, registerButton));


        panel = new Panel();
        panel.addStyleName(CENTER_PANEL);
        panel.setContent(formLayout);
        panel.setSizeUndefined();


        setCompositionRoot(panel);

    }

    @Override
    public void setCaption(String caption) {
        panel.setCaption(caption);
    }
}
