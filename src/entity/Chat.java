package entity;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
/**
 * The Chat class represents a chat with a name, list of users, number of members, list of messages, and a timestamp.
 */
public class Chat {
     private String chatName;
     private ArrayList<String> users = new ArrayList<>();
     private Integer noOfMembers;
     private ArrayList<Message> allmessages = new ArrayList<>();
     private LocalDateTime time;


     /**
      * Constructs a new Chat with the specified name, users, number of members, messages, and timestamp.
      *
      * @param chatName    the name of the chat
      * @param users       the list of users in the chat
      * @param noOfMembers the number of members in the chat
      * @param allmessages the list of messages in the chat
      * @param time        the time the chat was created
      */
     Chat(String chatName, ArrayList<String> users, Integer noOfMembers, ArrayList<Message> allmessages, LocalDateTime time) {
          this.chatName = chatName;
          this.users = users;
          this.noOfMembers = noOfMembers;
          this.allmessages = allmessages;
          this.time = LocalDateTime.now();
     }
     /**
      * Gets the name of the chat.
      *
      * @return the name of the chat
      */

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