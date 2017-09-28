package de.gfu.vaadin.ui.components;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.gfu.vaadin.model.User;

import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;

/**
 * Created by MBecker on 28.09.2017.
 */
public class LoginComponent extends CustomComponent {


    /**
     * Fields
     */
    private final TextField loginName;
    private final PasswordField password;


    private final Panel panel;
    private final Button loginButton;
    private final Button registerButton;
    private final Binder<User> userBinding = new Binder<>(User.class);

    public LoginComponent() {

        // disable default full size
        setSizeUndefined();


        loginName = new TextField("Anmeldename");
        loginName.addBlurListener(e -> getPassword().clear());


        password = new PasswordField("Passwort");


        userBinding.setBean(new User());
        userBinding.bindInstanceFields(this);


//        Aufgabe a)
//        userBinding.forField(loginField)
//                .bind(User::getLoginName, User::setLoginName);
//
//        userBinding.forField(passwordField)
//                .bind(User::getPassword, User::setPassword);


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


        final FormLayout formLayout = new FormLayout(loginName, password,
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
        final User user = userBinding.getBean();

        if ("foo".equals(user.getLoginName())
                && "bar".equals(user.getPassword())) {
            Notification.show("Anmeldung erfolgreich!");

        } else {
            Notification.show("Anmeldung fehlgeschlagen!");
            password.setComponentError(new UserError("Anmeldename und / oder Passwort unbekannt."));
        }

        password.clear();
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

    private PasswordField getPassword() {
        return password;
    }
}
