package data_access.similarityMapUpdaterFacade.mapGenerator;

import entity.User;
import org.json.JSONObject;

import java.util.Map;

public interface MapGeneratorInterface {
    public JSONObject generateMap(User user, Map<String, User> accounts);
}
