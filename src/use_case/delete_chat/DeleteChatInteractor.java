package use_case.delete_chat;

import entity.Chat;

public class DeleteChatInteractor implements DeleteChatInputBoundary{

    final DeleteChatUserDataAccessInterafce deleteChatDAO;
    final DeleteChatOutputBoundary deleteChatPresenter;


    public DeleteChatInteractor(DeleteChatUserDataAccessInterafce deleteChatDAO, DeleteChatOutputBoundary deleteChatPresenter) {
        this.deleteChatDAO = deleteChatDAO;
        this.deleteChatPresenter = deleteChatPresenter;
    }


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
