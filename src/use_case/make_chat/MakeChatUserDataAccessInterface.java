package use_case.make_chat;

import entity.Chat;

public interface MakeChatUserDataAccessInterface {

    boolean ChatExists(String chatName);
    boolean UserExists(String username);
    void saveChat(Chat chat);
}
