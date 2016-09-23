package de.gfu.vaadin.ui.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.ui.views.MyTheme;

/**
 * Created by mbecker on 29.07.2016.
 */
public class VerticalActionBar extends VerticalLayout {

    private ActionBarListener actionBarListener;

    public VerticalActionBar() {
        setSpacing(true);

        Button newPage = new Button("Neue Seite", this::onNewPage);
        Button logoutButton = new Button("Abmelden", this::onLogout);

        addComponent(newPage);
        addComponent(logoutButton);

        setSizeFull();
        addStyleName(MyTheme.LEFT_BAR);
    }

    public void setActionBarListener(ActionBarListener actionBarListener) {
        this.actionBarListener = actionBarListener;
    }

    private void onLogout(Button.ClickEvent clickEvent) {
        actionBarListener.onLogOut();
    }

    private void onNewPage(Button.ClickEvent clickEvent) {
        actionBarListener.onNewPage();
    }

    public static interface ActionBarListener {
        void onLogOut();
        void onNewPage();
    }

}
