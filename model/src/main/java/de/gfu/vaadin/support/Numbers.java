package de.gfu.vaadin.support;

import java.util.Optional;

import static java.lang.Long.parseLong;
import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Created by mbecker on 07.10.2016.
 */
public class Numbers {

    public static Optional<Number> parseNumber(String string) {
        try {
            return of(parseLong(string));
        } catch (NumberFormatException e) {
            return empty();
        }
    }

}
