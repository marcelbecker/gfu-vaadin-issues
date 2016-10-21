package de.gfu.vaadin.support;

/**
 * Created by mbecker on 21.10.2016.
 */
public class Strings {

    /**
     * Cuts the input string at index and appends three dots '...', if necessary.
     * @param input the input string
     * @param index a number greater than zero
     * @return the abbreviated string
     */
    public static String abbreviatedAt(String input, int index) {
        if (input == null) {
            return "";
        }
        if (input.length() <= index) {
            return input;
        }
        return input.substring(0, index).concat("...");
    }

}
