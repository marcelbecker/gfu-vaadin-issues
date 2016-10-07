package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.User;
import de.gfu.vaadin.support.Users;

/**
 * Created by mbecker on 07.10.2016.
 */
public class DefaultDataSetup {

    public static void withDefaultData() {

        User defaultUser = new User();
        defaultUser.setLoginName("mb");
        defaultUser.setPassword(Users.asHashedPassword("mb"));

        UserRepository.save(defaultUser);

    }

}
