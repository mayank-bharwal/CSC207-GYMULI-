package interface_adapter.retrieve_chat;

import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInputData;

public class RetrieveChatController {
    RetrieveChatInputBoundary retrieveChatInteractor;
    public RetrieveChatController(RetrieveChatInputBoundary retrieveChatInteractor) {
        this.retrieveChatInteractor = retrieveChatInteractor;
    }
    public void retrieveChat(String chatName) {

        RetrieveChatInputData inputData = new RetrieveChatInputData(chatName);
        retrieveChatInteractor.retrieveChat(inputData);


    }

}
