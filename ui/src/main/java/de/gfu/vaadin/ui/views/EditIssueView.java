package de.gfu.vaadin.ui.views;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDateConverter;
import com.vaadin.data.validator.RangeValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Type;
import de.gfu.vaadin.persistence.ItemRepository;
import de.gfu.vaadin.ui.forms.IssueForm;

import java.util.Date;

/**
 * Created by MBecker on 28.09.2017.
 */
public class EditIssueView extends CustomComponent implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
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
        issueBinder.setBean(getUI().getSession().getAttribute(Issue.class));

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
                            getUI().getNavigator().navigateTo("issues");
                        }),
                        new Button("Abbrechen")));
        formLayout.setSizeUndefined();

        setCompositionRoot(formLayout);

    }

    private Date maxDateInTheFuture(int days) {
        return new Date(new Date().getTime() + (1000*60*60*24*days));
    }

}
