package de.gfu.vaadin.ui.converters;


import com.vaadin.data.util.converter.Converter;
import de.gfu.vaadin.support.Users;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * Created by MBecker on 22.09.2017.
 */
public class HashConverter implements Converter<String, byte[]> {

    private final Map<byte[], String> reconversionCache = new HashMap<>();

    @Override
    public byte[] convertToModel(String value, Class<? extends byte[]> targetType, Locale locale) throws ConversionException {
        if (value == null) {
            return null;
        }
        final byte[] hashedPassword = Users.asHashedPassword(value);
        reconversionCache.put(hashedPassword, value);
        return hashedPassword;
    }

    @Override
    public String convertToPresentation(byte[] value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        if (value == null) {
            return null;
        }
        return reconversionCache.get(value);
    }

    @Override
    public Class<byte[]> getModelType() {
        return byte[].class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
