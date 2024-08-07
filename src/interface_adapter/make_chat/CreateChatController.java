package interface_adapter.make_chat;

import entity.User;
import use_case.make_chat.MakeChatInputBoundary;
import use_case.make_chat.MakeChatInputData;
import interface_adapter.ViewModelManager;

import java.time.LocalDateTime;

public class CreateChatController {
    private final MakeChatInputBoundary makeChatInputBoundary;
    private final ViewModelManager viewModelManager;

    public CreateChatController(MakeChatInputBoundary makeChatInputBoundary, ViewModelManager viewModelManager) {
        this.makeChatInputBoundary = makeChatInputBoundary;
        this.viewModelManager = viewModelManager;
    }

    public void createChat(String chatName, String user1, String user2) {
        MakeChatInputData inputData = new MakeChatInputData(chatName, user1, user2, LocalDateTime.now());
        makeChatInputBoundary.makeChat(inputData);

        User currentUser = viewModelManager.getCurrentUser();
        currentUser.getChats().add(chatName);
        viewModelManager.setCurrentUser(currentUser);

        viewModelManager.firePropertyChanged("chatsUpdated", null, null);
    }
}



