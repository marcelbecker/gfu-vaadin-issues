package de.gfu.vaadin.ui.layout;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.*;
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
        actionBar.setSizeUndefined();

        mainPanel = new Panel();
        mainPanel.setSizeUndefined();
        mainPanel.addStyleName(MyTheme.CssClass.MAIN_PANEL);
        mainPanel.addStyleName(MyTheme.CssClass.CENTER_PANEL);

        addComponent(actionBar);
        addComponent(mainPanel);

        addStyleName(MyTheme.CssClass.VACATION_BACKGROUND);
        setSizeFull();
        GridLayout grid = new GridLayout();
        for (int i = 0; i < 3; i++) {
            grid.addComponent(new Button("Col " + (grid.getCursorX() + 1)));
        }

        AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.addComponent(mainPanel, "");

        // A 400x250 pixels size layout
        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setWidth("400px");
        layout.setHeight("250px");

// A component with coordinates for its top-left corner
        TextField text = new TextField("Somewhere someplace");
        layout.addComponent(text, "left: 50px; top: 50px;");

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

            @Override
            public void onIssuesPage() {
                ViewController.showShowIssueView();
            }
        };
    }

    @Override
    public void showView(View view) {
        mainPanel.setContent((Component) view);
    }
}
