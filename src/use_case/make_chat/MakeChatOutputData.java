package use_case.make_chat;

import entity.Chat;

/**
 * This class represents the output data for the "Make Chat" use case.
 * It contains the information about the chat that was created and the status of the operation.
 */
public class MakeChatOutputData {

    private final Chat curentChat;
    private final boolean failView;

    /**
     * Constructs a new {@code MakeChatOutputData} instance with the specified chat and status.
     *
     * @param curentChat the chat that was created.
     * @param failView   the status of the operation; {@code true} if the creation failed, {@code false} otherwise.
     */
    public MakeChatOutputData(Chat curentChat, boolean failView) {
        this.curentChat = curentChat;
        this.failView = failView;
    }

    /**
     * Returns the chat that was created.
     *
     * @return the current chat.
     */
    public Chat getCurentChat() {
        return curentChat;
    }
}