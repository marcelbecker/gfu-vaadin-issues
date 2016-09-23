package de.gfu.vaadin.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by mbecker on 29.07.2016.
 */
public class User {

    private String longName;

    private byte[] icon;

    private String loginName;

    private byte[] password;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public boolean equalsPassword(String password) {
        return Arrays.equals(toMd5(password), this.password);
    }

    private byte[] toMd5(String password) {
        try {
            return MessageDigest.getInstance("MD5").digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("System error. No MD5 algorithm available.");
        }
    }

    public void setPassword(String password) {
        this.password = toMd5(password);
    }
}
