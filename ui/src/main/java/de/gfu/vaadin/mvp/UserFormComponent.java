package de.gfu.vaadin.mvp;

import com.vaadin.data.Binder;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.User;

/**
 *
 * Created by MBecker on 26.09.2017.
 */
public class UserFormComponent extends CustomComponent {




    private final TextField longName = new TextField("Name");
    private final TextField loginName = new TextField("Anmeldename");
    private final PasswordField password = new PasswordField("Passwort");
    private final Presenter presenter;


    public UserFormComponent(User user, Backend backend) {


        presenter = new Presenter(this, backend);


        final Binder<User> binder = new Binder<>(User.class);
        binder.setBean(user);
        binder.bindInstanceFields(this);


        final Button saveButton = new Button("Speichern",
                e -> presenter.saveUser(binder.getBean()));


        setCompositionRoot(
                new FormLayout(longName, loginName, password, saveButton));


    }

    @Override
    public void setComponentError(ErrorMessage componentError) {
        loginName.setComponentError(componentError);
    }

    static class Presenter {

        private final UserFormComponent component;
        private final Backend backend;

        Presenter(UserFormComponent component,
                  Backend backend) {
            this.component = component;
            this.backend = backend;
        }

        void saveUser(User user) {
            if (backend.getUser(user.getLoginName()).isPresent()) {
                component.setComponentError(new UserError("Benutzer existiert bereits."));
                return;
            }

            backend.postUser(user);
        }
    }


}
