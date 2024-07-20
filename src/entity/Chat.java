package entity;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Chat {
     private String chatName;
     private ArrayList<String> users = new ArrayList<>();
     private Integer noOfMembers;
     private ArrayList<Message> allmessages = new ArrayList<>();
     final private LocalDateTime time;

     /**
      * Requires:
      * @param chatName -name of chatroom
      * @param users - the people using chat
      * @param noOfMembers - number of users
      * @param allmessages - display all messages in the chat
      */

     Chat(String chatName, ArrayList<String> users, Integer noOfMembers, ArrayList<Message> allmessages) {
          this.chatName = chatName;
          this.users = users;
          this.noOfMembers = noOfMembers;
          this.allmessages = allmessages;
          this.time = LocalDateTime.now();
     }

     public String getChatName() {return chatName;}

     public void setChatName(String chatName) {this.chatName = chatName;}

     public ArrayList<String> getUsers() {
          return users;
     }

     public void setUsers(ArrayList<String> users) {
          this.users = users;
     }

     public Integer getNoOfMembers() {
          return noOfMembers;
     }

     public void setNoOfMembers(Integer noOfMembers) {
          this.noOfMembers = noOfMembers;
     }

     public ArrayList<Message> getAllmessages() {
          return allmessages;
     }

     public void setAllmessages(ArrayList<Message> allmessages) {
          this.allmessages = allmessages;
     }

     public LocalDateTime getTime() {
          return time;
     }

}
