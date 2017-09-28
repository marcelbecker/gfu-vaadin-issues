package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Type;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.support.Issues;
import de.gfu.vaadin.support.Users;

/**
 * Created by mbecker on 07.10.2016.
 */
public class DefaultDataSetup {

    public static void withDefaultUser() {
        User defaultUser = new User();
        defaultUser.setLoginName("mb");
        defaultUser.setPassword("mb");

        UserRepository.save(defaultUser);
    }

    public static void withDefaultData() {

        ItemRepository.clear();

        User user = Users.firstUser();

        Issue issue1 = Issues.newIssue(user);
        issue1.setTitle("Button umbenennen");
        issue1.setContent("Button 'Neue Seite' sollte 'Neues Issue' heißen.");
        issue1.setType(Type.TASK);

        Issue issue2 = Issues.newIssue(user);
        issue2.setTitle("Abmelden-Button ganz nach unten");
        issue2.setContent("Der Abmelden-Button soll ganz unten platziert werden.");
        issue2.setType(Type.TASK);

        Issue issue3 = Issues.newIssue(user);
        issue3.setTitle("Responsive Dashboard");
        issue3.setContent("Das Dashboard soll responsiv sein.");
        issue3.setType(Type.EPIC);

        Issue issue4 = Issues.newIssue(user);
        issue4.setTitle("Dashboard verlinken");
        issue4.setContent("Alle Issues auf dem Dashboard sollten klickbar sein und auf die Detail-Ansicht verlinken." +
                " Außerdem sollten sie sich vergrößern, wenn man darauf klickt, und verkleinern, wenn man ein zweites" +
                " Mal darauf klickt.");
        issue4.setType(Type.STORY);

        Issue issue5 = Issues.newIssue(user);
        issue5.setTitle("Button umbenennen");
        issue5.setContent("Button 'Neue Seite' sollte 'Neues Issue' heißen.");
        issue5.setType(Type.TASK);

        Issue issue6 = Issues.newIssue(user);
        issue6.setTitle("Menü");
        issue6.setContent("Ein Menü soll folgende Funktionen anbieten: Abmelden, ....");
        issue6.setType(Type.STORY);

        ItemRepository.save(issue1);
        ItemRepository.save(issue2);
        ItemRepository.save(issue3);
        ItemRepository.save(issue4);
        ItemRepository.save(issue5);
        ItemRepository.save(issue6);

    }

}
