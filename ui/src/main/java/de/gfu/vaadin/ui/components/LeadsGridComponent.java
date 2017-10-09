package de.gfu.vaadin.ui.components;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Lead;
import de.gfu.vaadin.model.LeadsSynchronization;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
public class LeadsGridComponent extends CustomComponent {

     class LeadForm {
         TextField source = new TextField("Quelle");
         RichTextArea description = new RichTextArea("Beschreibung");
         ComboBox<String> status = new ComboBox<>("Status");
         DateField date = new DateField("Datum");
    }


    public LeadsGridComponent() {

        final Grid<Lead> grid = new Grid<>(Lead.class);
        grid.setItems(LeadsSynchronization.getLeads());

        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                LeadsSynchronization.remove(event.getItem());
                getUI().addWindow(new Window("Info", new Label("Lead gelöscht!")));
            }
        });

        final LeadForm leadForm = new LeadForm();

        final Binder<Lead> leadBinder = new Binder<>(Lead.class);
        leadBinder.bindInstanceFields(leadForm);
        leadBinder.setBean(new Lead());

        leadForm.status.setItems("Offen", "In Bearbeitung", "Fertig");

        setCompositionRoot(new VerticalLayout(grid, new FormLayout(
                leadForm.source,
                leadForm.description,
                leadForm.status,
                leadForm.date,
                new Button("Hinzufügen", e -> LeadsSynchronization.addLead(leadBinder.getBean()))
        )));

    }



}
