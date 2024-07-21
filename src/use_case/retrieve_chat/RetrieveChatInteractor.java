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
        System.out.println("RetrieveChatInteractor: Checking if chat exists for chat name: " + chatName);
        if (!retrieveChatDAO.chatExistsByName(chatName)) {
            System.out.println("RetrieveChatInteractor: Chat does not exist for chat name: " + chatName);
            chatPresenter.prepareFailView("Could not retrieve chat info");
        } else {
            System.out.println("RetrieveChatInteractor: Chat exists for chat name: " + chatName);
            Chat chat = retrieveChatDAO.getChat(inputData.getChatName());
            if (chat == null) {
                System.out.println("RetrieveChatInteractor: Chat retrieval returned null for chat name: " + chatName);
                chatPresenter.prepareFailView("Could not retrieve chat info");
            } else {
                RetrieveChatOutputData outputData = new RetrieveChatOutputData(chat.getChatName(), chat.getUsers(),
                        chat.getNoOfMembers(), chat.getAllmessages(), chat.getTime(), false);
                System.out.println("RetrieveChatInteractor: Successfully retrieved chat info for chat name: " + chatName);
                chatPresenter.prepareSuccessView(outputData);
            }
        }
    }
}
