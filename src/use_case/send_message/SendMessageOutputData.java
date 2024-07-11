package use_case.send_message;

public class SendMessageOutputData {

    private final String message;
    private String creationTime;
    private boolean useCaseFailed;

    public SignupOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.message = message;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return message;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
