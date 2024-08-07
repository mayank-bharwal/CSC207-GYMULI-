package interface_adapter.delete_chat;

import entity.User;
import interface_adapter.ViewModelManager;
import use_case.delete_chat.DeleteChatInputBoundary;
import use_case.delete_chat.DeleteChatInputData;

public class DeleteChatController {
    private final DeleteChatInputBoundary deleteChatInputBoundary;
    private final ViewModelManager viewModelManager;

    public DeleteChatController(DeleteChatInputBoundary deleteChatInputBoundary, ViewModelManager viewModelManager) {
        this.deleteChatInputBoundary = deleteChatInputBoundary;
        this.viewModelManager = viewModelManager;
    }

    public void deleteChat(String chatname) {
        DeleteChatInputData inputData = new DeleteChatInputData(chatname);
        deleteChatInputBoundary.deleteChat(inputData);

        User currentUser = viewModelManager.getCurrentUser();
        currentUser.getChats().remove(chatname);

        viewModelManager.setCurrentUser(currentUser);

        viewModelManager.firePropertyChanged("chatsUpdated", null, null);
    }
}
