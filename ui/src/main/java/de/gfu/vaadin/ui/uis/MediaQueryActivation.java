package de.gfu.vaadin.ui.uis;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;


/**
 * Implementation of {@link com.vaadin.server.BootstrapListener} that adds the following meta tag to the bootstrap page:
 *
 * <p>
 *  <b>
 *     &lt;meta name="viewport" content="width=device-width, initial-scale=1.0" /&gt;
 *  </b>
 * </p>
 *
 * <p>This tag enables the CSS media queries.</p>
 *
 * @author Created by mbecker on 21.10.2016.
 */
public class MediaQueryActivation implements BootstrapListener {

    public static final String DEFAULT_VIEWPORT_CONFIGURATION = "width=device-width, initial-scale=1.0";

    @Override
    public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
    }

    /**
     * Adds the meta tag to the bootstrap page. Is called by Vaadin during bootstrap phase.
     * @param response the response object
     */
    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        Attributes attributes = new Attributes();
        attributes.put("name", "viewport");
        attributes.put("content", DEFAULT_VIEWPORT_CONFIGURATION);
        Element meta = new Element(Tag.valueOf("meta"), "", attributes);
        response.getDocument().head().appendChild(meta);
    }
}