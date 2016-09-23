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


    public final Items items = new Items();
    public final Users users = new Users();


    public class Items {

        private final Map<Integer, Item> items = Collections.synchronizedMap(new HashMap<>());

        public void save(Item item) {
            Objects.requireNonNull(item);

            if (item.getId() == null) {
                idGenerator.nextId(item);
            }

            items.put(item.getId(), item);
        }

        public void delete(Item item) {
            Objects.requireNonNull(item);
            Objects.requireNonNull(item.getId());

            items.remove(item.getId());
        }

        public Item load(Integer id) {
            return items.get(id);
        }

        public Collection<Item> loadAll() {
            return items.values();
        }
    }

    public class Users {
        private final Map<String, User> users = Collections.synchronizedMap(new HashMap<>());

        public void save(User user) {
            Objects.requireNonNull(user);

            if (users.get(user.getLoginName()) != null) {
                throw new IllegalArgumentException("Benutzer existiert bereits.");
            }

            users.put(user.getLoginName(), user);
        }

        public User load(String loginName) {
            return users.get(loginName);
        }
    }

}
