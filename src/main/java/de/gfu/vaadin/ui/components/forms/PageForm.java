package de.gfu.vaadin.ui.components.forms;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import de.gfu.vaadin.MainUI;
import de.gfu.vaadin.model.Page;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

/**
 * Created by mbecker on 29.07.2016.
 */
public class PageForm extends FormLayout {

    @Caption("Seiteninhalt")
    private RichTextArea content;

    @Caption("Erstellt")
    private DateField created;

    @Caption("Zuletzt aktualisiert")
    private DateField updated;

    private TextField userName;

    private BeanFieldGroup<Page> fieldGroup;

    private Page page;

    public PageForm(Page page) {
        this.page = page;
        userName = new TextField("Benutzer");

        fieldGroup = new BeanFieldGroup<>(Page.class);
        fieldGroup.buildAndBindMemberFields(this);
        fieldGroup.setItemDataSource(page);

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

        MainUI.getDatabase().items.save(page);
        fireEvent(new OkEvent(this, page));
    }

    public static class OkEvent extends Component.Event {

        private Page page;

        /**
         * Constructs a new event with the specified source component.
         *
         * @param source the source component of the event
         */
        public OkEvent(Component source, Page page) {
            super(source);
            this.page = page;
        }

        public Page getPage() {
            return page;
        }
    }
}
