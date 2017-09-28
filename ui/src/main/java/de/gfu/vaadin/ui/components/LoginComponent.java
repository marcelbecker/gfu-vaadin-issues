package de.gfu.vaadin.ui.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;

/**
 * Created by MBecker on 28.09.2017.
 */
public class LoginComponent extends CustomComponent {

    private final Panel panel;
    private final TextField loginField;
    private final PasswordField passwordField;
    private final Button loginButton;
    private final Button registerButton;

    public LoginComponent() {

        // disable default full size
        setSizeUndefined();


        loginField = new TextField("Anmeldename");
        loginField.addBlurListener(e -> getPasswordField().clear());


        passwordField = new PasswordField("Passwort");


        loginButton = new Button("Anmelden", this::onLogin);
        loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        registerButton = new Button("Registrieren", this::onRegister);
        registerButton.addStyleName(ValoTheme.BUTTON_LINK);


        // Aufgabe b)
        // loginButton.addClickListener(this::handleButtonClicks);
        // registerButton.addClickListener(this::handleButtonClicks);


        // Aufgabe e)
//        loginButton.addListener(Button.ClickEvent.class,
//                (Button.ClickListener) this::onLogin,
//                Button.ClickListener.BUTTON_CLICK_METHOD);


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

    private void onLogin(Button.ClickEvent e) {
        final String loginFieldValue = loginField.getValue();
        final String passwordFieldValue = passwordField.getValue();

        if ("foo".equals(loginFieldValue)
                && "bar".equals(passwordFieldValue)) {
            Notification.show("Anmeldung erfolgreich!");

        } else {
            Notification.show("Anmeldung fehlgeschlagen!");
            passwordField.setComponentError(new UserError("Anmeldename und / oder Passwort unbekannt."));
        }

        passwordField.clear();
    }

    private void onRegister(Button.ClickEvent e) {
        Notification.show("Registrierung nicht möglich.");
    }

    // Aufgabe b)
    private void handleButtonClicks(Button.ClickEvent e) {

        if (e.getButton() == loginButton) {
            onLogin(e);
        }

        if (e.getButton() == registerButton) {
            onRegister(e);
        }

    }

    private PasswordField getPasswordField() {
        return passwordField;
    }
}
