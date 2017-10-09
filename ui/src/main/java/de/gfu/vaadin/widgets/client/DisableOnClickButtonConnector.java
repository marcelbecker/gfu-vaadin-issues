package de.gfu.vaadin.widgets.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VButton;
import com.vaadin.shared.ui.Connect;
import de.gfu.vaadin.widgets.DisableOnClickButtonExtension;

import static com.vaadin.client.ApplicationConnection.DISABLED_CLASSNAME;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
@Connect(DisableOnClickButtonExtension.class)
public class DisableOnClickButtonConnector extends AbstractExtensionConnector {

    @Override
    protected void extend(ServerConnector serverConnector) {

        final Widget widget = ((ComponentConnector) serverConnector).getWidget();
        final VButton vButton = (VButton) widget;

        String pleaseWaitLabel = "Please wait...";

        RootPanel.get().addDomHandler(keyDownEvent -> {
            if (keyDownEvent.getNativeKeyCode() == 13) {
                transform(vButton, pleaseWaitLabel);
            }
        }, KeyDownEvent.getType());

        vButton.addClickHandler(clickEvent -> {
            transform(vButton, pleaseWaitLabel);
        });
    }

    private void transform(VButton vButton, String label) {
        vButton.setEnabled(false);
        vButton.addStyleName(DISABLED_CLASSNAME);
        vButton.setText(label);
    }


}
