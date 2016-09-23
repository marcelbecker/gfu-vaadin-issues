package de.gfu.vaadin.ui.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.gfu.vaadin.model.Page;

/**
 * Created by mbecker on 29.07.2016.
 */
public class PageDisplay extends VerticalLayout {

    private final Label content;

    private Page page;

    public PageDisplay(Page page) {
        setSizeFull();

        content = new Label();
        content.setContentMode(ContentMode.HTML);
        addComponent(content);
        setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        setPage(page);
    }

    public final void setPage(Page page) {
        this.page = page;
        if (page == null) {
            content.setValue("<span style=\"font-size: 35px;\">Willkommen</span>");
        } else {
            this.page = page;
            content.setValue(page.getContent());
        }
    }
}
