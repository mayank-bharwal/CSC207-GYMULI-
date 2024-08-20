package use_case.retrieve_chat;

import entity.Chat;

/**
 * Interface for data access operations related to retrieving chat information.
 * It provides methods for checking the existence of a chat by name and for fetching the details of a specific chat.
 */
public interface RetrieveChatUserDataAccessInterface {

    /**
     * Checks if a chat with the specified name exists.
     *
     * @param chatName the name of the chat to check.
     * @return {@code true} if the chat exists, {@code false} otherwise.
     */
    boolean chatExistsByName(String chatName);

    /**
     * Retrieves the chat with the specified name.
     *
     * @param chatName the name of the chat to retrieve.
     * @return the {@link Chat} object representing the chat with the specified name.
     */
    Chat getChat(String chatName);
}