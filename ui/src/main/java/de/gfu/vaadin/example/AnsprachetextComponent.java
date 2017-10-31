package de.gfu.vaadin.example;

import com.vaadin.data.HasValue;
import com.vaadin.shared.Registration;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

/**
 *
 * Created by MBecker on 31.10.2017.
 */
public class AnsprachetextComponent extends CustomComponent {


    private final TextField defaultField;
    private final CheckBox defaultValueCheckBox;
    private final CheckBox selectionCheckBox;
    private final ComboBox<String> comboBox;
    private final List<Consumer<String>> listeners = new ArrayList<>();

    /**
     * Public constructor.
     */
    public AnsprachetextComponent() {

        defaultValueCheckBox = new CheckBox();
        selectionCheckBox = new CheckBox();

        defaultField = new TextField();
        defaultField.setEnabled(false);

        comboBox = new ComboBox<>();
        comboBox.setTextInputAllowed(true);
        comboBox.setEmptySelectionAllowed(false);
        comboBox.setNewItemHandler(this::setValue);

        // Ensure always one check box is clicked
        defaultValueCheckBox
                .addValueChangeListener(this::toggle);
        selectionCheckBox
                .addValueChangeListener(this::toggle);

        // Enable / Disable combo box
        defaultValueCheckBox
                .addValueChangeListener(e -> comboBox.setEnabled(false));
        selectionCheckBox
                .addValueChangeListener(e -> comboBox.setEnabled(true));

        // Notify listeners
        defaultValueCheckBox
                .addValueChangeListener(e -> fireValueChange(defaultField.getValue()));
        selectionCheckBox
                .addValueChangeListener(e -> fireValueChange(comboBox.getSelectedItem().orElse(null)));
        comboBox
                .addValueChangeListener(e -> fireValueChange(e.getValue()));


        final GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.addComponents(
                defaultValueCheckBox,
                defaultField,
                selectionCheckBox,
                comboBox);

        setCompositionRoot(gridLayout);

    }

    /**
     * Sets the value. If the value is different from {@link #getValue()},
     * value change listeners will be notified.
     *
     * @param value the value
     */
    public void setValue(String value) {
        final String oldValue = getValue();
        if (oldValue != null && oldValue.equals(value)) {
            return;
        }

        final List<String> presetValues = new ArrayList<>(presetValues());
        if (!presetValues.contains(value)) {
            presetValues.add(value);
        }

        defaultField.setValue(presetValues.get(0));
        comboBox.setItems(presetValues.subList(1, presetValues.size()));

        if (defaultField.getValue().equals(value)) {
            defaultValueCheckBox.setValue(true);
        } else {
            selectionCheckBox.setValue(true);
            comboBox.setSelectedItem(value);
        }
    }

    /**
     * Returns the currently selected value.
     *
     * @return the value
     */
    public String getValue() {
        if (defaultValueCheckBox.getValue()) {
            return defaultField.getValue();
        }
        return comboBox.getSelectedItem()
                .orElse(null);
    }

    /**
     * Registers a consumer that will be called on value change.
     *
     * @param valueChange a consumer that accepts the new value
     * @return a registration
     */
    public Registration onValueChange(Consumer<String> valueChange) {
        listeners.add(valueChange);
        return () -> listeners.remove(valueChange);
    }

    private void toggle(HasValue.ValueChangeEvent<Boolean> event) {
        CheckBox notSource = event.getSource() == defaultValueCheckBox ?
                selectionCheckBox : defaultValueCheckBox;
        notSource.setValue(! event.getValue());
    }

    private void fireValueChange(String value) {
        listeners.forEach(c -> c.accept(value));
    }

    private static List<String> presetValues() {
        return asList(
                "An die Bewohner des Hauses",
                "An alle Stromsparer",
                "An die Familie",
                "An alle Mitbewohner"
                );
    }

    /**
     * Default preset value.
     *
     * @return default preset value
     */
    public static String defaultPresetValue() {
        return presetValues().get(0);
    }

}
