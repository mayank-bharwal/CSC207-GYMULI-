package interface_adapter.make_chat;

import use_case.make_chat.MakeChatInputBoundary;
import use_case.make_chat.MakeChatInputData;

import java.time.LocalDateTime;

public class CreateChatController {
    private final MakeChatInputBoundary makeChatInputBoundary;

    public CreateChatController(MakeChatInputBoundary makeChatInputBoundary) {
        this.makeChatInputBoundary = makeChatInputBoundary;
    }

    public void createChat(String chatName, String user1, String user2) {
        MakeChatInputData inputData = new MakeChatInputData(chatName, user1, user2, LocalDateTime.now());
        makeChatInputBoundary.makeChat(inputData);
    }
}
