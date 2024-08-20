package use_case.make_chat;

import entity.Chat;

/**
 * Interface for accessing and manipulating user and chat data in the "Make Chat" use case.
 * It provides methods to check the existence of chats and users, as well as to save a new chat.
 */
public interface MakeChatUserDataAccessInterface {

    /**
     * Checks if a chat with the specified name already exists.
     *
     * @param chatName the name of the chat to check.
     * @return {@code true} if the chat exists, {@code false} otherwise.
     */
    boolean ChatExists(String chatName);

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username the username of the user to check.
     * @return {@code true} if the user exists, {@code false} otherwise.
     */
    boolean UserExists(String username);

    /**
     * Saves the specified chat with the associated users.
     *
     * @param user_1 the username or ID of the first user.
     * @param user_2 the username or ID of the second user.
     * @param chat   the chat entity to be saved.
     */
    void saveChat(String user_1, String user_2, Chat chat);
}