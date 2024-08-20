package app;

import interface_adapter.ViewModelManager;
import interface_adapter.make_chat.CreateChatController;
import interface_adapter.make_chat.CreateChatViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.make_chat.MakeChatInputBoundary;
import views.CreateChatView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Unit test for the CreateChatViewFactory class.
 * This test ensures that the CreateChatViewFactory#create(ViewModelManager, CreateChatViewModel, MakeChatInputBoundary)
 * method correctly creates a CreateChatView object when provided with valid dependencies.
 */
class CreateChatViewFactoryTest {

    private ViewModelManager viewModelManager;
    private CreateChatViewModel createChatViewModel;
    private MakeChatInputBoundary makeChatInputBoundary;

    /**
     * Sets up the test environment by mocking the necessary dependencies.
     * Mocks include ViewModelManager, CreateChatViewModel, and MakeChatInputBoundary.
     */
    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        createChatViewModel = mock(CreateChatViewModel.class);
        makeChatInputBoundary = mock(MakeChatInputBoundary.class);
    }

    /**
     * Tests that the CreateChatViewFactory#create(ViewModelManager, CreateChatViewModel, MakeChatInputBoundary)
     * method successfully creates a non-null CreateChatView instance when valid dependencies are provided.
     */
    @Test
    void create() {
        CreateChatView createChatView = CreateChatViewFactory.create(viewModelManager, createChatViewModel, makeChatInputBoundary);

        assertNotNull(createChatView, "CreateChatView should not be null");
    }
}