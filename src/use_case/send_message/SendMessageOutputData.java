package use_case.send_message;

import entity.Message;
import entity.Chat;

public class SendMessageOutputData {

    private Message message;
    private String creationTime;
    private boolean useCaseFailed;

    public SendMessageOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.message = message;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public Message getMessage() {
        return message;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
