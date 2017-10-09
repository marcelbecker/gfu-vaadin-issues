package de.gfu.vaadin.widgets;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import de.gfu.vaadin.widgets.client.DisableOnClickButtonSharedState;

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
    }

    @Override
    protected DisableOnClickButtonSharedState getState() {
        return (DisableOnClickButtonSharedState) super.getState();
    }
}
