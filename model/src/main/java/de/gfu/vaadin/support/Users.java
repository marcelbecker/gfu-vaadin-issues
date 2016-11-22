package de.gfu.vaadin.support;

import de.gfu.vaadin.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mbecker on 07.10.2016.
 */
public class Users {

    public static User firstUser() {
        User user = new User();
        user.setLoginName("foo");
        user.setPassword(asHashedPassword("bar"));
        user.setLongName("Foo Bar");
        return user;
    }

    public static byte[] asHashedPassword(String password) {
        try {
            return MessageDigest.getInstance("MD5").digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("System error. No MD5 algorithm available.");
        }
    }

}
