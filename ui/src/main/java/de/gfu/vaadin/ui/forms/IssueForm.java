package de.gfu.vaadin.ui.forms;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import de.gfu.vaadin.model.Type;


/**
 * Created by MBecker on 28.09.2017.
 */
public class IssueForm {
    public TextField title = new TextField("Title");
    public RichTextArea content = new RichTextArea("Content");
    public ComboBox<Type> type = new ComboBox<>("Type");
    public InlineDateField deadline = new InlineDateField("Deadline");
    public TextField created = new TextField("Created");
    public TextField user = new TextField("User");

    {
        type.setItems(Type.values());
    }
}
