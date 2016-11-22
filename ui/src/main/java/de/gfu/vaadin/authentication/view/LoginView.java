package de.gfu.vaadin.authentication.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import de.gfu.vaadin.authentication.AuthenticationController;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.forms.LoginForm;

import java.util.Optional;

import static com.vaadin.ui.Notification.show;
import static de.gfu.vaadin.persistence.UserRepository.findByLoginNameAndPassword;

/**
 * Created by mbecker on 29.07.2016.
 */
public class LoginView extends VerticalLayout implements View {

    public static final String LOGIN_FAILED_MESSAGE = "Anmeldename und / oder Passwort unbekannt.";
    private LoginForm loginForm;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loginForm = new LoginForm();
        loginForm.setLoginListener(this::onLogin);
        loginForm.setRegisterListener(this::onRegister);


        Panel loginPanel = new Panel("Anmeldung", loginForm);
        loginPanel.addStyleName(MyTheme.CssClass.CENTER_PANEL);
        loginPanel.setSizeUndefined();

        Panel registerPanel = new Panel("Registrieren", loginForm);
        registerPanel.addStyleName(MyTheme.CssClass.CENTER_PANEL);
        registerPanel.setSizeUndefined();

        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(loginPanel, "Login");
        tabSheet.addTab(registerPanel, "Registrieren");

        HorizontalLayout horizonalLayout = new HorizontalLayout(tabSheet);
        horizonalLayout.setSizeFull();
        addComponent(horizonalLayout);
        setComponentAlignment(horizonalLayout, Alignment.MIDDLE_CENTER);

        setSpacing(true);
        setSizeFull();
    }

    private void onRegister() {
        UI.getCurrent().getNavigator().navigateTo("register");
    }

    private void onLogin(String loginName, String password) {
        Optional<User> userOptional = findByLoginNameAndPassword(loginName, password);
        if (userOptional.isPresent()) {
            AuthenticationController.login(userOptional.get());
        } else {
            loginForm.clearPassword();
            show(LOGIN_FAILED_MESSAGE);
            loginForm.setComponentError(new UserError(LOGIN_FAILED_MESSAGE));
        }
    }

    @Override
    public void detach() {
        super.detach();
        System.out.println("DETACH LOGINVIEW");
    }
}
