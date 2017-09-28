package de.gfu.vaadin.mvp;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by MBecker on 29.09.2017.
 */
public class MobileTextFieldPresenterTest {
    @Test
    public void testClearTextField() throws Exception {
        // given
        final MobileTextField componentMock = Mockito.mock(MobileTextField.class);
        final MobileTextField.MobileTextFieldPresenter presenter =
                new MobileTextField.MobileTextFieldPresenter(componentMock);
        // when
        presenter.onClearButtonClick();
        //then
        Mockito.verify(componentMock).clearText();
    }

}