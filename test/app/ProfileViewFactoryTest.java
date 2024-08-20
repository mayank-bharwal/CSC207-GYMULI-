package app;

import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.ProfileView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Unit test for the ProfileViewFactory class.
 * Verifies that the ProfileViewFactory#create(ViewModelManager) method returns
 * a valid ProfileView instance.
 */
class ProfileViewFactoryTest {

    private ViewModelManager viewModelManager;

    /**
     * Sets up the test environment by mocking the ViewModelManager.
     * This method is executed before each test.
     */
    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
    }

    /**
     * Tests the ProfileViewFactory#create(ViewModelManager) method.
     * Ensures that the factory creates a non-null instance of ProfileView.
     */
    @Test
    void create() {
        ProfileView profileView = ProfileViewFactory.create(viewModelManager);
        assertNotNull(profileView, "ProfileView should not be null");
    }
}