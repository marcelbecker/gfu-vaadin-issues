package de.gfu.vaadin.ui.auth;

import com.vaadin.server.VaadinSession;
import de.gfu.vaadin.model.User;

/**
 * Created by mbecker on 29.07.2016.
 */
public class CurrentUser {

    public static User get() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    public static void set(User user) {
        VaadinSession.getCurrent().setAttribute(User.class, user);
    }

}
