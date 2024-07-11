package use_case.send_message;
import entity.Message;

public interface SendMessageUserDataAccessInterface{
    void save(Message message);
}
