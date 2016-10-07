package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Item;
import de.gfu.vaadin.persistence.ItemRepository;
import de.gfu.vaadin.ui.components.forms.IssueForm;

import java.util.Optional;

/**
 * Created by mbecker on 07.10.2016.
 */
public class EditIssueView extends CustomComponent implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


        String parameters = event.getParameters();


        Optional<Item> itemOptional = ItemRepository.findItemById(parameters);


        IssueForm issueForm = new IssueForm((Issue) itemOptional.orElse(new Issue()));


        issueForm.addListener((Event okEvent) -> {
            Issue issue = ((IssueForm.OkEvent) okEvent).getIssue();
            ItemRepository.save(issue);
            ViewController.showShowIssueView(issue.getId());
        });


        setCompositionRoot(issueForm);


        setSizeFull();

    }

}
