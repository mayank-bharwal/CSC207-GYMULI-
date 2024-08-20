package use_case.delete_chat;

/**
 * Represents the input data required to delete a chat.
 * This class encapsulates the chat name that needs to be deleted.
 */
public class DeleteChatInputData {
    final private String chatname;

    /**
     * Constructs a {@code DeleteChatInputData} object with the specified chat name.
     *
     * @param chatname The name of the chat to be deleted.
     */
    public DeleteChatInputData(String chatname) {
        this.chatname = chatname;
    }

    /**
     * Retrieves the name of the chat to be deleted.
     *
     * @return The name of the chat.
     */
    public String getChatname() {
        return chatname;
    }
}
