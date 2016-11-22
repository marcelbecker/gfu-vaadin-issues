package de.gfu.vaadin.ui.components.forms;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Status;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

/**
 * Created by mbecker on 29.07.2016.
 */
public class IssueForm extends FormLayout {

    @Caption("Titel")
    private TextField title;

    @Caption("Seiteninhalt")
    private RichTextArea content;

    @Caption("Erstellt")
    private DateField created;

    @Caption("Zuletzt aktualisiert")
    private DateField updated;

    @Caption("Benutzer")
    private TextField longName;

    private ComboBox status;

    private BeanFieldGroup<Issue> fieldGroup;

    private Issue issue;
    private final Button cancel;
    private final Button ok;

    public IssueForm(Issue issue) {
        this.issue = issue;

        status = new ComboBox("Status");
        status.addItems(Status.values());
        status.setNullSelectionAllowed(false);

        fieldGroup = new BeanFieldGroup<>(Issue.class);
        fieldGroup.buildAndBindMemberFields(this);
        fieldGroup.setItemDataSource(issue);
        fieldGroup.getItemDataSource().addNestedProperty("user.longName");

        longName = new TextField("Benutzer", fieldGroup.getItemDataSource().getItemProperty("user.loginName"));

        content.setNullRepresentation("");
        title.setNullRepresentation("");
        title.setNullSettingAllowed(false);
        title.setInputPrompt("Titel angeben");

        cancel = new Button("Verwerfen", this::cancel);
        ok = new Button("Speichern", this::ok);
        ok.addStyleName(BUTTON_PRIMARY);

        HorizontalLayout buttonGroup = new HorizontalLayout(ok, cancel);
        buttonGroup.setSpacing(true);

        addComponent(title);
        addComponent(content);

        HorizontalLayout datesLayout = new HorizontalLayout(created, updated);
        datesLayout.setSpacing(true);

        HorizontalLayout metaLayout = new HorizontalLayout(status, longName);
        metaLayout.setSpacing(true);

        addComponent(datesLayout);
        addComponent(metaLayout);
        addComponent(buttonGroup);

        setReadOnly(false);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        ok.setVisible(!readOnly);
        cancel.setVisible(!readOnly);
        created.setVisible(readOnly);
        updated.setVisible(readOnly);
        longName.setVisible(readOnly);
        longName.setReadOnly(true);
        fieldGroup.setReadOnly(readOnly);
        fieldGroup.buildAndBindMemberFields(this);
    }

    private void cancel(Button.ClickEvent clickEvent) {
        fieldGroup.getFields().stream()
                .filter(field -> !field.isReadOnly())
                .forEach(Field::clear);
    }

    private void ok(Button.ClickEvent clickEvent) {
        try {
            fieldGroup.commit();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Validierung fehlgeschlagen", ERROR_MESSAGE);
        }

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
