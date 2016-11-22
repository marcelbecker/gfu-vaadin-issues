package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.Item;

import java.util.Date;
import java.util.Optional;

import static de.gfu.vaadin.persistence.DatabaseProvider.databaseProviderInstance;
import static de.gfu.vaadin.support.Numbers.parseNumber;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

/**
 * Created by mbecker on 07.10.2016.
 */
public class ItemRepository {

    public static void clear() {
        databaseProviderInstance().get().items().clear();
    }

    public static void save(Item item) {
        System.out.println("Saving item " + item.toString());
        if (item.getCreated() == null) {
            item.setCreated(new Date());
        }
        item.setUpdated(new Date());
        databaseProviderInstance().get().items().save(item);
    }

    public static Optional<Item> findItemById(String id) {
        return parseNumber(id)
                .map(Number::intValue)
                .map(parsedId -> ofNullable(databaseProviderInstance().get().items().load(parsedId)))
                .orElse(empty());
    }
}
