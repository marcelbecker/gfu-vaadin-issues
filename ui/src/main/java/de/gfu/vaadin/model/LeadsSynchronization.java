package de.gfu.vaadin.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 *
 * Created by MBecker on 09.10.2017.
 */
public class LeadsSynchronization {


    static List<WeakReference<LeadListener>> listeners = new ArrayList<>();

    static List<Lead> leads = new ArrayList<>();


    public static void addListener(LeadListener leadListener) {
        listeners.add(new WeakReference<>(leadListener));
    }

    public static void addLead(Lead lead) {
        leads.add(lead);

        listeners.forEach(l -> {
            ofNullable(l.get()).ifPresent(listener -> {
                listener.update(leads);
            });
        });
    }

    public static void remove(Lead lead) {
        if (leads.remove(lead)) {

            listeners.forEach(l -> {
                ofNullable(l.get()).ifPresent(listener -> {
                    listener.update(leads);
                });
            });
        }
    }

    public static List<Lead> getLeads() {
        return Collections.unmodifiableList(leads);
    }

    public interface LeadListener {

        void update(List<Lead> leads);

    }
}
