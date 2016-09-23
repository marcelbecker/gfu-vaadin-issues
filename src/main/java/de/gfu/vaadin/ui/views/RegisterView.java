package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.MainUI;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.ui.auth.CurrentUser;
import de.gfu.vaadin.ui.components.forms.UserForm;

import static de.gfu.vaadin.ui.views.ViewController.showLoginView;

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
        panel.addStyleName(MyTheme.CENTER_PANEL);
        panel.setSizeUndefined();

        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        addStyleName(MyTheme.VACATION_BACKGROUND);
        setSizeFull();
    }

    private void onBack() {
        showLoginView();
    }

    private void onRegister(User user) {
        CurrentUser.set(user);
        if (MainUI.getDatabase().users.load(user.getLoginName()) != null) {
            Notification notification = new Notification(String.format("Benutzer %1s existiert bereits.", user.getLoginName()), Notification.Type.WARNING_MESSAGE);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        } else {
            MainUI.getDatabase().users.save(user);
            showLoginView();
            Notification notification = new Notification(String.format("Benutzer %1s registriert.", user.getLoginName()), Notification.Type.HUMANIZED_MESSAGE);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        }
    }
}
