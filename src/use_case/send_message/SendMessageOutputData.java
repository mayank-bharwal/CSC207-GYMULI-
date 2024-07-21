package use_case.send_message;

import entity.Message;
import entity.Chat;

import java.time.LocalDateTime;

public class SendMessageOutputData {

    private Message message;
    private LocalDateTime creationTime;
    private boolean useCaseFailed;
    private String chatName;

    /**
     *
     * @param username
     * @param creationTime
     * @param useCaseFailed
     * @param chatName
     */
    public SendMessageOutputData(String username, LocalDateTime creationTime, boolean useCaseFailed, String chatName) {

        this.message = message;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
        this.chatName = chatName;
    }

    /**
     *
     * @return message input by user
     */
    public Message getMessage() {
        return message;
    }

    /**
     *
     * @return the local time when the message was created
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     *
     * @param creationTime - time of creation
     */
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     *
     * @param chatName
     * @return the name of the chatroom
     */
    public String getChatName(String chatName) {return chatName;}
}
