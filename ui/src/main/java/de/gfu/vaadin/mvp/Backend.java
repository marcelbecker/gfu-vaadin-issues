package de.gfu.vaadin.mvp;

import de.gfu.vaadin.model.User;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Created by MBecker on 26.09.2017.
 */
public class Backend {

    public void postUser(User user) {
        System.out.println("POST User " + user);
    }

    public void putUser(User user) {
        System.out.println("PUT User " + user);
    }

    public Optional<User> getUser(String loginName) {
        if ("foo".equals(loginName)) {
            final User user = new User();
            user.setLongName("Foo Bar");
            user.setLoginName(loginName);
            user.setPassword("bar");
            return of(user);
        }

        return empty();
    }

}
