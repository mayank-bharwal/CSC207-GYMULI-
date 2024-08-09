package interface_adapter.delete_chat;

import entity.User;
import interface_adapter.ViewModelManager;
import use_case.delete_chat.DeleteChatInputBoundary;
import use_case.delete_chat.DeleteChatInputData;

/**
 * Controller class responsible for handling requests to delete a chat.
 * It coordinates between the view and the use case layer for the delete chat functionality.
 */
public class DeleteChatController {
    private final DeleteChatInputBoundary deleteChatInputBoundary;
    private final ViewModelManager viewModelManager;

    /**
     * Constructs a {@code DeleteChatController} with the specified input boundary and view model manager.
     *
     * @param deleteChatInputBoundary The use case boundary to handle the delete chat operation.
     * @param viewModelManager        The manager responsible for updating and managing view models.
     */
    public DeleteChatController(DeleteChatInputBoundary deleteChatInputBoundary, ViewModelManager viewModelManager) {
        this.deleteChatInputBoundary = deleteChatInputBoundary;
        this.viewModelManager = viewModelManager;
    }

    /**
     * Deletes a chat based on the provided chat name.
     * <p>
     * This method creates an instance of {@link DeleteChatInputData} with the chat name and passes it to the
     * {@link DeleteChatInputBoundary} to perform the deletion operation.
     * </p>
     *
     * @param chatname The name of the chat to be deleted.
     */
    public void deleteChat(String chatname) {
        System.out.println("Controller Called");
        DeleteChatInputData inputData = new DeleteChatInputData(chatname);
        deleteChatInputBoundary.deleteChat(inputData);




    }
}
