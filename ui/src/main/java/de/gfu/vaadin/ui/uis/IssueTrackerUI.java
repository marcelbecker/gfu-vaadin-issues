package de.gfu.vaadin.ui.uis;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.gfu.vaadin.model.Issue;
import de.gfu.vaadin.model.Status;
import de.gfu.vaadin.model.Type;
import de.gfu.vaadin.persistence.DefaultDataSetup;
import de.gfu.vaadin.persistence.IssueRepository;
import de.gfu.vaadin.persistence.ItemRepository;
import de.gfu.vaadin.support.Predicates;
import de.gfu.vaadin.theme.MyTheme;

import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.util.*;
import java.util.Calendar;

import static com.vaadin.shared.ui.datefield.Resolution.DAY;
import static de.gfu.vaadin.application.SessionObjects.stubUser;
import static de.gfu.vaadin.support.Issues.newIssue;
import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;
import static de.gfu.vaadin.ui.uis.IssueTrackerUI.IssueFormLayout.DoneEvent.ResultType.CANCEL;
import static de.gfu.vaadin.ui.uis.IssueTrackerUI.IssueFormLayout.DoneEvent.ResultType.SAVE;

/**
 * Created by mbecker on 07.10.2016.
 */
@Theme(MyTheme.NAME)
public class IssueTrackerUI extends UI {

    private Table table;
    private BeanItemContainer<Issue> container;

    @Override
    protected void init(VaadinRequest request) {

        DefaultDataSetup.withDefaultData();

//        getSession().setConverterFactory(new DefaultConverterFactory() {
//            @Override
//            public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> createConverter(Class<PRESENTATION> presentationType, Class<MODEL> modelType) {
//                if (User.class == modelType && String.class == presentationType) {
//                    return (Converter<PRESENTATION, MODEL>) new IssueForm.UserConverter();
//                }
//                return super.createConverter(presentationType, modelType);
//            }
//        });

        addStyleName(VACATION_BACKGROUND);

        table = new Table("Alle Offenen Issues");

        table.addGeneratedColumn("status", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                Property property = source.getContainerProperty(itemId, columnId);
                ComboBox comboBox = new ComboBox("");
                comboBox.addItems(EnumSet.allOf(Status.class));
                comboBox.setPropertyDataSource(property);
                comboBox.setImmediate(false);
                comboBox.addValueChangeListener(event -> {
                    ItemRepository.save((Issue) itemId);
                    String caption = "Status von Item " + ((Issue) itemId).getId() + " auf " +
                            event.getProperty().getValue() +
                            " geändert.";
                    Notification notification = new Notification(caption);
                    notification.setDelayMsec(10000);
                    notification.setPosition(Position.MIDDLE_CENTER);
                    notification.show(Page.getCurrent());
                });
                return comboBox;
            }
        });

        container = new BeanItemContainer<>(Issue.class);

        container.addAll(IssueRepository.findIssuesBy(Predicates::all));

        table.setContainerDataSource(container);

        table.setVisibleColumns("title", "content", "type", "status", "created");
        table.setColumnExpandRatio("content", 1f);
        table.setWidth(100, Unit.PERCENTAGE);
        table.setPageLength(container.size());
        table.setSelectable(true);
        table.addItemClickListener(this::onTableClick);
