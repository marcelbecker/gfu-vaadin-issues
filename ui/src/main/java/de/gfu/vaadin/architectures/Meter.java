package de.gfu.vaadin.architectures;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import static java.math.BigDecimal.ROUND_HALF_DOWN;
import static java.math.BigDecimal.valueOf;

/**
 *
 * Meter represents our domain model. It is an abstraction for a power meter account that stores several
 * meter readings and calculates plausible forecasts for future power consumption.
 *
 *
 * Created by MBecker on 06.10.2017.
 */
public class Meter extends Observable {


    private final List<MeterReading> readings = new ArrayList<>();

    public Meter() {
        final MeterReading initialReading = new MeterReading();
        initialReading.setReadingDate(LocalDate.now().minusMonths(1));
        initialReading.setReadingValue(valueOf(1120.2));

        readings.add(initialReading);
    }

    public List<MeterReading> getReadings() {
        return Collections.unmodifiableList(readings);
    }

    public void addReading(MeterReading meterReading) {
        readings.add(meterReading);
        setChanged();
        notifyObservers();
    }

    public boolean isPlausible(MeterReading meterReading) {

        if (readings.isEmpty()) {
            return true;
        }

        final MeterReading lastReading = readings.get(readings.size() - 1);

        if (meterReading.getReadingValue().compareTo(lastReading.getReadingValue()) < 0) {
            return false;
        }

        final BigDecimal averagePowerConsumption = averagePowerConsumption();

        final BigDecimal powerConsumption =

                meterReading.getReadingValue()

                        .subtract(lastReading.getReadingValue());

        final BigDecimal delta =

                averagePowerConsumption

                        .subtract(powerConsumption)
                        .abs();

        // Delta between power consumption and average power consumption should be
        // lower than the average power consumption including a buffer factor.
        final BigDecimal buffer = valueOf(1.5);
        return delta.compareTo(averagePowerConsumption.multiply(buffer)) < 0;
    }

    public BigDecimal averagePowerConsumption() {
        final MeterReading initialReading = readings.get(0);
        final MeterReading lastReading = readings.get(readings.size() - 1);

        if (readings.size() == 1) {
            return lastReading.getReadingValue();

        } else {
            return lastReading.getReadingValue()

                    .subtract(initialReading.getReadingValue())

                            .divide(valueOf(readings.size() - 1), ROUND_HALF_DOWN);
        }
    }


    /**
     *
     * MeterReading represents the result of a meter reading.
     *
     */
    public static class MeterReading {

        private LocalDate readingDate = LocalDate.now();
        private BigDecimal readingValue;

        public void setReadingDate(LocalDate readingDate) {
            this.readingDate = readingDate;
        }

        public void setReadingValue(BigDecimal readingValue) {
            this.readingValue = readingValue;
        }

        public LocalDate getReadingDate() {
            return readingDate;
        }

        public BigDecimal getReadingValue() {
            return readingValue;
        }

    }



}
