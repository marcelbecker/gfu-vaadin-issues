package de.gfu.vaadin.extensions;

import com.vaadin.data.HasValue;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;

/**
 * Created by MBecker on 06.10.2017.
 */
public class Autocomplete extends AbstractExtension {

    public static void extend(HasValue<String> field) {
        new Autocomplete().extend((AbstractClientConnector) field);
    }

}
