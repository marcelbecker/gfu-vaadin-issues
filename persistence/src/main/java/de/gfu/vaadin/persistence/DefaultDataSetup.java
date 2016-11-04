package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.support.Users;

/**
 * Created by mbecker on 07.10.2016.
 */
public class DefaultDataSetup {

    public static void withDefaultUser() {
        User defaultUser = new User();
        defaultUser.setLoginName("mb");
        defaultUser.setPassword(Users.asHashedPassword("mb"));

        UserRepository.save(defaultUser);
    }

    public static void withDefaultData() {

        Issue issue1 = new Issue();
        issue1.setTitle("Button umbenennen");
        issue1.setContent("Button 'Neue Seite' sollte 'Neues Issue' heißen.");

        Issue issue2 = new Issue();
        issue2.setTitle("Abmelden-Button ganz nach unten");
        issue2.setContent("Der Abmelden-Button soll ganz unten platziert werden.");

        Issue issue3 = new Issue();
        issue3.setTitle("Responsive Dashboard");
        issue3.setContent("Das Dashboard soll responsiv sein.");

        Issue issue4 = new Issue();
        issue4.setTitle("Dashboard verlinken");
        issue4.setContent("Alle Issues auf dem Dashboard sollten klickbar sein und auf die Detail-Ansicht verlinken." +
                " Außerdem sollten sie sich vergrößern, wenn man darauf klickt, und verkleinern, wenn man ein zweites" +
                " Mal darauf klickt.");

        Issue issue5 = new Issue();
        issue5.setTitle("Button umbenennen");
        issue5.setContent("Button 'Neue Seite' sollte 'Neues Issue' heißen.");

        Issue issue6 = new Issue();
        issue6.setTitle("Button umbenennen");
        issue6.setContent("Button 'Neue Seite' sollte 'Neues Issue' heißen.");

        ItemRepository.save(issue1);
        ItemRepository.save(issue2);
        ItemRepository.save(issue3);
        ItemRepository.save(issue4);
        ItemRepository.save(issue5);
        ItemRepository.save(issue6);

    }

}
