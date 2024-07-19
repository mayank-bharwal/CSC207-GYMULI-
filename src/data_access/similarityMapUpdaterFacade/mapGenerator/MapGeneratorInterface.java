package data_access.similarityMapUpdaterFacade.mapGenerator;

import entity.User;

import java.util.Map;
import org.json.*;

public interface MapGeneratorInterface {
    public JSONObject generateMap(User newuser, Map<String, User> accounts);
    // use the  accounts hashmap from userDAO to get dict of accounts to user objects to get all the users
    // then calculate similarity score and generate a map // returns json object like package.json
}
