package data_access.similarityMapUpdaterFacade;

import entity.User;

import java.util.Map;

public interface UpdateDBInterface {
    public void UpdateDB(User user, Map<String, User> accounts);
}
