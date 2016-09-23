package de.gfu.vaadin.ui.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.model.Issue;

/**
 * Created by mbecker on 29.07.2016.
 */
public class IssueDisplay extends VerticalLayout {

    private final Label content;

    private Issue issue;

    public IssueDisplay(Issue issue) {
        setSizeFull();

        content = new Label();
        content.setContentMode(ContentMode.HTML);
        addComponent(content);
        setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        setIssue(issue);
    }

    public final void setIssue(Issue issue) {
        this.issue = issue;
        if (issue == null) {
            content.setValue("<span style=\"font-size: 35px;\">Willkommen</span>");
        } else {
            this.issue = issue;
            content.setValue(issue.getContent());
        }
    }
}
