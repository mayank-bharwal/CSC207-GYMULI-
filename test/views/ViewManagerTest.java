package views;

import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

/**
 * Test class for the ViewManager class, ensuring proper handling of property changes
 * and switching between views.
 */
class ViewManagerTest {

    private ViewManager viewManager;
    private ViewModelManager viewModelManager;
    private JPanel views;
    private CardLayout cardLayout;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        views = new JPanel();
        cardLayout = mock(CardLayout.class);

        views.setLayout(cardLayout);
        viewManager = new ViewManager(views, cardLayout, viewModelManager);
    }

    /**
     * Tests the propertyChange method to ensure it changes the view correctly when
     * the "activeView" property is updated.
     */
    @Test
    void propertyChange_activeViewChanged() {
        String newView = "NewView";
        when(viewModelManager.getActiveView()).thenReturn(newView);
        PropertyChangeEvent event = new PropertyChangeEvent(this, "activeView", null, newView);

        viewManager.propertyChange(event);

        verify(cardLayout, times(1)).show(views, newView);
    }

    /**
     * Tests the propertyChange method to ensure it does nothing when a different property
     * is updated.
     */
    @Test
    void propertyChange_nonActiveViewProperty() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "someOtherProperty", null, "SomeValue");

        viewManager.propertyChange(event);

        verifyNoInteractions(cardLayout);
    }
}