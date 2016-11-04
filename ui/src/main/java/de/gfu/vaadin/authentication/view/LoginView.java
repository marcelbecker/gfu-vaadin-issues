package de.gfu.vaadin.authentication.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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

        Panel panel = new Panel("Anmeldung", loginForm);
        panel.addStyleName(MyTheme.CssClass.CENTER_PANEL);
        panel.setSizeUndefined();

        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

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
}
