package de.gfu.vaadin.ui.components;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import de.gfu.vaadin.model.Lead;
import de.gfu.vaadin.model.LeadsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
public class LeadsGridComponentTest {

    LeadsService leadsService = Mockito.mock(LeadsService.class);

    LeadsGridComponent gridComponent
            = new LeadsGridComponent(leadsService);

    UI dummyUI;

    @Before
    public void setUp() throws Exception {

        dummyUI = spy(new UI() {
            @Override
            protected void init(VaadinRequest request) {

            }
        });

        dummyUI.setContent(gridComponent);

    }

    @Test
    public void testOnAddLead() throws Exception {

        // when
        gridComponent.onAddLead();

        // then
        verify(leadsService).add(any());

    }

    @Test
    public void testOnDeleteLead() throws Exception {
        final Lead lead = new Lead();

        // when
        gridComponent.onDeleteLead(lead);

        // then
        verify(leadsService).remove(lead);
        verify(dummyUI).addWindow(any());

    }

    @Test
    public void testUpdate() throws Exception {

        gridComponent.update(new ArrayList<>());

    }
}