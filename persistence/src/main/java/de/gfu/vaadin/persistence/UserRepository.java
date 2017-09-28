package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.User;

import java.util.Objects;
import java.util.Optional;

import static de.gfu.vaadin.persistence.DatabaseProvider.databaseProviderInstance;
import static java.util.Optional.ofNullable;

/**
 * Created by mbecker on 07.10.2016.
 */
public class UserRepository {

    public static void save(User user) {
        databaseProviderInstance().get().users().save(user);
    }

    public static Optional<User> findByLoginName(String loginName) {
        return ofNullable(databaseProviderInstance().get().users().load(loginName));
    }

    public static Optional<User> findByLoginNameAndPassword(String loginName, String password) {
        return findByLoginName(loginName)
                .filter(user -> Objects.equals(password, user.getPassword()));
    }

}
