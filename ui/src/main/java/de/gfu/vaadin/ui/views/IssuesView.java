package de.gfu.vaadin.ui.views;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.application.SessionObjects;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.persistence.IssueRepository;
import de.gfu.vaadin.support.Issues;
import de.gfu.vaadin.support.Predicates;

import java.util.List;

/**
 * Created by MBecker on 28.09.2017.
 */
public class IssuesView extends CustomComponent implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final Grid<Issue> grid = new Grid<>(Issue.class);
        grid.setColumns("title", "content", "type", "status", "created");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.getColumn("content").setWidth(600);
        grid.addItemClickListener(e -> onEditIssue(e.getItem()));


        List<Issue> issueList = IssueRepository.findIssuesBy(Predicates::all);

        DataProvider<Issue, ?> dataProvider = DataProvider.ofCollection(issueList);
        grid.setDataProvider(dataProvider);

        final Button addIssueButton = new Button("Neues Issue", e -> {
            User user = SessionObjects.stubUser();
            onEditIssue(Issues.newIssue(user));
        });

        setCompositionRoot(new VerticalLayout(grid, addIssueButton));


    }

    private void onEditIssue(Issue issue) {

        getUI().getSession().setAttribute(Issue.class, issue);
        getUI().getNavigator().navigateTo("editIssue");

    }

}
