package de.gfu.vaadin.authentication;

import de.gfu.vaadin.model.User;

import static com.vaadin.server.Page.getCurrent;
import static de.gfu.vaadin.application.SessionObjects.setCurrentUser;

/**
 * Created by mbecker on 07.10.2016.
 */
public class AuthenticationController {

    public static void logout() {
        setCurrentUser(null);
        getCurrent().reload();
    }

    public static void login(User user) {
        setCurrentUser(user);
        getCurrent().setLocation("/access");
    }
}
