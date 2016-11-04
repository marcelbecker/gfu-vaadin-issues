package de.gfu.vaadin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbecker on 29.07.2016.
 */
public class Issue extends Item {

    private List<Comment> comments = new ArrayList<>();

    private List<Attachment> attachments = new ArrayList<>();

    private String type;

    private String status;

    private String title;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
