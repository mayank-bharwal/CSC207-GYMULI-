package interface_adapter.retrieve_chat;

public class RetrieveChatState {
    private String chatName = "";
    private String chatnameError = null;
    private String noOfMemners;
    private String allMessages;
    private String creationtime;

    public RetrieveChatState(RetrieveChatState copy) {
        chatName = copy.chatName;
        chatnameError = copy.chatnameError;
        noOfMemners = copy.noOfMemners;
        allMessages = copy.allMessages;
        creationtime = copy.creationtime;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatnameError() {
        return chatnameError;
    }

    public void setChatnameError(String chatnameError) {
        this.chatnameError = chatnameError;
    }

    public String getNoOfMemners() {
        return noOfMemners;
    }

    public void setNoOfMemners(String noOfMemners) {
        this.noOfMemners = noOfMemners;
    }

    public String getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(String allMessages) {
        this.allMessages = allMessages;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    @Override
    public String toString() {
        return "RetrieveChatState [chatName=" + chatName + '\'' + ", chatnameError=" + chatnameError
                + '\'' + ", noOfMemners=" + noOfMemners + '\'' + ", allMessages=" + allMessages
                + '\'' + ", creationtime=" + creationtime + '\'' +"]";
    }
}
