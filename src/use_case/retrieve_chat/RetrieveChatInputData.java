package use_case.retrieve_chat;

public class RetrieveChatInputData {
    private final String chatName;

    public RetrieveChatInputData(String chatName) {
        this.chatName = chatName;
    }

    public String getChatName() {
        return chatName;
    }
}