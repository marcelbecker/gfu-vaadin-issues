package de.gfu.vaadin.ui.layout;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import de.gfu.vaadin.authentication.AuthenticationController;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.VerticalActionBar;
import de.gfu.vaadin.ui.views.ViewController;

/**
 * Created by mbecker on 07.10.2016.
 */
public class MainLayout extends CssLayout implements ViewDisplay {

    Panel mainPanel;

    public MainLayout() {

        VerticalActionBar actionBar = new VerticalActionBar();
        actionBar.setActionBarListener(getActionBarListener());
        actionBar.setWidth(19.1f, Unit.PERCENTAGE);

        mainPanel = new Panel();
        mainPanel.setSizeUndefined();
        mainPanel.addStyleName(MyTheme.CssClass.MAIN_PANEL);
        mainPanel.addStyleName(MyTheme.CssClass.CENTER_PANEL);

        addComponent(actionBar);
        addComponent(mainPanel);

        addStyleName(MyTheme.CssClass.VACATION_BACKGROUND);
        setSizeFull();

    }

    private VerticalActionBar.ActionBarListener getActionBarListener() {
        return new VerticalActionBar.ActionBarListener() {
            @Override
            public void onLogOut() {
                AuthenticationController.logout();
            }

            @Override
            public void onNewPage() {
                ViewController.showEditIssueView();
            }
        };
    }

    @Override
    public void showView(View view) {
        mainPanel.setContent((Component) view);
    }
}
