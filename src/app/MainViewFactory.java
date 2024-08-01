package app;

import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatController;
import data_access.UserDataAccessObject;
import views.MainView;

/**
 * A factory class for creating instances of {@link MainView}.
 */
public class MainViewFactory {

    /**
     * Creates a new {@link MainView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param retrieveChatController the controller responsible for handling the retrieval of chat messages.
     * @return a new instance of {@link MainView}.
     */

    public static MainView create(ViewModelManager viewModelManager, RetrieveChatController retrieveChatController,
                                  UserDataAccessObject userDataAccessObject) {
        return new MainView(viewModelManager, retrieveChatController, userDataAccessObject);
    }
}
