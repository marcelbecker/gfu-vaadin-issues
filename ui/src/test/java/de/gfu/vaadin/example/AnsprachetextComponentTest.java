package de.gfu.vaadin.example;

import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Dieser Test zeigt, dass einfache Komponenten bereits mit einer sehr guten
 * Line Coverage über das Public Interface der Komponente getestet werden
 * können. Mit diesem einfachen BlackBox-Test erreichen wir 98% Line Coverage.
 * <br/><br/>
 * Wir testen allerdings nur das Verhalten am Public Interface, nicht die
 * UI-Logik, welche die Benutzer sehen. Wenn das gewünscht ist, muss ein Presenter
 * aus der Komponente herausgezogen werden. Da die Logik hier fast trivial ist,
 * verzichte ich darauf.
 * <br/><br/>
 * Created by MBecker on 31.10.2017.
 */
public class AnsprachetextComponentTest {

    private final AnsprachetextComponent ansprachetextComponent =
            new AnsprachetextComponent();

    @Test
    public void testConstruction() throws Exception {
        assertNotNull(ansprachetextComponent);
    }

    @Test
    public void testSetAndGetValue() throws Exception {
        assertNull(ansprachetextComponent.getValue());
        ansprachetextComponent.setValue("TEST");
        assertEquals("TEST", ansprachetextComponent.getValue());
    }

    @Test
    public void testOnValueChange() throws Exception {
        final Consumer listenerMock = mock(Consumer.class);
        ansprachetextComponent.onValueChange(listenerMock);
        ansprachetextComponent.setValue("NEU");
        ansprachetextComponent.setValue("NEU");
        verify(listenerMock, times(1)).accept("NEU");
    }

    @Test
    public void testSetDefaultValue() throws Exception {
        final String defaultPresetValue = AnsprachetextComponent.defaultPresetValue();
        ansprachetextComponent.setValue(defaultPresetValue);
        assertEquals(defaultPresetValue, ansprachetextComponent.getValue());
    }
}