package de.gfu.vaadin.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.persistence.DefaultDataSetup;
import de.gfu.vaadin.persistence.IssueRepository;
import de.gfu.vaadin.support.Predicates;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.LoginComponent;

import javax.servlet.annotation.WebServlet;

import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;

/**
 *
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
public class IssueTrackerUI extends UI {

    @Override
    protected void init(VaadinRequest request) {


        DefaultDataSetup.withDefaultData();


        addStyleName(VACATION_BACKGROUND);

        final Grid<Issue> grid = new Grid<>(Issue.class);
        grid.setColumns("title", "content", "type", "status", "created");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.getColumn("content").setWidth(600);


        grid.setItems(IssueRepository.findIssuesBy(Predicates::all));


        final Panel panel = new Panel(grid);
        panel.setWidth(80, Unit.PERCENTAGE);



        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel,
                Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    @WebServlet(urlPatterns = "/issues/*", name = "IssueTrackerServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IssueTrackerUI.class, productionMode = false, heartbeatInterval = 300)
    public static class IssueTrackerUIServlet extends VaadinServlet {}

}
