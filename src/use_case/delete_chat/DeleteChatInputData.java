package use_case.delete_chat;

public class DeleteChatInputData {
    final private String chatname;

    /**
     *
     * @param chatname - chat that you want to delete
     */


    public DeleteChatInputData(String chatname) {
        this.chatname = chatname;
    }

    public String getChatname() {
        return chatname;
    }
}
