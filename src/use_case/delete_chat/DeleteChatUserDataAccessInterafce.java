package use_case.delete_chat;

import entity.Chat;

public interface DeleteChatUserDataAccessInterafce {

    boolean ChatExists(String chatname);
    Chat getChat(String chatname);
    void DeleteChat(String chatname);
}
