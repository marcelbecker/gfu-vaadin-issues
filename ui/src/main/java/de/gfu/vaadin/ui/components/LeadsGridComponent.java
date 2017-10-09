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

    @Override
    public void update(List<Lead> leads) {
        final UI ui = getUI();
        if (ui == null) {
            throw new RuntimeException("This should not happen!");
        }

        System.out.println("Updating UI " + ui);

        // This is necessary, because ... TODO
        ui.access( () -> grid.setItems(leads) );
    }

    @Override
    public void detach() {
        super.detach();
        LeadsSynchronization.removeListener(this);
    }

    class LeadForm {
         TextField source = new TextField("Quelle");
         RichTextArea description = new RichTextArea("Beschreibung");
         ComboBox<String> status = new ComboBox<>("Status");
         DateField date = new DateField("Datum");
    }


    public LeadsGridComponent(LeadsService service) {
        this.service = service;

        LeadsSynchronization.addListener(this);

        grid = new Grid<>(Lead.class);
        grid.setItems(service.leads());

        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                service.remove(event.getItem());
                getUI().addWindow(new Window("Info", new Label("Lead gelöscht!")));
            }
        });

        final LeadForm leadForm = new LeadForm();

        final Binder<Lead> leadBinder = new Binder<>(Lead.class);
        leadBinder.bindInstanceFields(leadForm);
        leadBinder.setBean(new Lead());

        leadForm.status.setItems("Offen", "In Bearbeitung", "Fertig");

        final VerticalLayout verticalLayout = new VerticalLayout(new FormLayout(
                leadForm.source,
                leadForm.description,
                leadForm.status,
                leadForm.date,
                new Button("Hinzufügen", e -> service.add(leadBinder.getBean()))
        ), grid);

        verticalLayout.setSizeUndefined();

        setCompositionRoot(verticalLayout);

    }



}
