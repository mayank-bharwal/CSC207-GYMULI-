package data_access.apiCallFacade;

import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.User;
import org.bson.Document;

import java.util.Map;

public interface FacadeInterface {
    public void UpdateDB(User user, Map<String, User> accounts);
    public String filter(String text);
    public void use_paid(boolean use_paid);
    public Map<User, Double> getMap(User user,Map<String,User> accounts, int n);
}
