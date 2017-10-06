package de.gfu.vaadin.architectures.passiveview;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.BigDecimalRangeValidator;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.FooterCell;
import de.gfu.vaadin.architectures.Meter;

import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDate.now;

/**
 *
 * Created by MBecker on 06.10.2017.
 */
public class MeterView extends CustomComponent {

    private final Grid<Meter.MeterReading> zählerstände;
    private final TextField zählerstand;
    private final DateField erfassungsDatum;

    private final Binder<Meter.MeterReading> binder;
    private final FooterCell footerCell;

    public MeterView() {

        binder = new Binder<>(Meter.MeterReading.class);

        // init UI components
        zählerstand = new TextField("Zählerstand");
        erfassungsDatum = new DateField("Datum der Erfassung");
        Button erfassen = new Button("Erfassen");

        zählerstände = new Grid<>("Erfasste Zählerstände");
        zählerstände.setFooterVisible(true);
        zählerstände.addColumn(Meter.MeterReading::getReadingDate).setCaption("Datum der Erfassung");
        zählerstände.addColumn(Meter.MeterReading::getReadingValue).setCaption("Zählerstand");
        footerCell = zählerstände.addFooterRowAt(0)
                .join(zählerstände.getColumns().toArray(new Grid.Column[0]));

        final FormLayout formLayout = new FormLayout(
                new Label("Zählerstand erfassen"),
                zählerstand,
                erfassungsDatum,
                erfassen
        );

        final VerticalLayout verticalLayout = new VerticalLayout(
                formLayout,
                zählerstände
        );

        setCompositionRoot(verticalLayout);


        // init the presenter
        final MeterViewPresenter presenter = new MeterViewPresenter(this, new Meter());


        // init user interaction
        erfassen.addClickListener(e -> presenter.onErfassen());

    }

    void initBinding() {

        // Zählerstand
        binder.forField(zählerstand)
                .asRequired("Bitte eine gültige Dezimalzahl eingeben.")
                .withNullRepresentation("")
                .withConverter(new StringToBigDecimalConverter("Bitte eine gültige Dezimalzahl eingeben."))
                .withValidator(new BigDecimalRangeValidator("Bitte eine positive Dezimalzahl eingeben.",
                        ZERO, null))
                .bind(Meter.MeterReading::getReadingValue, Meter.MeterReading::setReadingValue);


        // Datum der Erfassung
        binder.forField(erfassungsDatum)
                .asRequired("Bitte ein Erfassungsdatum eingeben.")
                .withValidator(new DateRangeValidator("Bitte ein Datum eingeben, das max. eine Woche zurückliegt.",
                        now().minusWeeks(1), now().plusDays(1)))
                .bind(Meter.MeterReading::getReadingDate, Meter.MeterReading::setReadingDate);


        // Objekt binden
        binder.setBean(new Meter.MeterReading());
    }

    void setInputData(Meter.MeterReading meterReading) {
        binder.setBean(meterReading);
    }

    Meter.MeterReading getInputData() {
        return binder.getBean();
    }

    void setReadingsList(List<Meter.MeterReading> readings) {
        zählerstände.setItems(readings);
    }

    void setFooterText(String text) {
        footerCell.setText(text);
    }

    void renderErrorMessage(String message) {
        zählerstand.setComponentError(new UserError(message));
    }

    boolean isInputDataValid() {
        return binder.validate().isOk();
    }
}
