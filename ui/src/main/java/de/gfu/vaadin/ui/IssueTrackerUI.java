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
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Type;
import de.gfu.vaadin.persistence.DefaultDataSetup;
import de.gfu.vaadin.persistence.ItemRepository;
import de.gfu.vaadin.theme.MyTheme;
import de.gfu.vaadin.ui.components.MainLayout;
import de.gfu.vaadin.ui.forms.IssueForm;
import de.gfu.vaadin.ui.views.EditIssueView;
import de.gfu.vaadin.ui.views.ErrorView;
import de.gfu.vaadin.ui.views.IssuesView;

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

        final MainLayout mainLayout = new MainLayout();

        final Navigator navigator = new Navigator(this, mainLayout);
        navigator.addView("", IssuesView.class);
        navigator.addView("issues", IssuesView.class);
        navigator.addView("editIssue", EditIssueView.class);

        navigator.addViewChangeListener(e -> {
            System.out.println(e.getOldView() + " -> " + e.getNewView());
            return true;
        });

        navigator.setErrorView(ErrorView.class);

        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = "/issues/*", name = "IssueTrackerServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IssueTrackerUI.class, productionMode = false, heartbeatInterval = 300)
    public static class IssueTrackerUIServlet extends VaadinServlet {
    }

}
