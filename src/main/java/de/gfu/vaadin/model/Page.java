package de.gfu.vaadin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbecker on 29.07.2016.
 */
public class Page extends Item {

    private List<Page> pages = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    private List<Attachment> attachments = new ArrayList<>();

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public List<Page> getPages() {
        return pages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public String getContent() {
        return content;
    }
}
