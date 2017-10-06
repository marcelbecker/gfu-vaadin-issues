package de.gfu.vaadin.architectures;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.BigDecimalRangeValidator;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.FooterCell;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDate.now;

/**
 *
 * Created by MBecker on 06.10.2017.
 */
public class MeterView extends CustomComponent {

    public MeterView() {


        // init the domain model
        final Meter meter = new Meter();


        // init UI components
        final TextField zählerstand = new TextField("Zählerstand");
        final DateField erfassungsDatum = new DateField("Datum der Erfassung");
        final Button erfassen = new Button("Erfassen");
        final Grid<Meter.MeterReading> zählerstände = new Grid<>("Erfasste Zählerstände");
        zählerstände.addColumn(Meter.MeterReading::getReadingDate).setCaption("Datum der Erfassung");
        zählerstände.addColumn(Meter.MeterReading::getReadingValue).setCaption("Zählerstand");
        final FooterCell footerCell = zählerstände.addFooterRowAt(0)
                .join(zählerstände.getColumns().toArray(new Grid.Column[0]));
        zählerstände.setItems(meter.getReadings());


        // init data binding
        final Binder<Meter.MeterReading> binder = new Binder<>(Meter.MeterReading.class);
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

        footerCell.setText("Durchschnittlicher Verbrauch: " + meter.averagePowerConsumption());


        // Interaktions-Logik
        erfassen.addClickListener(e -> {

            if (binder.validate().hasErrors()) {
                return;
            }

            final Meter.MeterReading meterReading = binder.getBean();

            if (meter.isPlausible(meterReading)) {
                binder.setBean(new Meter.MeterReading());
                meter.addReading(meterReading);
                zählerstände.setItems(meter.getReadings());
                footerCell.setText("Durchschnittlicher Verbrauch: " + meter.averagePowerConsumption());

            } else {

                zählerstand.setComponentError(new UserError("Zählerstand nicht plausibel."));
            }

        });


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

}
