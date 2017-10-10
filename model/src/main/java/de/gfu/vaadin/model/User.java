package de.gfu.vaadin.model;

import java.util.Observable;

/**
 *
 * Created by mbecker on 29.07.2016.
 */
public class User {

    private String longName;

    private byte[] icon;

    private String loginName;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
