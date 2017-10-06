package de.gfu.vaadin.architectures.supervisingcontroller;

import de.gfu.vaadin.architectures.Meter;

/**
 *
 *
 * Created by MBecker on 06.10.2017.
 */
class MeterViewPresenter {

    private final MeterView view;
    private final Meter meter;

    MeterViewPresenter(MeterView view, Meter meter) {
        this.view = view;
        this.meter = meter;
    }

    void onErfassen() {

        if (! view.isInputDataValid()) {
            return;
        }

        final Meter.MeterReading meterReading = view.getInputData();

        // interact with the model
        if (meter.isPlausible(meterReading)) {
            // update the model
            meter.addReading(meterReading);

        } else {
            // update the view
            view.renderErrorMessage("Zählerstand nicht plausibel.");
        }

    }

}
