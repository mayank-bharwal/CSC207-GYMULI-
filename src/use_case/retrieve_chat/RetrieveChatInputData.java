package use_case.retrieve_chat;

public class RetrieveChatInputData {
    final private String chatName;

    public RetrieveChatInputData(String chatName) {
        this.chatName = chatName;
    }


    String getChatName() {return chatName;}
}
