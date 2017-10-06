package de.gfu.vaadin.architectures.supervisingcontroller;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.BigDecimalRangeValidator;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.FooterCell;
import de.gfu.vaadin.architectures.Meter;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDate.now;

/**
 *
 * Created by MBecker on 06.10.2017.
 */
public class MeterView extends CustomComponent {

    private final Grid<Meter.MeterReading> zählerstände;
    private final FooterCell footerCell;
    private final Binder<Meter.MeterReading> binder;
    private final TextField zählerstand;

    public MeterView() {


        // init the domain model
        final Meter meter = new Meter();
        meter.addObserver((source, arg) -> reload((Meter) source));


        // init supervising presenter
        final MeterViewPresenter presenter = new MeterViewPresenter(this, meter);


        // init UI components
        zählerstand = new TextField("Zählerstand");
        final DateField erfassungsDatum = new DateField("Datum der Erfassung");
        final Button erfassen = new Button("Erfassen");
        zählerstände = new Grid<>("Erfasste Zählerstände");
        zählerstände.addColumn(Meter.MeterReading::getReadingDate).setCaption("Datum der Erfassung");
        zählerstände.addColumn(Meter.MeterReading::getReadingValue).setCaption("Zählerstand");
        footerCell = zählerstände.addFooterRowAt(0)
                .join(zählerstände.getColumns().toArray(new Grid.Column[0]));


        // init data binding
        binder = new Binder<>(Meter.MeterReading.class);
        // Zählerstand
        binder.forField(zählerstand)
                .asRequired("Bitte eine gültige Dezimalzahl eingeben.")
                .withNullRepresentation("")
                .withConverter(new StringToBigDecimalConverter("Bitte eine gültige Dezimalzahl eingeben."))
                .withValidator(new BigDecimalRangeValidator("Bitte eine positive Dezimalzahl eingeben.",
                        ZERO,
                        null))
                .bind(Meter.MeterReading::getReadingValue, Meter.MeterReading::setReadingValue);
        // Datum der Erfassung
        binder.forField(erfassungsDatum)
                .asRequired("Bitte ein Erfassungsdatum eingeben.")
                .withValidator(new DateRangeValidator("Bitte ein Datum eingeben, das max. eine Woche zurückliegt.",
                        now().minusWeeks(1),
                        now().plusDays(1)))
                .bind(Meter.MeterReading::getReadingDate, Meter.MeterReading::setReadingDate);
        // Objekt binden
        binder.setBean(new Meter.MeterReading());


        // Interaktions-Logik
        erfassen.addClickListener(e -> presenter.onErfassen());


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

    }

    boolean isInputDataValid() {
        return binder.validate().isOk();
    }

    private void reload(Meter meter) {
        zählerstände.setItems(meter.getReadings());
        binder.setBean(new Meter.MeterReading());
        footerCell.setText("Durchschnittlicher Verbrauch: " + meter.averagePowerConsumption());
    }

    Meter.MeterReading getInputData() {
        return binder.getBean();
    }

    void renderErrorMessage(String message) {
        zählerstand.setComponentError(new UserError(message));
    }
}
