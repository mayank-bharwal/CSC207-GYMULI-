package app;

import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessageViewModel;
import views.ChatView;

public class ChatViewFactory {
    public static ChatView create(ViewModelManager viewModelManager, SendMessageController sendMessageController, SendMessageViewModel sendMessageViewModel, RetrieveChatViewModel retrieveChatViewModel) {
        return new ChatView(viewModelManager, sendMessageController, sendMessageViewModel, retrieveChatViewModel);
    }
}

