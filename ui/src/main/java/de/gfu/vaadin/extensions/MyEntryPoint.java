package de.gfu.vaadin.extensions;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by MBecker on 06.10.2017.
 */
public class MyEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new MyWidget());
    }
}
