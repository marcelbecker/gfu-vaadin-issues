package de.gfu.vaadin.model;

import java.util.List;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
public class LeadsService {

    public void add(Lead lead) {
        LeadsSynchronization.addLead(lead);
    }

    public void remove(Lead lead) {
        LeadsSynchronization.remove(lead);
    }

    public List<Lead> leads() {
        return LeadsSynchronization.getLeads();
    }

}
