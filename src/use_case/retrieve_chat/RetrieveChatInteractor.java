package use_case.retrieve_chat;
import entity.Chat;

public class RetrieveChatInteractor implements RetrieveChatInputBoundary {
    final private RetrieveChatOutputBoundary chatPresenter;
    final private RetrieveChatUserDataAccessInterface retrieveChatDAO;

    public RetrieveChatInteractor(RetrieveChatOutputBoundary chatPresenter, RetrieveChatUserDataAccessInterface retrieveDAO) {
        this.chatPresenter = chatPresenter;
        this.retrieveChatDAO = retrieveDAO;
    }

    @Override
    public void retrieveChat(RetrieveChatInputData inputData) {
        String chatName = inputData.getChatName();
        if (!retrieveChatDAO.chatExistsByName(chatName)) {
            chatPresenter.prepareFailView("Could not retrieve chat info");
        } else {
            Chat chat = retrieveChatDAO.getChat(inputData.getChatName());
            if (chat == null) {
                chatPresenter.prepareFailView("Could not retrieve chat info");
            } else {
                RetrieveChatOutputData outputData = new RetrieveChatOutputData(chat.getChatName(), chat.getUsers(),
                        chat.getNoOfMembers(), chat.getAllmessages(), chat.getTime(), false);
                System.out.println("RetrieveChatInteractor: chat.getAllmessages(): " + chat.getAllmessages());
                chatPresenter.prepareSuccessView(outputData);
            }
        }
    }
}
