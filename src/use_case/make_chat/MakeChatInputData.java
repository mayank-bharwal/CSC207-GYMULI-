package use_case.make_chat;

import java.time.LocalDateTime;

public class MakeChatInputData {
     final private String User_1;
     final private String User_2;
     final private String chatName;
     final private LocalDateTime time;

    public MakeChatInputData(String chatName, String user1, String user2, LocalDateTime time) {
        this.chatName = chatName;
        this.User_1 = user1;
        this.User_2 = user2;
        this.time = time;
    }

    public String getChatName() {
        return chatName;
    }

    public String getUser_1() {
        return User_1;
    }

    public String getUser_2() {
        return User_2;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
