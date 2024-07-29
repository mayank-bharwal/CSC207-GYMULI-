package app;

import interface_adapter.ViewModelManager;
import interface_adapter.make_chat.CreateChatController;
import interface_adapter.make_chat.CreateChatViewModel;
import use_case.make_chat.MakeChatInputBoundary;
import views.CreateChatView;
/**
 * A factory class for creating instances of {@link CreateChatView}.
 */
public class CreateChatViewFactory {

    /**
     * Creates a new {@link CreateChatView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param createChatViewModel the view model for the create chat view, containing the data and logic specific to creating chat rooms.
     * @param makeChatInputBoundary the input boundary for the create chat use case, defining the operations related to creating a chat room.
     * @return a new instance of {@link CreateChatView}.
     */

    public static CreateChatView create(ViewModelManager viewModelManager, CreateChatViewModel createChatViewModel, MakeChatInputBoundary makeChatInputBoundary) {
        CreateChatController createChatController = new CreateChatController(makeChatInputBoundary, viewModelManager);
        return new CreateChatView(viewModelManager, createChatViewModel, createChatController);
    }
}

