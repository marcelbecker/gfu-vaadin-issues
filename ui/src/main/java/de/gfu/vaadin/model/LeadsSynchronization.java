package de.gfu.vaadin.model;

import com.vaadin.ui.UI;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public static void removeListener(LeadListener leadListener) {
        final Optional<WeakReference<LeadListener>> any = listeners.stream()
                .filter(wl -> wl.get() == leadListener)
                .findAny();
        any.ifPresent( l -> listeners.remove(l));
    }

    public static void addLead(Lead lead) {
        leads.add(lead);

        listeners.forEach(l -> {
            ofNullable(l.get()).ifPresent(listener -> {
                update(listener, leads);
            });
        });
    }

    public static void remove(Lead lead) {
        if (leads.remove(lead)) {

            listeners.forEach(l -> {
                ofNullable(l.get()).ifPresent(listener -> {
                    update(listener, leads);
                });
            });
        }
    }

    public static List<Lead> getLeads() {
        return Collections.unmodifiableList(leads);
    }

    static void update(LeadListener leadListener, List<Lead> leads) {
        final UI ui = leadListener.getUI();
        if (ui == null) {
            throw new RuntimeException("This should not happen!");
        }

        System.out.println("Updating UI " + ui);

        // This is necessary, because ... TODO
        ui.access( () -> leadListener.update(leads) );
    }

    public interface LeadListener {

        void update(List<Lead> leads);

        UI getUI();

    }
}
