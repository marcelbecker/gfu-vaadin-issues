package de.gfu.vaadin.persistence;

import java.util.function.Supplier;

/**
 * Created by mbecker on 07.10.2016.
 */
public class DatabaseProvider implements Supplier<Database> {

    private static final DatabaseProvider instance = new DatabaseProvider();

    private final Database database;

    private DatabaseProvider() {
        database = new Database();
    }

    static DatabaseProvider databaseProviderInstance() {
        return instance;
    }

    @Override
    public Database get() {
        return database;
    }
}
