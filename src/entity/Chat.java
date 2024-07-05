package entity;

import java.util.ArrayList;

public class Chat {
     private ArrayList<User> users = new ArrayList<>();
     private Integer noOfMembers;
     private ArrayList<Message> allmessages = new ArrayList<>();

     Chat(ArrayList<User> users, Integer noOfMembers, ArrayList<Message> allmessages) {
          this.users = users;
          this.noOfMembers = noOfMembers;
          this.allmessages = allmessages;
     }

     public ArrayList<User> getUsers() {
          return users;
     }

     public void setUsers(ArrayList<User> users) {
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

}
