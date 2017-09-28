package de.gfu.vaadin.mvp;


import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.model.User;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by MBecker on 26.09.2017.
 */
public class UserDisplay extends CustomComponent implements Observer {


    private final Label longNameLabel;
    private final Label loginNameLabel;
    private final Label passwordLabel;

    public UserDisplay(User user) {

        longNameLabel = new Label();
        loginNameLabel = new Label();
        passwordLabel = new Label();

        update(user);

        setCompositionRoot(
                new VerticalLayout(longNameLabel, loginNameLabel, passwordLabel));

    }

    private void update(User user) {
        longNameLabel.setValue(user.getLongName());
        loginNameLabel.setValue(user.getLoginName());
        passwordLabel.setValue("***");
    }

    @Override
    public void update(Observable o, Object arg) {
        User user = (User) o;
        update(user);
    }
}
