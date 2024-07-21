package app;

import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import views.ChatView;

public class ChatViewFactory {
    public static ChatView create(ViewModelManager viewModelManager, RetrieveChatViewModel retrieveChatViewModel) {
        return new ChatView(viewModelManager, retrieveChatViewModel);
    }
}
