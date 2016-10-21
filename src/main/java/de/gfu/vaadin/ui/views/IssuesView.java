package de.gfu.vaadin.ui.views;

import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Issue;

import static com.vaadin.server.Responsive.makeResponsive;
import static de.gfu.vaadin.persistence.IssueRepository.findIssuesBy;
import static de.gfu.vaadin.support.Strings.abbreviatedAt;

/**
 * Created by mbecker on 21.10.2016.
 */
public class IssuesView extends CssLayout implements View  {


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        setSizeFull();

        addStyleName("gfu-dashboard");

        makeResponsive(this);

        findIssuesBy(issue -> true).forEach(this::addPanel);

    }

    private void addPanel(Issue issue) {
        VerticalLayout layout = new VerticalLayout(new Label(abbreviatedAt(issue.getContent(), 100)));
        Panel panel = new Panel(issue.getTitle(), layout);
        panel.addClickListener(this::toggleSize);
        addComponent(panel);
    }

    private void toggleSize(MouseEvents.ClickEvent event) {
        Component clickedComponent = event.getComponent();
        if (0 > clickedComponent.getWidth()) {
            clickedComponent.setWidth(500, Unit.PIXELS);
            clickedComponent.setHeight(500, Unit.PIXELS);
        } else {
            clickedComponent.setSizeUndefined();
        }
    }
}
