package de.gfu.vaadin.application;

import de.gfu.vaadin.model.User;

import java.util.Optional;

import static com.vaadin.server.VaadinSession.getCurrent;
import static java.util.Optional.ofNullable;

/**
 * Created by mbecker on 29.07.2016.
 */
public class SessionObjects {

    /**
     * Retrieves current user from http session.
     * @return an optional user
     */
    public static Optional<User> currentUser() {
        return ofNullable((User) getCurrent().getSession().getAttribute("User"));
    }

    /**
     * Store the user object in the http session.
     * @param user the {@link User}
     */
    public static void setCurrentUser(User user) {
        getCurrent().getSession().setAttribute("User", user);
    }

}
