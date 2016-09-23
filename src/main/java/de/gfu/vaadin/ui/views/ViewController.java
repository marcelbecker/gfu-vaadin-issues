package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

/**
 * Created by mbecker on 29.07.2016.
 */
class ViewController {

    public static void registerViews(Navigator navigator) {
        navigator.addView("", MainView.class);
        navigator.addView("login", LoginView.class);
        navigator.addView("register", RegisterView.class);
    }

    public static void showLoginView() {
        UI.getCurrent().getNavigator().navigateTo("login");
    }

    public static void showMainView() {
        UI.getCurrent().getNavigator().navigateTo("");
    }

    public static void showRegisterView() {
        UI.getCurrent().getNavigator().navigateTo("register");
    }
}
