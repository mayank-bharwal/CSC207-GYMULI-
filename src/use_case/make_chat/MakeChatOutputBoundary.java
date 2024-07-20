package use_case.make_chat;

public interface MakeChatOutputBoundary {
    void setPassView(MakeChatOutputData chat);

    void setFailView(String error);
}
