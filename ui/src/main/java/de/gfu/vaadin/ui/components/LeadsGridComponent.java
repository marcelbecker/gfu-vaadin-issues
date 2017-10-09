package de.gfu.vaadin.ui.components;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Lead;
import de.gfu.vaadin.model.LeadsService;
import de.gfu.vaadin.model.LeadsSynchronization;

import java.util.List;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
public class LeadsGridComponent extends CustomComponent implements LeadsSynchronization.LeadListener {

    private final Grid<Lead> grid;
    private final LeadsService service;
    private final Binder<Lead> leadBinder;

    @Override
    public void update(List<Lead> leads) {
        grid.setItems(leads);
    }

    @Override
    public void detach() {
        super.detach();
        LeadsSynchronization.removeListener(this);
    }

    class LeadForm {
         TextField source = new TextField("Quelle");
         TextField description = new TextField("Beschreibung");
         ComboBox<String> status = new ComboBox<>("Status");
         DateField date = new DateField("Datum");
    }


    public LeadsGridComponent(LeadsService service) {
        this.service = service;

        LeadsSynchronization.addListener(this);

        grid = new Grid<>(Lead.class);
        grid.setCaption("Aktuelle Leads");
        grid.setWidth(800, Unit.PIXELS);
        grid.setItems(service.leads());

        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                onDeleteLead(event.getItem());
            }
        });

        final LeadForm leadForm = new LeadForm();

        leadBinder = new Binder<>(Lead.class);
        leadBinder.bindInstanceFields(leadForm);
        leadBinder.setBean(new Lead());

        leadForm.status.setItems("Offen", "In Bearbeitung", "Fertig");

        final FormLayout formLayout = new FormLayout(
                leadForm.source,
                leadForm.description,
                leadForm.status,
                leadForm.date,
                new Button("Hinzufügen", e -> onAddLead())
        );
        formLayout.setCaption("Neuer Lead");

        final HorizontalLayout horizontalLayout = new HorizontalLayout(formLayout, grid);

        setCompositionRoot(horizontalLayout);

    }

    void onDeleteLead(Lead lead) {
        service.remove(lead);
        getUI().addWindow(new Window("Info", new Label("Lead gelöscht!")));
    }

    void onAddLead() {
        service.add(leadBinder.getBean());
        leadBinder.setBean(new Lead());
    }


}
