package de.gfu.vaadin.authentication.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.ui.components.forms.UserForm;

import java.util.Optional;

import static com.vaadin.shared.Position.TOP_CENTER;
import static com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static de.gfu.vaadin.persistence.UserRepository.findByLoginName;
import static de.gfu.vaadin.persistence.UserRepository.save;
import static de.gfu.vaadin.theme.MyTheme.CssClass.CENTER_PANEL;
import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;
import static java.lang.String.format;

/**
 * Created by mbecker on 29.07.2016.
 */
public class RegisterView extends VerticalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UserForm userForm = new UserForm(new User());
        userForm.setOkListener(this::onRegister);
        userForm.setBackListener(this::onBack);

        Panel panel = new Panel("Registrierung", userForm);
        panel.addStyleName(CENTER_PANEL);
        panel.setSizeUndefined();

        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        addStyleName(VACATION_BACKGROUND);
        setSizeFull();
    }

    private void onBack() {
        UI.getCurrent().getNavigator().navigateTo("login");
    }

    private void onRegister(User user) {
        Optional<User> userOptional = findByLoginName(user.getLoginName());
        if (userOptional.isPresent()) {
            Notification notification = new Notification(format("Benutzer %s existiert bereits.", user.getLoginName()), WARNING_MESSAGE);
            notification.setPosition(TOP_CENTER);
            notification.show(Page.getCurrent());
        } else {
            save(user);
            UI.getCurrent().getNavigator().navigateTo("login");
            Notification notification = new Notification(format("Benutzer %s registriert.", user.getLoginName()), HUMANIZED_MESSAGE);
            notification.setPosition(TOP_CENTER);
            notification.show(Page.getCurrent());
        }
    }

    @Override
    public void attach() {
        super.attach();
    }

    @Override
    public void detach() {
        super.detach();
    }

    @Override
    public void addAttachListener(AttachListener listener) {
        super.addAttachListener(listener);
    }
}
