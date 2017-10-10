package de.gfu.vaadin.widgets.client;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorMap;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.button.ButtonConnector;
import com.vaadin.client.ui.orderedlayout.VAbstractOrderedLayout;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.button.ButtonServerRpc;
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

        final String pleaseWaitLabel;
        if (getState().pleaseWaitLabel != null) {
            pleaseWaitLabel = getState().pleaseWaitLabel;
        } else {
            pleaseWaitLabel = "Please wait...";
        }

        final int key;
        if (getState().shortCutKey != null) {
            key = getState().shortCutKey;
        } else {
            key = -1;
        }

        RootPanel.get().addDomHandler(keyDownEvent -> {
            if (keyDownEvent.getNativeKeyCode() == key) {
                transform(vButton, pleaseWaitLabel, serverConnector);
            }
        }, KeyDownEvent.getType());

        vButton.addClickHandler(clickEvent -> {
            transform(vButton, pleaseWaitLabel, serverConnector);
        });

    }

    @Override
    public DisableOnClickButtonSharedState getState() {
        return (DisableOnClickButtonSharedState) super.getState();
    }

    private void transform(VButton vButton, String label, ServerConnector serverConnector) {
        vButton.setEnabled(false);
        vButton.addStyleName(DISABLED_CLASSNAME);
        vButton.setText(label);
        RpcProxy.create(MyComponentServerRpc.class, this).disabled();
    }


}
