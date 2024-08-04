package data_access.apiCallFacade;

import data_access.readDB.MongoConnection;
import entity.User;

import java.util.Map;

public interface FacadeInterface {
    public void UpdateDB(User user, Map<String, User> accounts, MongoConnection mongoConnection);
    public String filter(String text);
    public void use_paid(boolean use_paid);
}
