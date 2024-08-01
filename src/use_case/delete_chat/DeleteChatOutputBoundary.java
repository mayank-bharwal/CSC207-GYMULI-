package use_case.delete_chat;

public interface DeleteChatOutputBoundary {

    void setPassView(DeleteChatOutputData chat);

    void setFailView(String error);
}
