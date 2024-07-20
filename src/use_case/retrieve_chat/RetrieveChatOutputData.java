package use_case.retrieve_chat;

import entity.Message;
import entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RetrieveChatOutputData {
    final private String chatName;
    private ArrayList<String> users = new ArrayList<>();
    private final Integer noOfmembers;
    final private ArrayList<Message> allMessages;
    private final LocalDateTime time;
    private final boolean useCaseFailed;

    public RetrieveChatOutputData(String chatName, ArrayList<String> users, Integer noOfmembers, ArrayList<Message> allMessages, LocalDateTime time, boolean useCaseFailed) {
        this.chatName = chatName;
        this.users = users;
        this.noOfmembers = noOfmembers;
        this.allMessages = allMessages;
        this.time = time;
        this.useCaseFailed = useCaseFailed;
    }
     public String getChatName() {
        return chatName;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public Integer getNoOfmembers() {
        return noOfmembers;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
