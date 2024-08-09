package use_case.delete_chat;

import entity.Chat;

/**
 * Interactor class for handling the process of deleting a chat.
 * It implements the {@link DeleteChatInputBoundary} interface and coordinates
 * the interaction between the input data, data access object, and output boundary.
 */
public class DeleteChatInteractor implements DeleteChatInputBoundary{

    final DeleteChatUserDataAccessInterafce deleteChatDAO;
    final DeleteChatOutputBoundary deleteChatPresenter;

    /**
     * Constructs a {@code DeleteChatInteractor} with the specified data access object
     * and output boundary.
     *
     * @param deleteChatDAO The data access object to interact with chat data.
     * @param deleteChatPresenter The output boundary to communicate results of the deletion.
     */
    public DeleteChatInteractor(DeleteChatUserDataAccessInterafce deleteChatDAO, DeleteChatOutputBoundary deleteChatPresenter) {
        this.deleteChatDAO = deleteChatDAO;
        this.deleteChatPresenter = deleteChatPresenter;
    }

    /**
     * Executes the process of deleting a chat based on the provided input data.
     * <p>
     * This method checks if the chat exists. If it does not exist, it sets
     * a failure view through the output boundary. If the chat exists, it retrieves
     * the chat details, deletes the chat, and sets a success view.
     * </p>
     *
     * @param inputData The data required to delete a chat, encapsulated in a {@link DeleteChatInputData} object.
     */
    @Override
    public void deleteChat(DeleteChatInputData inputData) {

        if (!deleteChatDAO.ChatExists(inputData.getChatname())) {
            deleteChatPresenter.setFailView("The chat does not exist");
        } else {
            System.out.println("interactor called");
            Chat deletedChat =  deleteChatDAO.getChat(inputData.getChatname());
            DeleteChatOutputData outputdata = new DeleteChatOutputData(deletedChat.getChatName(), deletedChat.getUsers(), false);
            deleteChatPresenter.setPassView(outputdata);
            deleteChatDAO.DeleteChat(inputData.getChatname());

        }
    }
}
