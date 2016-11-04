package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.ui.components.forms.IssueForm;

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
        panel.addClickListener(event -> showDetailWindow(issue));
        addComponent(panel);
    }

    private void showDetailWindow(Issue issue) {
        Window window = new Window(issue.getTitle());
        IssueForm issueForm = new IssueForm(issue);
        issueForm.setReadOnly(true);
        window.setContent(issueForm);
        window.setModal(true);
        window.setWindowMode(WindowMode.NORMAL);
        window.addStyleName("gfu-issue-detail-window");

        Responsive.makeResponsive(window);

        UI.getCurrent().addWindow(window);
    }
}
