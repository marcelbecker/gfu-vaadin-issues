package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 * Created by MBecker on 28.09.2017.
 */
public class ErrorView extends CustomComponent implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setCompositionRoot(new Label("Seite nicht gefunden."));
    }

}
