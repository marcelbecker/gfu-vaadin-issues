package de.gfu.vaadin.example;

import com.vaadin.server.VaadinRequest;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * Created by MBecker on 31.10.2017.
 */
public class AnsprachetextUITest {

    @Test
    public void init() throws Exception {
        new AnsprachetextUI().init(mock(VaadinRequest.class));
    }

}