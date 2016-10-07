package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Item;
import de.gfu.vaadin.persistence.ItemRepository;
import de.gfu.vaadin.ui.components.IssueDisplay;
import de.gfu.vaadin.ui.components.forms.IssueForm;

import java.util.Optional;

import static de.gfu.vaadin.ui.views.ViewController.showEditIssueView;

/**
 * Created by mbecker on 07.10.2016.
 */
public class ShowIssueView extends CustomComponent implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();

        String parameters = event.getParameters();

        Optional<Item> itemOptional = ItemRepository.findItemById(parameters);

        if (! itemOptional.isPresent()) {
            setCompositionRoot(new Label("Issue nicht gefunden."));
            return;
        }

        Issue issue = (Issue) itemOptional.get();

        IssueDisplay issueDisplay = new IssueDisplay(issue);

        IssueForm issueForm = new IssueForm(issue);
        issueForm.setReadOnly(true);

        Button editButton = new Button("Bearbeiten", ev -> showEditIssueView(issue.getId()));
        editButton.addStyleName(ValoTheme.BUTTON_PRIMARY);


        VerticalLayout compositionRoot = new VerticalLayout(issueForm, editButton);
        compositionRoot.setSpacing(true);
        compositionRoot.setComponentAlignment(editButton, Alignment.MIDDLE_CENTER);

        setCompositionRoot(compositionRoot);

    }

}
