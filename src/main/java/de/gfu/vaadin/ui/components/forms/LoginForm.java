package de.gfu.vaadin.ui.components.forms;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by mbecker on 29.07.2016.
 */
public class LoginForm extends FormLayout {

    private TextField loginName;

    private PasswordField password;

    private LoginListener loginListener;

    private RegisterListener registerListener;

    public LoginForm() {
        loginName = new TextField("Anmeldename");
        password = new PasswordField("Passwort");

        Button loginButton = new Button("Anmelden", this::login);
        Button registerButton = new Button("Registrieren", this::register);

        loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        registerButton.addStyleName(ValoTheme.BUTTON_LINK);

        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        addComponent(loginName);
        addComponent(password);
        addComponent(new HorizontalLayout(loginButton, registerButton));
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setRegisterListener(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }

    private void login(Button.ClickEvent clickEvent) {
        loginName.commit();
        password.commit();

        String loginName = this.loginName.getValue();
        String password = this.password.getValue();

        loginListener.onLogin(loginName, password);
    }

    private void register(Button.ClickEvent clickEvent) {
        registerListener.onRegister();
    }

    public void clearPassword() {
        this.password.clear();
    }

    public static interface LoginListener {
        void onLogin(String loginName, String password);
    }

    public static interface RegisterListener {
        void onRegister();
    }
}
