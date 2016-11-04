package de.gfu.vaadin.authentication.ui;

/**
 * @author Created by mbecker on 04.11.2016.
 */
public class Account {
    private String benutzername;
    private String vorname;
    private String nachname;
    private String email;

    public Account(String benutzername,
                   String vorname,
                   String nachname,
                   String email) {
        this.benutzername = benutzername;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
