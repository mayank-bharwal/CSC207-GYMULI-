package use_case.retrieve_chat;

import entity.Message;

import java.util.ArrayList;

public class RetrieveChatInputData {
    final private String chatName;

    public RetrieveChatInputData(String chatName, ArrayList<Message> allmessages) {
        this.chatName = chatName;
    }


    String getChatName() {return chatName;}
}
