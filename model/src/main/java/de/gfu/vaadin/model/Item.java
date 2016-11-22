package de.gfu.vaadin.model;

import java.util.Date;

/**
 * Created by mbecker on 29.07.2016.
 */
public abstract class Item {

    private Integer id;

    private User user;

    private Date created;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", user=" + user +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
