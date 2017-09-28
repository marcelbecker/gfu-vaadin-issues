package de.gfu.vaadin.ui.components;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.*;
import com.vaadin.ui.*;

import java.io.File;

/**
 * Created by MBecker on 28.09.2017.
 */
public class MainLayout extends CustomComponent implements ViewDisplay {
    @Override
    public void showView(View view) {
        final Panel panel = new Panel();

        panel.setContent(view.getViewComponent());


        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

        final FileResource fileResource =
                new FileResource(new File(basepath + "/WEB-INF/images/vaadin.png"));
        final ExternalResource externalResource =
                new ExternalResource("http://www.gfu.net/");
        final ThemeResource themeResource =
                new ThemeResource("images/vaadin.png");


        final Image fileImage = new Image(null, fileResource);
        final HorizontalLayout horizontalLayout = new HorizontalLayout(fileImage,
                new Link("GFU Cyrus", externalResource), new Image(null, themeResource));


        final MenuBar menuBar = new MenuBar();

        menuBar.addItem("Alle Issues",
                selectedItem -> getUI().getNavigator().navigateTo("issues"));
        menuBar.addItem("Neues Issue",
                selectedItem -> getUI().getNavigator().navigateTo("editIssue"));
        menuBar.addItem("Abmelden",
                selectedItem -> getUI().getNavigator().navigateTo("logout"));

        final Button fileDownload = new Button("Download File");
        final Button themeImageDownload = new Button("Download Theme Image");
        new FileDownloader(fileResource)
                .extend(fileDownload);
        new FileDownloader(themeResource)
                .extend(themeImageDownload);
        setCompositionRoot(
                new VerticalLayout(new Label("Oben"),
                        menuBar,
                        panel,
                        fileDownload,
                        themeImageDownload,
                        horizontalLayout,
                        new Label("Unten")));
    }
}
