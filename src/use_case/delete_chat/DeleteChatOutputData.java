package use_case.delete_chat;

import java.util.ArrayList;

public class DeleteChatOutputData {
    private final String chatname;
    private final ArrayList<String> members;
    private final boolean failView;

    /**
     * @param chatname - chat identifier
     * @param members - all members of the Chat
     * @param failView - if the usecase fails
     */

    public DeleteChatOutputData(String chatname, ArrayList<String> members, boolean failView) {
        this.chatname = chatname;
        this.members = members;
        this.failView = failView;
    }

    public String getChatname() {
        return chatname;
    }
    public ArrayList<String> getMembers() {
        return members;
    }
    public boolean isFailView() {
        return failView;
    }


}
