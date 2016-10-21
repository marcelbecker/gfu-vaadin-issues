package de.gfu.vaadin.ui.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.theme.MyTheme;

/**
 * Created by mbecker on 29.07.2016.
 */
public class VerticalActionBar extends VerticalLayout {

    private ActionBarListener actionBarListener;

    public VerticalActionBar() {
        setSpacing(true);

        addComponent(new Button("Neue Seite", this::onNewPage));
        addComponent(new Button("Abmelden", this::onLogout));
        addComponent(new Button("Alle Issues", this::onIssues));

        setSizeFull();
        addStyleName(MyTheme.CssClass.LEFT_BAR);
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

    private void onIssues(Button.ClickEvent clickEvent) {
        actionBarListener.onIssuesPage();
    }

    public static interface ActionBarListener {
        void onLogOut();
        void onNewPage();
        void onIssuesPage();
    }

}
