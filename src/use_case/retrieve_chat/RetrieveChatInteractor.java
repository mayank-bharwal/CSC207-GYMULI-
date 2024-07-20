//package use_case.retrieve_chat;
//import entity.Chat;
//
//public class RetrieveChatInteractor implements RetrieveChatInputBoundary {
//    final private RetrieveChatOutputBoundary chatPresenter;
//    final private RetrieveChatUserDataAccessInterface retrieveChatDAO;
//
//    public RetrieveChatInteractor(RetrieveChatOutputBoundary chatPresenter, RetrieveChatUserDataAccessInterface retrieveDAO) {
//        this.chatPresenter = chatPresenter;
//        this.retrieveChatDAO = retrieveDAO;
//    }
//
//    @Override
//    public void retrieveChat(RetrieveChatInputData inputData) {
//        String chatName = inputData.getChatName();
//        if (!retrieveChatDAO.chatExistsByName(chatName)){
//            chatPresenter.prepareFailView("Could not retrieve chat info");
//        } else {
//            Chat chat = retrieveChatDAO.getChat(inputData.getChatName());
//            RetrieveChatOutputData outputData = new RetrieveChatOutputData(chat.getChatName(), chat.getAllmessages(), false);
//            chatPresenter.prepareSuccessView(outputData);
//
//        }
//
//    }
//}
