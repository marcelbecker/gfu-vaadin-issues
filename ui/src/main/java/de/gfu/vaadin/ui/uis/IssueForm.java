package de.gfu.vaadin.ui.uis;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import de.gfu.vaadin.model.User;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Created by mbecker on 18.11.2016.
 */
public class IssueForm {

    public TextField title;

    public RichTextArea content;

    public ComboBox type;

    public InlineDateField deadline;

    public TextField created;

    public TextField longName = new TextField("User");
//    public TextField user = new TextField("User");
//    public TextField user = new TextField("User");
//
//    public IssueForm() {
//        user.setConverter(new UserConverter());
//    }

    public static class UserConverter implements Converter<String, User> {

        private User user;

        @Override
        public User convertToModel(String value, Class<? extends User> targetType, Locale locale) throws ConversionException {
            return user;
        }

        @Override
        public String convertToPresentation(User user, Class<? extends String> targetType, Locale locale) throws ConversionException {
            this.user = user;
            return Optional.ofNullable(user).map(User::getLongName).filter(Objects::nonNull).orElse("");
        }

        @Override
        public Class<User> getModelType() {
            return User.class;
        }

        @Override
        public Class<String> getPresentationType() {
            return String.class;
        }
    }
}
