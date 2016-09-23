package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.ui.auth.CurrentUser;
import de.gfu.vaadin.ui.components.IssueDisplay;
import de.gfu.vaadin.ui.components.VerticalActionBar;
import de.gfu.vaadin.ui.components.forms.IssueForm;

/**
 * Created by mbecker on 29.07.2016.
 */
public class MainView extends CssLayout implements View {

    private Panel mainPanel;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        VerticalActionBar actionBar = new VerticalActionBar();
        actionBar.setActionBarListener(getActionBarListener());
        actionBar.setWidth(19.1f, Unit.PERCENTAGE);

        mainPanel = new Panel();
        mainPanel.setSizeUndefined();
        mainPanel.addStyleName(MyTheme.MAIN_PANEL);
        mainPanel.addStyleName(MyTheme.CENTER_PANEL);

        IssueDisplay issueDisplay = new IssueDisplay(null);
        mainPanel.setContent(issueDisplay);

        addComponent(actionBar);
        addComponent(mainPanel);

        addStyleName(MyTheme.VACATION_BACKGROUND);
        setSizeFull();
    }

    private VerticalActionBar.ActionBarListener getActionBarListener() {
        return new VerticalActionBar.ActionBarListener() {
            @Override
            public void onLogOut() {
                CurrentUser.set(null);
                ViewController.showLoginView();
            }

            @Override
            public void onNewPage() {
                IssueForm issueForm = new IssueForm(new Issue());
                mainPanel.setContent(issueForm);
                issueForm.addListener((Listener) MainView.this::onNewPage);
            }
        };
    }

    public void onNewPage(Component.Event event) {
        if (event instanceof IssueForm.OkEvent) {
            Issue issue = ((IssueForm.OkEvent) event).getIssue();
            mainPanel.setContent(new IssueDisplay(issue));
            mainPanel.setCaption("Seite " + issue.getId());
        }
    }

}
