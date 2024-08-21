package app;

import data_access.UserDataAccessObject;
import interface_adapter.ViewModelManager;
import interface_adapter.delete_chat.DeleteChatController;
import interface_adapter.retrieve_chat.RetrieveChatController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.MainView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class MainViewFactoryTest {

    private ViewModelManager viewModelManager;
    private RetrieveChatController retrieveChatController;
    private UserDataAccessObject userDataAccessObject;
    private DeleteChatController deleteChatController;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        retrieveChatController = mock(RetrieveChatController.class);
        userDataAccessObject = mock(UserDataAccessObject.class);
        deleteChatController = mock(DeleteChatController.class);
    }

    @Test
    void create() {
        MainView mainView = MainViewFactory.create(viewModelManager, retrieveChatController, userDataAccessObject, deleteChatController);

        assertNotNull(mainView, "MainView should not be null");
    }
}