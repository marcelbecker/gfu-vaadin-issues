package de.gfu.vaadin.ui.uis;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import de.gfu.vaadin.authentication.view.LoginView;
import de.gfu.vaadin.authentication.view.RegisterView;
import de.gfu.vaadin.persistence.DefaultDataSetup;
import de.gfu.vaadin.theme.MyTheme;

import javax.servlet.annotation.WebServlet;

import java.util.Locale;

import static com.vaadin.server.Sizeable.Unit.PIXELS;
import static de.gfu.vaadin.theme.MyTheme.CssClass.VACATION_BACKGROUND;

/**
 * Created by mbecker on 07.10.2016.
 */
@Theme(ValoTheme.THEME_NAME)
public class StandardUI extends UI {

public class User {

    Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

    public class Account {
        private String benutzername;
        private String vorname;
        private String nachname;
        private String email;
        public Account(String benutzername,
                       String vorname,
                       String nachname,
                       String email) {
            this.benutzername = benutzername;
            this.vorname = vorname;
            this.nachname = nachname;
            this.email = email;
        }

        public String getBenutzername() {
            return benutzername;
        }

        public void setBenutzername(String benutzername) {
            this.benutzername = benutzername;
        }

        public String getVorname() {
            return vorname;
        }

        public void setVorname(String vorname) {
            this.vorname = vorname;
        }

        public String getNachname() {
            return nachname;
        }

        public void setNachname(String nachname) {
            this.nachname = nachname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }


    @Override
    protected void init(VaadinRequest request) {

//        Account account = new Account("t.account", "Test", "Person", "t@mail.com");
//        TextField benutzername = new TextField("Benutzername", new ObjectProperty<>(account.getBenutzername()));
//        TextField vorname = new TextField("Vorname", new ObjectProperty<>(account.getVorname()));
//        TextField nachname = new TextField("Nachname", new ObjectProperty<>(account.getNachname()));
//        TextField email = new TextField("E-Mail", new ObjectProperty<>(account.getEmail()));
//
//        assert benutzername.getValue() == benutzername.getPropertyDataSource().getValue();

//        FormLayout formLayout = new FormLayout(benutzername, vorname, nachname, email);
//        formLayout.setSpacing(true);
//        formLayout.setMargin(true);
//        setContent(formLayout);

//        Account account = new Account("t.account", "Test", "Person", "t@mail.com");
//        TextField benutzername = new TextField("Benutzername", account.getBenutzername());
//        TextField vorname = new TextField("Vorname", account.getVorname());
//        TextField nachname = new TextField("Nachname", account.getNachname());
//        TextField email = new TextField("E-Mail", account.getEmail());
//
//        Label benutzernameLabel = new Label(benutzername);
//
//        FormLayout formLayout = new FormLayout(benutzername, benutzernameLabel, vorname, nachname, email);
//        formLayout.setSpacing(true);
//        formLayout.setMargin(true);
//        setContent(formLayout);

//        Account account = new Account("t.account", "Test", "Person", "t@mail.com");
//        Item item = new PropertysetItem();
//        item.addItemProperty("benutzername", new ObjectProperty<>(account.getBenutzername()));
//        item.addItemProperty("vorname", new ObjectProperty<>(account.getVorname()));
//        new TextField("Benutzername", item.getItemProperty("benutzername"));
//        new TextField("Vorname", item.getItemProperty("vorname"));
//
//
//        setContent(new FormLayout(
//        new TextField("Benutzername", item.getItemProperty("benutzername")),
//        new TextField("Vorname", item.getItemProperty("vorname"))
//        ));

        Account account = new Account("t.account", "Test", "Person", "t@mail.com");
        User user = new User();
        user.setAccount(account);

        BeanItem<User> userBeanItem = new BeanItem<>(user);
        userBeanItem.addNestedProperty("account.benutzername");

        new TextField("Benutzername", userBeanItem.getItemProperty("account.benutzername"));



//        new TextField("Benutzername", item.getItemProperty("benutzername"));
//        new TextField("Vorname", item.getItemProperty("vorname"));

        BeanFieldGroup<Account> fieldGroup = new BeanFieldGroup<>(Account.class);
        fieldGroup.setItemDataSource(account);

        TextField benutzername = (TextField) fieldGroup.buildAndBind("benutzername");
        TextField vorname = (TextField) fieldGroup.buildAndBind("vorname");

        setContent(new FormLayout(
                new TextField("Benutzername", userBeanItem.getItemProperty("account.benutzername"))
        ));

//        TabSheet tabSheet = new TabSheet();
//        tabSheet.addTab(new Label(sampleTxt()), "Tab Nummer 1");
//        tabSheet.addTab(new Label(sampleTxt()), "Tab Nummer 2");
//        tabSheet.addTab(new Label(sampleTxt()), "Tab Nummer 3");
//
//
//        Accordion accordion = new Accordion();
//        accordion.addTab(new Label(sampleTxt()), "Tab Nummer 1");
//        accordion.addTab(new Label(sampleTxt()), "Tab Nummer 2");
//        accordion.addTab(new Label(sampleTxt()), "Tab Nummer 3");
//
//
//        VerticalLayout verticalLayout = new VerticalLayout(new Label("TabSheet"), tabSheet, new Label("Accordion"), accordion);
//        verticalLayout.setWidth(500, PIXELS);
//        verticalLayout.setMargin(true);
//        verticalLayout.setSpacing(true);
//
//        TextField textField = new TextField("Eingabe");
//        Label text = new Label();
//        text.setPropertyDataSource(textField);
//        text.setConverter(new Converter<String, String>() {
//            String originalValue;
//
//            @Override
//            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
//                return originalValue;
//            }
//
//            @Override
//            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
//                originalValue = value;
//                return value.toUpperCase();
//            }
//
//            @Override
//            public Class<String> getModelType() {
//                return String.class;
//            }
//
//            @Override
//            public Class<String> getPresentationType() {
//                return String.class;
//            }
//        });
//        verticalLayout.addComponents(textField, text);
//
//        setContent(verticalLayout);

    }

    private String sampleTxt() {
        return "Neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci velit";
    }

    @WebServlet(urlPatterns = "/standard/*", name = "StandardServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = StandardUI.class, productionMode = true, heartbeatInterval = 15)
    public static class StandardUIServlet extends VaadinServlet {
    }

}
