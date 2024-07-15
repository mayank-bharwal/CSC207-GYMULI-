package use_case.retrieve_chat;

import entity.Chat;

public interface RetrieveChatUserDataAccessInterface {
    boolean chatExistsByName(String chatName);
    Chat getChat(String chatName);
}
