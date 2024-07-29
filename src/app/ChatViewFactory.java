package app;

import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessageViewModel;
import views.ChatView;
/**
 * A factory class for creating instances of {@link ChatView}.
 */
public class ChatViewFactory {

    /**
     * Creates a new {@link ChatView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param sendMessageController the controller responsible for handling the send message actions.
     * @param sendMessageViewModel the view model for the send message view, containing the data and logic specific to sending messages.
     * @param retrieveChatViewModel the view model for the chat retrieval view, containing the data and logic specific to retrieving chat messages.
     * @return a new instance of {@link ChatView}.
     */

    public static ChatView create(ViewModelManager viewModelManager, SendMessageController sendMessageController, SendMessageViewModel sendMessageViewModel, RetrieveChatViewModel retrieveChatViewModel) {
        return new ChatView(viewModelManager, sendMessageController, sendMessageViewModel, retrieveChatViewModel);
    }
}

