package interface_adapter.retrieve_chat;

import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInputData;

public class RetrieveChatController {
    private final RetrieveChatInputBoundary retrieveChatInputBoundary;

    public RetrieveChatController(RetrieveChatInputBoundary retrieveChatInputBoundary) {
        this.retrieveChatInputBoundary = retrieveChatInputBoundary;
    }

    public void retrieveChat(String chatName) {
        RetrieveChatInputData inputData = new RetrieveChatInputData(chatName);
        retrieveChatInputBoundary.retrieveChat(inputData);
    }
}


