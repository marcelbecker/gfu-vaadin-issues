package de.gfu.vaadin.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mbecker on 29.07.2016.
 */
public class IdGenerator {

    private final AtomicInteger id = new AtomicInteger();

    public void nextId(Item item) {
        int nextId = id.incrementAndGet();
        item.setId(nextId);
    }

}
