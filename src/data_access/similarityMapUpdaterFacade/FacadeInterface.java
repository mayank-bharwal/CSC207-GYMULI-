package data_access.similarityMapUpdaterFacade;

import data_access.readDB.MongoConnection;
import entity.User;

import java.util.Map;

public interface FacadeInterface {
    public void UpdateDB(User user, Map<String, User> accounts, MongoConnection mongoConnection);
}
