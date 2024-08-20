package use_case.delete_chat;

import entity.Chat;

/**
 * Interface for data access operations related to chat deletion.
 * It defines methods for checking the existence of a chat, retrieving a chat,
 * and deleting a chat from the data source.
 */
public interface DeleteChatUserDataAccessInterafce {

    /**
     * Checks if a chat with the given name exists.
     *
     * @param chatname The name of the chat to check.
     * @return {@code true} if the chat exists; {@code false} otherwise.
     */
    boolean ChatExists(String chatname);

    /**
     * Retrieves a chat by its name.
     *
     * @param chatname The name of the chat to retrieve.
     * @return The {@link Chat} object representing the chat.
     */
    Chat getChat(String chatname);

    /**
     * Deletes a chat with the given name.
     *
     * @param chatname The name of the chat to delete.
     */
    void DeleteChat(String chatname);
}
