package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.MainUI;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.ui.auth.CurrentUser;
import de.gfu.vaadin.ui.components.forms.LoginForm;

/**
 * Created by mbecker on 29.07.2016.
 */
public class LoginView extends VerticalLayout implements View {

    private LoginForm loginForm;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loginForm = new LoginForm();
        loginForm.setLoginListener(this::onLogin);
        loginForm.setRegisterListener(this::onRegister);

        Panel panel = new Panel("Anmeldung", loginForm);
        panel.addStyleName(MyTheme.CENTER_PANEL);
        panel.setSizeUndefined();

        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        addStyleName(MyTheme.VACATION_BACKGROUND);
        setSpacing(true);
        setSizeFull();
    }

    private void onRegister() {
        ViewController.showRegisterView();
    }

    private void onLogin(String loginName, String password) {
        User user = MainUI.getDatabase().users.load(loginName);

        if (user == null || ! user.equalsPassword(password)) {
            this.loginForm.clearPassword();
            this.loginForm.setComponentError(new UserError("Anmeldename und / oder Passwort unbekannt."));
        } else {
            CurrentUser.set(user);
            ViewController.showMainView();
        }
    }
}
