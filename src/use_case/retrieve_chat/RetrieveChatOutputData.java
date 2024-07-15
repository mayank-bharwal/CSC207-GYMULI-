package use_case.retrieve_chat;

import entity.Message;

import java.util.ArrayList;

public class RetrieveChatOutputData {
    final private String chatName;
    final private ArrayList<Message> allMessages;
    private boolean useCaseFailed;

    public RetrieveChatOutputData(String chatName, ArrayList<Message> allMessages, boolean useCaseFailed) {
        this.chatName = chatName;
        this.allMessages = allMessages;
        this.useCaseFailed = useCaseFailed;
    }
    public String getChatName() {
        return chatName;
    }
    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }
}
