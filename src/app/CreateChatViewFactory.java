package app;

import interface_adapter.ViewModelManager;
import interface_adapter.make_chat.CreateChatController;
import interface_adapter.make_chat.CreateChatViewModel;
import use_case.make_chat.MakeChatInputBoundary;
import views.CreateChatView;

public class CreateChatViewFactory {
    public static CreateChatView create(ViewModelManager viewModelManager, CreateChatViewModel createChatViewModel, MakeChatInputBoundary makeChatInputBoundary) {
        String currentUser = viewModelManager.getCurrentUser();
        CreateChatController createChatController = new CreateChatController(makeChatInputBoundary);
        return new CreateChatView(viewModelManager, createChatViewModel, createChatController, currentUser);
    }
}

