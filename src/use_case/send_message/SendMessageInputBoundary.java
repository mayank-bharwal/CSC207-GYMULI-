package use_case.send_message;

public interface SendMessageInputBoundary {
    void execute(SendMessageInputData inputData);
    void change(SendMessageInputData inputData);
}
