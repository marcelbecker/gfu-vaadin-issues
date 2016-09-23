package de.gfu.vaadin.ui.components.forms;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import de.gfu.vaadin.MainUI;
import de.gfu.vaadin.model.Issue;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

/**
 * Created by mbecker on 29.07.2016.
 */
public class IssueForm extends FormLayout {

    @Caption("Seiteninhalt")
    private RichTextArea content;

    @Caption("Erstellt")
    private DateField created;

    @Caption("Zuletzt aktualisiert")
    private DateField updated;

    private TextField userName;

    private BeanFieldGroup<Issue> fieldGroup;

    private Issue issue;

    public IssueForm(Issue issue) {
        this.issue = issue;
        userName = new TextField("Benutzer");

        fieldGroup = new BeanFieldGroup<>(Issue.class);
        fieldGroup.buildAndBindMemberFields(this);
        fieldGroup.setItemDataSource(issue);

        content.setNullRepresentation("");

        Button cancel = new Button("Verwerfen", this::cancel);
        Button ok = new Button("Speichern", this::ok);
        ok.addStyleName(BUTTON_PRIMARY);

        HorizontalLayout buttonGroup = new HorizontalLayout(ok, cancel);
        buttonGroup.setSpacing(true);

        addComponent(content);
        addComponent(created);
        addComponent(updated);
        addComponent(buttonGroup);
    }

    private void cancel(Button.ClickEvent clickEvent) {
        fieldGroup.clear();
    }

    private void ok(Button.ClickEvent clickEvent) {
        try {
            fieldGroup.commit();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Validierung fehlgeschlagen", Notification.Type.ERROR_MESSAGE);
        }

        MainUI.getDatabase().items.save(issue);
        fireEvent(new OkEvent(this, issue));
    }

    public static class OkEvent extends Component.Event {

        private Issue issue;

        /**
         * Constructs a new event with the specified source component.
         *
         * @param source the source component of the event
         */
        public OkEvent(Component source, Issue issue) {
            super(source);
            this.issue = issue;
        }

        public Issue getIssue() {
            return issue;
        }
    }
}
