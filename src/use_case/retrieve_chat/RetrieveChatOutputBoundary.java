package use_case.retrieve_chat;

public interface RetrieveChatOutputBoundary {
    void prepareSuccessView(RetrieveChatOutputData chatInfo);

    void prepareFailView(String error);
}