package use_case.retrieve_chat;

/**
 * Represents the input data required for retrieving a chat in the "Retrieve Chat" use case.
 * This class contains the necessary information to identify and retrieve the specified chat.
 */
public class RetrieveChatInputData {

    private final String chatName;

    /**
     * Constructs a new {@code RetrieveChatInputData} instance with the specified chat name.
     *
     * @param chatName the name of the chat to be retrieved.
     */
    public RetrieveChatInputData(String chatName) {
        this.chatName = chatName;
    }

    /**
     * Returns the name of the chat to be retrieved.
     *
     * @return the name of the chat.
     */
    public String getChatName() {
        return chatName;
    }
}