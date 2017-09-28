package de.gfu.vaadin.mvp;

import de.gfu.vaadin.model.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by MBecker on 29.09.2017.
 */
public class PresenterTest {
    
    UserFormComponent componentMock = Mockito.mock(UserFormComponent.class);
    Backend backendMock = Mockito.mock(Backend.class);
    UserFormComponent.Presenter presenter = 
            new UserFormComponent.Presenter(componentMock, backendMock);

    @Test
    public void saveUserWithUnknownUser() throws Exception {
        // case 1 : User is unknown to backend
        when(backendMock.getUser(anyString()))
                .thenReturn(Optional.empty());

        // when
        presenter.saveUser(new User());

        // then
        backendMock.postUser(any());
    }

    @Test
    public void saveUserWithKnownUser() throws Exception {
        // case 2 : User is known to backend
        when(backendMock.getUser(anyString()))
                .thenReturn(Optional.of(new User()));

        // when
        presenter.saveUser(new User());

        // then
        componentMock.setComponentError(any());
    }
}