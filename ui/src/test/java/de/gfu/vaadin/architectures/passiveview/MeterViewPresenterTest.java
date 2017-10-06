package de.gfu.vaadin.architectures.passiveview;

import de.gfu.vaadin.architectures.Meter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 *
 * Created by MBecker on 06.10.2017.
 */
public class MeterViewPresenterTest {

    private final Meter meterMock = mock(Meter.class);
    private final MeterView viewMock = mock(MeterView.class);

    private final MeterViewPresenter presenter =
            new MeterViewPresenter(viewMock, meterMock);

    @Before
    public void setUp() throws Exception {
        reset(viewMock, meterMock);
        when(viewMock.isInputDataValid())
                .thenReturn(true);
        when(meterMock.isPlausible(any()))
                .thenReturn(true);
    }

    @Test
    public void onErfassen_onInvalidInput() throws Exception {
        when(viewMock.isInputDataValid())
                .thenReturn(false);

        presenter.onErfassen();

        verify(viewMock, never()).getInputData();
    }

    @Test
    public void onErfassen_onNonPlausibleInput() throws Exception {
        when(meterMock.isPlausible(any()))
                .thenReturn(false);

        presenter.onErfassen();

        verify(viewMock).renderErrorMessage(any());
    }

    @Test
    public void onErfassen_validAndPlausibleInput() throws Exception {

        presenter.onErfassen();

        verify(meterMock).addReading(any());
        verify(viewMock).setFooterText(any());
        verify(viewMock).setInputData(any());
        verify(viewMock).setReadingsList(any());
    }
}