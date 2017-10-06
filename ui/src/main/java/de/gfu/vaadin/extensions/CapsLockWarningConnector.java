package de.gfu.vaadin.extensions;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VOverlay;

/**
 * Created by MBecker on 06.10.2017.
 */
public class CapsLockWarningConnector extends AbstractExtensionConnector {
    @Override
    protected void extend(ServerConnector serverConnector) {
        final Widget widget =
                ((ComponentConnector) serverConnector).getWidget();


        // Preparations for the added feature
        final VOverlay warning = new VOverlay();
        warning.setOwner(widget);
        warning.add(new HTML("Caps Lock is enabled!"));
    }
}
