package de.gfu.vaadin.ui.components.forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import de.gfu.vaadin.authentication.ui.Account;
import de.gfu.vaadin.model.User;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_FRIENDLY;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_LINK;
import static de.gfu.vaadin.support.Users.asHashedPassword;

/**
 * Created by mbecker on 29.07.2016.
 */
public class UserForm extends FormLayout {

    @Caption("Anmeldename")
    private TextField loginName;

    @Caption("Spitzname")
    private TextField longName;

    private PasswordField passwordField;

    private User user;

    private BeanFieldGroup<User> fieldGroup;

    private OkListener okListener;

    private BackListener backListener;

    public UserForm(User user) {
        this.user = user;

        passwordField = new PasswordField("Passwort");
        fieldGroup = new BeanFieldGroup<>(User.class);
        fieldGroup.buildAndBindMemberFields(this);
        fieldGroup.setItemDataSource(user);

        loginName.setNullRepresentation("");
        longName.setNullRepresentation("");

        passwordField.setImmediate(false);
        loginName.setImmediate(false);
        longName.setImmediate(false);

//        Table table = new Table("Accounts", new BeanItemContainer<>(Account.class));
//        Item item = table.addItem(new Account("t.account", "Test", "Person", "t@mail.com"));
//addComponent(table);
//        BeanFieldGroup<Account> accountFG = new BeanFieldGroup<>(Account.class);
//        accountFG.setItemDataSource(item);
//        Field<?> field = accountFG.buildAndBind("Benutzername", "benutzername");
//        addComponent(field);

        Button registerButton = new Button("Registrieren", this::onRegister);
        registerButton.addStyleName(BUTTON_FRIENDLY);

        Button backButton = new Button("Zurück", this::onBack);
        backButton.addStyleName(BUTTON_LINK);

        HorizontalLayout buttonGroup = new HorizontalLayout(registerButton, backButton);

        addComponent(loginName);
        addComponent(passwordField);
        addComponent(longName);
        addComponent(buttonGroup);
    }

    public void setOkListener(OkListener okListener) {
        this.okListener = okListener;
    }

    public void setBackListener(BackListener backListener) {
        this.backListener = backListener;
    }

    protected void onRegister(Button.ClickEvent clickEvent) {
        try {
            fieldGroup.commit();
            passwordField.commit();
        } catch (FieldGroup.CommitException e) {
            throw new RuntimeException(e);
        }
        user.setPassword(asHashedPassword(passwordField.getValue()));
        okListener.onOk(user);
    }

    protected void onBack(Button.ClickEvent clickEvent) {
        backListener.onBack();
    }

    public static interface OkListener {
        void onOk(User user);
    }

    public static interface BackListener {
        void onBack();
    }
}
