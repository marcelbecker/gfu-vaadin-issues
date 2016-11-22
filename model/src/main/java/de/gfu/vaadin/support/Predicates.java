package de.gfu.vaadin.support;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Created by mbecker on 18.11.2016.
 */
public class Predicates {

    public static <T> boolean all(T t) {
        return true;
    }

    public static <T> boolean none(T t) {
        return false;
    }

    public static <T> boolean empty(T t) {
        if (t == null) {
            return true;
        } else if (t instanceof Collection) {
            return ((Collection) t).isEmpty();
        } else if (t instanceof Optional) {
            return ((Optional) t).isPresent();
        } else if (t instanceof String) {
            return ((String) t).isEmpty();
        }
        throw new NotSupportedException("No predicate for type " + t.getClass() + " available!");
    }

    public static <T> boolean nonEmpty(T t) {
        return ! empty(t);
    }

    public static class NotSupportedException extends RuntimeException {
        public NotSupportedException(String message) {
            super(message);
        }
    }

}
