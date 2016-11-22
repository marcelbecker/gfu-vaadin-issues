package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.Item;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mbecker on 29.07.2016.
 */
public class IdGenerator {

    private final AtomicInteger id = new AtomicInteger();

    public int nextId(Item item) {
        return id.incrementAndGet();
    }

}
