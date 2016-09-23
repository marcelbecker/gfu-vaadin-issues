package gfu.vaadin.samples.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * A simple Component that contains two components and the ability to toggle between them.
 * Created by mbecker on 01.07.2016.
 */
public class ToggleComponent extends CustomComponent {

    private final Component[] components;
    private byte componentIndex;

    public ToggleComponent(Component component1, Component component2) {
        components = new Component[] {component1, component2};
        setCompositionRoot(component1);
    }

    public void toggle() {
        setCompositionRoot(components[componentIndex ^= 1]);
    }
}
