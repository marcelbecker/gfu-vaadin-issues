package de.gfu.vaadin.ui.uis;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;


/**
 * Created by mbecker on 21.10.2016.
 */
public class MediaQueryActivation implements BootstrapListener {
    @Override
    public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
    }

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        Attributes attributes = new Attributes();
        attributes.put("name", "viewport");
        attributes.put("content", "width=device-width, initial-scale=1.0");
        Element meta = new Element(Tag.valueOf("meta"), "", attributes);
        response.getDocument().head().appendChild(meta);
    }
}