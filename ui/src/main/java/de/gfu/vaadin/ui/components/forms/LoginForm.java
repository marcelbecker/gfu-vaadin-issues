package de.gfu.vaadin.ui.components.forms;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;
import de.gfu.vaadin.model.User;

import java.lang.invoke.LambdaMetafactory;
import java.util.EventObject;

import static com.vaadin.event.ShortcutAction.KeyCode.ENTER;

/**
 * Created by mbecker on 29.07.2016.
 */
public class LoginForm extends CustomComponent {

    private TextField loginName;

    private PasswordField password;

    private LoginListener loginListener;

    private RegisterListener registerListener;
    private final Button loginButton;
    private final Button registerButton;

    public LoginForm() {
        loginName = new TextField("Anmeldename");
        password = new PasswordField("Passwort");

        loginButton = new Button("Anmelden", this::login);
        registerButton = new Button("Registrieren", this::register);

        ObjectProperty<String> loginNameProperty = new ObjectProperty<>("");
        loginName.setPropertyDataSource(loginNameProperty);

        BeanItem<User> userBeanItem = new BeanItem<>(new User());
        loginName.setPropertyDataSource(userBeanItem.getItemProperty("loginName"));

        loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        registerButton.addStyleName(ValoTheme.BUTTON_LINK);

        loginButton.setClickShortcut(ENTER);

        HorizontalLayout hl = new HorizontalLayout(loginButton, registerButton);
        hl.setSpacing(true);

        Field field = new TextField();
        field.addValueChangeListener(this::onValueChange);

        ClientConnector label = new Label();
        label.addAttachListener(this::onAttach);
        label.addDetachListener(this::onDetach);

        FieldEvents.FocusNotifier focusable = new Button();
        focusable.addFocusListener(this::onFocus);
        FieldEvents.BlurNotifier blurable = new Button();
        blurable.addBlurListener(this::onBlur);

        Window window = new Window();
        window.addResizeListener(this::onResize);
        window.addBlurListener(this::onBlur);

        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(loginName);
        formLayout.addComponent(password);
        formLayout.addComponent(hl);

        setCompositionRoot(formLayout);
    }

    private void onFocus(FieldEvents.FocusEvent focusEvent) {

    }

    private void onBlur(FieldEvents.BlurEvent blurEvent) {

    }

    private void onDetach(DetachEvent detachEvent) {

    }

    private void onAttach(AttachEvent attachEvent) {
    }

    private void onResize(Window.ResizeEvent resizeEvent) {

    }

    private void onValueChange(Property.ValueChangeEvent valueChangeEvent) {

    }

    @Override
    public void setCaption(String caption) {
        new Panel().setCaption(caption);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setRegisterListener(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }

    private void login(Button.ClickEvent clickEvent) {
        loginName.commit();
        password.commit();

        User user = new User();
        BeanItem<User> userBeanItem = new BeanItem<>(user);

        loginName.setPropertyDataSource(userBeanItem.getItemProperty("loginName"));
        loginName.setPropertyDataSource(userBeanItem.getItemProperty("password"));

        String loginName = this.loginName.getValue();
        String password = this.password.getValue();

        loginListener.onLogin(loginName, password);
    }

    public void clearPassword() {
        this.password.clear();
    }

    @Override
    public void setComponentError(ErrorMessage componentError) {
        password.setComponentError(componentError);
    }

    public static interface LoginListener {
        void onLogin(String loginName, String password);
    }

    public static interface RegisterListener {
        void onRegister();
    }

    private void register(Button.ClickEvent clickEvent) {
        registerListener.onRegister();
    }
}
