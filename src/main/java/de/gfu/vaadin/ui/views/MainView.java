package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import de.gfu.vaadin.model.Page;
import de.gfu.vaadin.ui.auth.CurrentUser;
import de.gfu.vaadin.ui.components.PageDisplay;
import de.gfu.vaadin.ui.components.VerticalActionBar;
import de.gfu.vaadin.ui.components.forms.PageForm;

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

        PageDisplay pageDisplay = new PageDisplay(null);
        mainPanel.setContent(pageDisplay);

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
                PageForm pageForm = new PageForm(new Page());
                mainPanel.setContent(pageForm);
                pageForm.addListener((Listener) MainView.this::onNewPage);
                Notification.show("WHASSS UUUPP!");
            }
        };
    }

    public void onNewPage(Component.Event event) {
        if (event instanceof PageForm.OkEvent) {
            Page page = ((PageForm.OkEvent) event).getPage();
            mainPanel.setContent(new PageDisplay(page));
            mainPanel.setCaption("Seite " + page.getId());
        }
    }

}
