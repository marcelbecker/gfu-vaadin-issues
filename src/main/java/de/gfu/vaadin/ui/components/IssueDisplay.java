package de.gfu.vaadin.ui.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.gfu.vaadin.model.Issue;

/**
 * Created by mbecker on 29.07.2016.
 */
public class IssueDisplay extends VerticalLayout {

    private Label title;
    @Caption("Erstellt am:")
    private TextField created;
    @Caption("Zuletzt bearbeitet: ")
    private TextField updated;
    @Caption("Status: ")
    private TextField status;
    @Caption("Typ: ")
    private TextField type;
    private Label content;

    public IssueDisplay(Issue issue) {
        setSizeFull();
        setSpacing(true);

        BeanFieldGroup<Issue> fieldGroup = new BeanFieldGroup<>(Issue.class);
        fieldGroup.setReadOnly(true);
        fieldGroup.buildAndBindMemberFields(this);
        fieldGroup.setItemDataSource(issue);

        content = new Label(fieldGroup.getItemDataSource().getItemProperty("content"));
        content.setContentMode(ContentMode.HTML);
        content.setSizeFull();

        title = new Label(fieldGroup.getItemDataSource().getItemProperty("title"));
        title.addStyleName(ValoTheme.LABEL_COLORED);
        title.setSizeFull();

        GridLayout details = new GridLayout(2, 4);

        details.addComponent(new Panel(title), 0, 0, 1, 0);
        details.addComponent(new Panel(content), 0, 1, 1, 1);
        details.addComponents(created, updated);
        details.addComponents(status, type);
        details.setSpacing(true);

        Panel display = new Panel("Issue #" + issue.getId(), details);
        display.setSizeFull();


        addComponent(display);
        setComponentAlignment(display, Alignment.MIDDLE_CENTER);

    }

}
