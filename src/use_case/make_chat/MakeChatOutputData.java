package use_case.make_chat;

import entity.Chat;

public class MakeChatOutputData {
    private final Chat curentChat;
    private final boolean failView;


    public MakeChatOutputData(Chat curentChat, boolean failView) {
        this.curentChat = curentChat;
        this.failView = failView;
    }
    public Chat getCurentChat() {return curentChat;}

}
