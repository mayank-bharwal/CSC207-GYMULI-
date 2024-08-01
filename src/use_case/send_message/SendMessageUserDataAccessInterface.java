package use_case.send_message;
import entity.Message;
import entity.User;


public interface SendMessageUserDataAccessInterface{
     /**
      *
      * @param message - text input by user
      */
     void saveMessage(Message message);
     String filter(String message);
}
