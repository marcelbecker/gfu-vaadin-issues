package de.gfu.vaadin.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mbecker on 29.07.2016.
 */
public class Issue extends Item {

    private List<Comment> comments = new ArrayList<>();

    private List<Attachment> attachments = new ArrayList<>();

    @NotNull
    private Type type;

    private Status status;

    @Size(min = 3, max = 254)
    private String title;

    private String content;

    @Future
    private Date deadline;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "comments=" + comments +
                ", attachments=" + attachments +
                ", type=" + type +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
