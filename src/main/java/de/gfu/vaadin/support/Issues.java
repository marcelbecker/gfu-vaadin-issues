package de.gfu.vaadin.support;

import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mbecker on 21.10.2016.
 */
public class Issues {

    public static Issue newIssue(User user) {
        Issue issue = new Issue();
        issue.setUser(user);
        issue.setStatus(defaultStatus());
        return issue;
    }

    public static String defaultStatus() {
        return allStatus().iterator().next();
    }

    public static List<String> allStatus() {
        return Arrays.asList(
                "Neu", "In Arbeit", "Fertig");
    }

}
