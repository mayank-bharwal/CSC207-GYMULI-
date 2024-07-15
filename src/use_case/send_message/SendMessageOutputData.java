package use_case.send_message;

import entity.Message;
import entity.Chat;

import java.time.LocalDateTime;

public class SendMessageOutputData {

    private Message message;
    private LocalDateTime creationTime;
    private boolean useCaseFailed;
    private String chatName;

    public SendMessageOutputData(String username, LocalDateTime creationTime, boolean useCaseFailed, String chatName) {
        this.message = message;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
        this.chatName = chatName;
    }

    public Message getMessage() {
        return message;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getChatName(String chatName) {return chatName;}
}
