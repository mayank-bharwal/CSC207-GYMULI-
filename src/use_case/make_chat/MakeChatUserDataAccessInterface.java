package use_case.make_chat;

import entity.Chat;

public interface MakeChatUserDataAccessInterface {

    boolean ChatExists(String username);
    boolean UserExists(String username);
    boolean saveChat(Chat chat);
}
