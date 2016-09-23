package gfu.vaadin.samples.layouts;

import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mbecker on 01.07.2016.
 */
public class FooterLayout extends CssLayout {


    BuyPresenter buyPresenter = new BuyPresenter();
    public FooterLayout(Component... children) {
        super(children);
    }

    class BuyPresenter {
        void buy() {};
    }

    class UserData {
        String id;
        String name;
        String email;
        boolean premium;
    }

    public static class Product {
        private String id;
        @Size(min = 1, max = 255)
        private String name;
        @Size(max = 2500)
        private String description;
        @Digits(integer = 6, fraction = 2)
        private BigDecimal price;

        public Product(String name) {
            this.name = name;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    List<UserData> getUserData() {
        return Collections.emptyList();
    }

    List<Product> getProducts() {
        return Arrays.asList(new Product("Maus"), new Product("Kaffeetasse"), new Product("Handy"));
    }

    @Override
    public void attach() {
        super.attach();
        setSizeFull();
        addStyleName("gfu-footer");

        Container container = new BeanItemContainer<>(Product.class, getProducts());
        ComboBox comboBox = new ComboBox("Produkte", container);
        comboBox.setItemCaptionPropertyId("name");

        TextField name = fieldGroup.buildAndBind("Produktname", "name", TextField.class);
        addComponent(name);
        BeanFieldGroup<Product> fieldGroup = new BeanFieldGroup<>(Product.class);
        fieldGroup.setItemDataSource(new Product("Monitor"));
        try {
            fieldGroup.commit();
        } catch (FieldGroup.CommitException e) {
            e.getInvalidFields();
        }

        addComponent(comboBox);

        Button button = new Button("Kaufen");
        button.addClickListener(this::buy);

    }

    public void buy(Button.ClickEvent event) {
        event.getButton().setEnabled(false);
        buyPresenter.buy();
    }

    @Override
    protected String getCss(Component c) {
        return "background-color: black; color: white; width: 100%;";
    }
}
