package de.gfu.vaadin.ui.components;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.*;

/**
 * Created by MBecker on 28.09.2017.
 */
public class MainLayout extends CustomComponent implements ViewDisplay {
    @Override
    public void showView(View view) {
        final Panel panel = new Panel();

        panel.setContent(view.getViewComponent());

        final MenuBar menuBar = new MenuBar();

        menuBar.addItem("Alle Issues",
                selectedItem -> getUI().getNavigator().navigateTo("issues"));
        menuBar.addItem("Neues Issue",
                selectedItem -> getUI().getNavigator().navigateTo("editIssue"));
        menuBar.addItem("Abmelden",
                selectedItem -> getUI().getNavigator().navigateTo("logout"));

        setCompositionRoot(
                new VerticalLayout(new Label("Oben"),
                        menuBar,
                        panel, new Label("Unten")));
    }
}
