package de.gfu.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import de.gfu.vaadin.model.Lead;
import de.gfu.vaadin.model.LeadsSynchronization;

import java.util.List;

/**
 * Created by MBecker on 09.10.2017.
 */
public class LeadsView implements View, LeadsSynchronization.LeadListener {

    private Grid<Lead> grid;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        grid = new Grid<>();
        LeadsSynchronization.addListener(this);


    }

    @Override
    public void update(List<Lead> leads) {
        grid.setItems(leads);
    }

    @Override
    public UI getUI() {
        return null;
    }
}
