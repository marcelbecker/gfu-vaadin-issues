package de.gfu.vaadin.model;

import com.vaadin.ui.UI;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by MBecker on 09.10.2017.
 */
public class LeadsSynchronizationTest {
    private UI uiMock = Mockito.mock(UI.class);

    @Test
    public void update() throws Exception {


        final LeadsSynchronization.LeadListener listener = new LeadsSynchronization.LeadListener() {

            @Override
            public void update(List<Lead> leads) {

            }

            @Override
            public UI getUI() {
                return uiMock;
            }
        };

        // when
        LeadsSynchronization.addListener(listener);
        final Lead lead = new Lead();
        LeadsSynchronization.addLead(lead);

        // then
        verify(uiMock).access(any());
        Mockito.reset(uiMock);


        // when
        LeadsSynchronization.remove(new Lead());

        // then
        verify(uiMock, never()).access(any());
        Mockito.reset(uiMock);

        // when
        LeadsSynchronization.remove(lead);

        // then
        verify(uiMock).access(any());
        Mockito.reset(uiMock);


        // when
        LeadsSynchronization.removeListener(listener);
        LeadsSynchronization.addLead(new Lead());

        // then
        verify(uiMock, never()).access(any());

    }

}