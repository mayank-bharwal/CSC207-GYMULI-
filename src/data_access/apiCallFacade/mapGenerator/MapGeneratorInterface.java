package data_access.apiCallFacade.mapGenerator;

import entity.User;
import org.bson.Document;
import org.json.JSONObject;

import java.util.Map;

public interface MapGeneratorInterface {
    public JSONObject generateMap(User user, Map<String, User> accounts);
    public Map<User, Double> generateDoc(User user, int n, Map<String,User> accounts);
}
