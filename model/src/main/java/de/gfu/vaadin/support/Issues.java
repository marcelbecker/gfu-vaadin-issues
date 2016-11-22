package de.gfu.vaadin.support;

import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Status;
import de.gfu.vaadin.model.Type;
import de.gfu.vaadin.model.User;

import static de.gfu.vaadin.model.Type.TASK;

/**
 * Created by mbecker on 21.10.2016.
 */
public class Issues {

    public static Issue newIssue(User user) {
        Issue issue = new Issue();
        issue.setUser(user);
        issue.setStatus(defaultStatus());
        issue.setType(TASK);
        return issue;
    }

    public static Status defaultStatus() {
        return Status.values()[0];
    }

}
