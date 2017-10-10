package de.gfu.vaadin.mvp;


import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 *
 * Created by MBecker on 26.09.2017.
 */
public class MobileTextField extends CustomComponent {

    private final TextField textField;
    private final Button clearButton;

    public MobileTextField() {

        textField = new TextField();
        clearButton = new Button("X");
        clearButton.addClickListener(e -> clearText());
        setCompositionRoot(new HorizontalLayout(textField, clearButton));

    }

    void clearText() {
        textField.clear();
    }

}
