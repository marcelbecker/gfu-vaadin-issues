package de.gfu.vaadin.persistence;


import de.gfu.vaadin.model.IdGenerator;
import de.gfu.vaadin.model.Item;
import de.gfu.vaadin.model.User;

import java.util.*;

/**
 * Created by mbecker on 29.07.2016.
 */
public class Database {

    private final IdGenerator idGenerator = new IdGenerator();

    Database() {
    }

    private final Items items = new Items();
    private final Users users = new Users();

    public Items items() {
        return items;
    }

    public Users users() {
        return users;
    }

    class Items {

        private final Map<Integer, Item> items = Collections.synchronizedMap(new HashMap<>());

        void save(Item item) {
            Objects.requireNonNull(item);

            if (item.getId() == null) {
                idGenerator.nextId(item);
            }

            items.put(item.getId(), item);
        }

        void delete(Item item) {
            Objects.requireNonNull(item);
            Objects.requireNonNull(item.getId());

            items.remove(item.getId());
        }

        Item load(Integer id) {
            return items.get(id);
        }

        Collection<Item> loadAll() {
            return items.values();
        }
    }

    class Users {
        private final Map<String, User> users = Collections.synchronizedMap(new HashMap<>());

        void save(User user) {
            Objects.requireNonNull(user);

            if (users.get(user.getLoginName()) != null) {
                throw new IllegalArgumentException("Benutzer existiert bereits.");
            }

            users.put(user.getLoginName(), user);
        }

        User load(String loginName) {
            return users.get(loginName);
        }
    }

}
