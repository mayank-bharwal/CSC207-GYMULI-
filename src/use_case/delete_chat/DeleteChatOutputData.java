package use_case.delete_chat;

import java.util.ArrayList;

/**
 * Data class that encapsulates the output information for the delete chat use case.
 * It contains details about the chat being deleted and the status of the deletion operation.
 */
public class DeleteChatOutputData {
    private final String chatname;
    private final ArrayList<String> members;
    private final boolean failView;

    /**
     * Constructs a {@code DeleteChatOutputData} instance with the specified chat details and failure status.
     *
     * @param chatname The identifier of the chat being deleted.
     * @param members The list of members in the chat.
     * @param failView Indicates if the use case failed. {@code true} if the deletion failed; {@code false} otherwise.
     */
    public DeleteChatOutputData(String chatname, ArrayList<String> members, boolean failView) {
        this.chatname = chatname;
        this.members = members;
        this.failView = failView;
    }

    /**
     * Gets the identifier of the chat.
     *
     * @return The chat identifier.
     */
    public String getChatname() {
        return chatname;
    }

    /**
     * Gets the list of members in the chat.
     *
     * @return The list of chat members.
     */
    public ArrayList<String> getMembers() {
        return members;
    }

    /**
     * Checks if the use case failed.
     *
     * @return {@code true} if the use case failed; {@code false} otherwise.
     */
    public boolean isFailView() {
        return failView;
    }


}
