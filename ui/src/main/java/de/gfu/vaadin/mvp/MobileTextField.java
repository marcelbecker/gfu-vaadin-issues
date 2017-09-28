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

        final MobileTextFieldPresenter presenter =
                new MobileTextFieldPresenter(this);

        textField = new TextField();
        clearButton = new Button("X");
        clearButton.addClickListener(e -> presenter.onClearButtonClick());
        setCompositionRoot(new HorizontalLayout(textField, clearButton));

    }

    void clearText() {
        textField.clear();
    }

    /**
     * Presenter class for {@link MobileTextField}.
     */
    static class MobileTextFieldPresenter {
        private final MobileTextField mobileTextField;

        MobileTextFieldPresenter(MobileTextField mobileTextField) {
            this.mobileTextField = mobileTextField;
        }

        void onClearButtonClick() {
            mobileTextField.clearText();
        }
    }
}
