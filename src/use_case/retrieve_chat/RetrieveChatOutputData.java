package use_case.retrieve_chat;

import entity.Message;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the output data for the "Retrieve Chat" use case.
 * This class encapsulates the details of a chat that has been retrieved,
 * including chat information, user details, messages, and the operation status.
 */
public class RetrieveChatOutputData {

    private final String chatName;
    private ArrayList<String> users = new ArrayList<>();
    private final Integer noOfmembers;
    private final ArrayList<Message> allMessages;
    private final LocalDateTime time;
    private final boolean useCaseFailed;

    /**
     * Constructs a new {@code RetrieveChatOutputData} instance with the specified details.
     *
     * @param chatName      the name of the chat.
     * @param users         a list of usernames or IDs of users in the chat.
     * @param noOfmembers   the number of members in the chat.
     * @param allMessages   a list of all messages in the chat.
     * @param time          the time when the chat was created or last updated.
     * @param useCaseFailed {@code true} if the use case failed, {@code false} otherwise.
     */
    public RetrieveChatOutputData(String chatName, ArrayList<String> users, Integer noOfmembers,
                                  ArrayList<Message> allMessages, LocalDateTime time, boolean useCaseFailed) {
        this.chatName = chatName;
        this.users = users;
        this.noOfmembers = noOfmembers;
        this.allMessages = allMessages;
        this.time = time;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the name of the chat.
     *
     * @return the name of the chat.
     */
    public String getChatName() {
        return chatName;
    }

    /**
     * Returns a list of usernames or IDs of users in the chat.
     *
     * @return a list of users in the chat.
     */
    public ArrayList<String> getUsers() {
        return users;
    }

    /**
     * Returns the number of members in the chat.
     *
     * @return the number of members.
     */
    public Integer getNoOfmembers() {
        return noOfmembers;
    }

    /**
     * Returns a list of all messages in the chat.
     *
     * @return a list of all messages.
     */
    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    /**
     * Returns the time when the chat was created or last updated.
     *
     * @return the time of the chat.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns whether the use case failed.
     *
     * @return {@code true} if the use case failed, {@code false} otherwise.
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}