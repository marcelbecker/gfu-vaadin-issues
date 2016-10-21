package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static de.gfu.vaadin.persistence.DatabaseProvider.databaseProviderInstance;
import static de.gfu.vaadin.support.Numbers.parseNumber;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by mbecker on 07.10.2016.
 */
public class ItemRepository {

    public static void save(Item item) {
        databaseProviderInstance().get().items().save(item);
    }

    public static Optional<Item> findItemById(String id) {
        return parseNumber(id)
                .map(Number::intValue)
                .map(parsedId -> ofNullable(databaseProviderInstance().get().items().load(parsedId)))
                .orElse(empty());
    }
}