//        table.setColumnWidth("content", 200);

        Button newIssueBtn = new Button("Neues Issue", event -> openIssueFormWindow(newIssue(stubUser())));

        Panel panel = new Panel("Offene Issues", new VerticalLayout(table, newIssueBtn));
        panel.addStyleName(MyTheme.CssClass.CENTER_PANEL);
        panel.setWidth(80, Unit.PERCENTAGE);

        VerticalLayout verticalLayout = new VerticalLayout(panel);
        verticalLayout.setSizeFull();
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    private void onTableClick(ItemClickEvent event) {
        openIssueFormWindow((Issue) event.getItemId());
    }

    private void openIssueFormWindow(Issue issue) {
        Window window = new Window("Issue " + issue.getId() + " bearbeiten");
        IssueFormLayout issueFormLayout = new IssueFormLayout(issue);

        issueFormLayout.addDoneListener(event -> {
            window.close();
            if (((IssueFormLayout.DoneEvent) event).getResultType() == SAVE) {
                if (container.containsId(issue)) {
                    table.refreshRowCache();
                } else {
                    container.addBean(issue);
                }
            }
        });


        window.setContent(issueFormLayout);
        window.setModal(true);

        UI.getCurrent().addWindow(window);
    }

    public static class IssueFormLayout extends FormLayout {

        private final BeanFieldGroup<Issue> fieldGroup;

        public IssueFormLayout(Issue issue) {

            fieldGroup = new BeanFieldGroup<>(Issue.class);
            fieldGroup.setItemDataSource(issue);
            IssueForm issueForm = new IssueForm();
            fieldGroup.buildAndBindMemberFields(issueForm);
//            TextField userField = new TextField();
//            fieldGroup.bind(issueForm.user, "user.longName");
            fieldGroup.bind(issueForm.longName, "user.longName");

            addComponents(issueForm.title, issueForm.type, issueForm.content, issueForm.deadline,
                    issueForm.created, issueForm.longName);


            issueForm.created.setReadOnly(true);
            issueForm.longName.setReadOnly(true);
            issueForm.content.addValidator(new StringLengthValidator("Max. Länge von 512 überschritten.", 0, 512, true));
            issueForm.type.addValidator(new Validator() {
                @Override
                public void validate(Object value) throws InvalidValueException {
                    if (value == Type.BUG) {
                        throw new InvalidValueException("Bitte keine Bugs einstellen :)");
                    }
                }
            });
            java.util.Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, 10);
            issueForm.deadline.addValidator(
                    new DateRangeValidator("Datum {0} liegt zu weit in der Zukunft.", null, calendar.getTime(), DAY));

            Button saveBtn = new Button("Speichern", this::onSave);
            Button cancelBtn = new Button("Abbrechen", this::onCancel);
            saveBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);

            HorizontalLayout horizontalLayout = new HorizontalLayout(saveBtn, cancelBtn);
            horizontalLayout.setSpacing(true);
            addComponents(horizontalLayout);

            // Find the application directory
            String basepath = VaadinService.getCurrent()
                    .getBaseDirectory().getAbsolutePath();

// Image as a file resource
            FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/vaadin.png"));

            ThemeResource themeResource = new ThemeResource("images/vacation.jpg");

            ExternalResource externalResource = new ExternalResource("http://www.gfu.net");
            ExternalResource internalResource = new ExternalResource("/aus");

// Show the image in the application
            Image image = new Image("Image from file", resource);
            Image themeImage = new Image("Image from file", themeResource);

// Let the user view the file in browser or download it
            Link link = new Link("Link to the image file", resource);
            Link externalLink = new Link("Link to the image file", externalResource);

            addComponents(new Link("Aus", internalResource));
            FileDownloader fileDownloader = new FileDownloader(resource);
            Button downloadBtn = new Button("Download");
            fileDownloader.extend(downloadBtn);
            addComponents(downloadBtn);

            setWidthUndefined();
            setSpacing(true);
            setMargin(true);

        }




        private void onSave(Button.ClickEvent event) {
            try {
                fieldGroup.commit();
            } catch (FieldGroup.CommitException e) {
                return;
            }
            ItemRepository.save(fieldGroup.getItemDataSource().getBean());
            getListeners(Component.Event.class)
                    .forEach(listener -> ((Listener) listener).componentEvent(new DoneEvent(this, SAVE)));
        }

        private void onCancel(Button.ClickEvent event) {
            getListeners(Component.Event.class)
                    .forEach(listener -> ((Listener) listener).componentEvent(new DoneEvent(this, CANCEL)));
        }


        public void addDoneListener(Listener listener) {
            this.addListener(listener);
        }

        public static class DoneEvent extends Event {
            private final ResultType resultType;

            public static enum ResultType {
                SAVE, CANCEL
            }
            /**
             * Constructs a new event with the specified source component.
             *
             * @param source the source component of the event
             */
            public DoneEvent(Component source, ResultType resultType) {
                super(source);
                this.resultType = resultType;
            }

            public ResultType getResultType() {
                return resultType;
            }
        }
    }

    @WebServlet(urlPatterns = "/issues/*", name = "IssueTrackerServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IssueTrackerUI.class, productionMode = true, heartbeatInterval = 15)
    public static class IssueTrackerUIServlet extends VaadinServlet {
    }

}
