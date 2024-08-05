package data_access.apiCallFacade;

import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.User;

import java.util.Map;

public interface FacadeInterface {
    public void UpdateDB(User user, Map<String, User> accounts, readDBInterface mongoConnection);
    public String filter(String text);
    public void use_paid(boolean use_paid);
}
