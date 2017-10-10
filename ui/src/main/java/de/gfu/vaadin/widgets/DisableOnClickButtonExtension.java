package de.gfu.vaadin.widgets;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Component;
import de.gfu.vaadin.widgets.client.DisableOnClickButtonSharedState;
import de.gfu.vaadin.widgets.client.MyComponentServerRpc;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
public class DisableOnClickButtonExtension extends AbstractExtension {

    private final String pleaseWaitLabel;
    private final Integer shortCutKey;

    public DisableOnClickButtonExtension(String pleaseWaitLabel) {
        this(pleaseWaitLabel, null);
    }

    public DisableOnClickButtonExtension(String pleaseWaitLabel,
                                         Integer shortCutKey) {
        this.pleaseWaitLabel = pleaseWaitLabel;
        this.shortCutKey = shortCutKey;
    }

    @Override
    public void extend(AbstractClientConnector target) {
        super.extend(target);
        getState().pleaseWaitLabel = pleaseWaitLabel;
        getState().shortCutKey = shortCutKey;
        registerRpc((MyComponentServerRpc) () ->
                ((Component) target).setEnabled(false));
    }

    @Override
    protected DisableOnClickButtonSharedState getState() {
        return (DisableOnClickButtonSharedState) super.getState();
    }


}
