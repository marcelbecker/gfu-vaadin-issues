package de.gfu.vaadin.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDateConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.validator.RangeValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.gfu.vaadin.application.SessionObjects;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Type;
import de.gfu.vaadin.model.User;
import de.gfu.vaadin.persistence.DefaultDataSetup;
import de.gfu.vaadin.persistence.IssueRepository;
import de.gfu.vaadin.persistence.ItemRepository;
import de.gfu.vaadin.support.Issues;
import de.gfu.vaadin.support.Predicates;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.forms.IssueForm;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.List;

import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;

/**
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
public class IssueTrackerUI extends UI {

    private DataProvider<Issue, ?> dataProvider;
    private List<Issue> issueList;

    @Override
    protected void init(VaadinRequest request) {


        DefaultDataSetup.withDefaultData();


        addStyleName(VACATION_BACKGROUND);

        final Grid<Issue> grid = new Grid<>(Issue.class);
        grid.setColumns("title", "content", "type", "status", "created");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.getColumn("content").setWidth(600);
        grid.addItemClickListener(e -> onEditIssue(e.getItem()));


        issueList = IssueRepository.findIssuesBy(Predicates::all);

        dataProvider = DataProvider.ofCollection(issueList);
        grid.setDataProvider(dataProvider);

        final Button addIssueButton = new Button("Neues Issue", e -> {
            User user = SessionObjects.stubUser();
            onEditIssue(Issues.newIssue(user));
        });


        final Panel panel = new Panel(new VerticalLayout(grid, addIssueButton));
        panel.setWidth(80, Unit.PERCENTAGE);


        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel,
                Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    private void onEditIssue(Issue i) {

        final Window window = new Window("Issue bearbeiten ...");

        final IssueForm form = new IssueForm();

        Binder<Issue> issueBinder = new BeanValidationBinder<>(Issue.class);

        issueBinder.forField(form.created)
                .withNullRepresentation("")
                .withConverter(new StringToDateConverter())
                .bind(Issue::getCreated, Issue::setCreated);
        issueBinder.forField(form.deadline)
                .withConverter(new LocalDateToDateConverter())
                .withValidator(new RangeValidator<>("Max. 10 Tage in der Zukunft.",
                        Date::compareTo, new Date(), maxDateInTheFuture(10)))
                .bind(Issue::getDeadline, Issue::setDeadline);
        issueBinder
                .bind(form.user,
                        (issue) -> issue.getUser().getLongName(),
                        (issue, name) -> issue.getUser().setLongName(name));
        issueBinder.forField(form.type)
                .withValidator((value, context) -> value == Type.BUG
                        ? ValidationResult.error("Keine Bugs!")
                        : ValidationResult.ok())
                .bind(Issue::getType, Issue::setType);

        issueBinder.bindInstanceFields(form);
        issueBinder.setBean(i);

        form.created.setReadOnly(true);
        form.user.setReadOnly(true);



        final FormLayout formLayout = new FormLayout(
                form.title,
                form.type,
                form.content,
                form.deadline,
                form.user,
                form.created,
                new HorizontalLayout(
                    new Button("Speichern", e -> {
                        ItemRepository.save(issueBinder.getBean());
                        if (! issueList.contains(issueBinder.getBean())) {
                            issueList.add(issueBinder.getBean());
                        }
                        dataProvider.refreshAll();

                        window.close();
                    }),
                    new Button("Abbrechen", e -> window.close())));
        formLayout.setSizeUndefined();

        window.setContent(formLayout);
        window.setModal(true);

        getUI().addWindow(window);

    }

    private Date maxDateInTheFuture(int days) {
        return new Date(new Date().getTime() + (1000*60*60*24*days));
    }

    @WebServlet(urlPatterns = "/issues/*", name = "IssueTrackerServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IssueTrackerUI.class, productionMode = false, heartbeatInterval = 300)
    public static class IssueTrackerUIServlet extends VaadinServlet {
    }

}
