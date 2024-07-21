package interface_adapter.retrieve_chat;

import entity.Message;

import java.util.ArrayList;
import java.util.List;

public class RetrieveChatState {
    private String chatName;
    private List<String> users = new ArrayList<>();
    private Integer noOfMembers;
    private List<Message> allMessages = new ArrayList<>();
    private String error;

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Integer getNoOfMembers() {
        return noOfMembers;
    }

    public void setNoOfMembers(Integer noOfMembers) {
        this.noOfMembers = noOfMembers;
    }

    public List<Message> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(List<Message> allMessages) {
        this.allMessages = allMessages;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
