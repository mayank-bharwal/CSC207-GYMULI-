package use_case.retrieve_message;

public interface RetrieveMessageUserDataAccessInterface {
    boolean AccountExists(String username);
    String getMessage(String sender, String receiver);
}
