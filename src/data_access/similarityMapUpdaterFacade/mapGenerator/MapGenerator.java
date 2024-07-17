package data_access.similarityMapUpdaterFacade.mapGenerator;

import data_access.similarityMapUpdaterFacade.mapGenerator.apiCaller.APICaller;
import entity.User;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapGenerator implements MapGeneratorInterface {

    // generates a json dictionary like package.json
    @Override
    public JSONObject generateMap(User user, Map<String, User> accounts) {
        APICaller apiCaller = new APICaller();
        Map<Tuple, Float> similarityMap = new HashMap<>();
        accounts.forEach((key, value) -> {
            String text1 = user.getBio() + " " + user.getProgramOfStudy() + " " + user.getInterests().toString()
                    + " " + user.getAge().toString();
            String text2 = value.getBio() + " " + value.getProgramOfStudy() + " " + value.getInterests().toString()
                    + " " + value.getAge().toString();
            Float similarityScore = apiCaller.getSimilarityScore(text1, text2);
            similarityMap.put(new Tuple(user.getUsername(), key), similarityScore);
        });
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<Tuple, Float> entry : similarityMap.entrySet()) {
            jsonObject.put(entry.getKey().toString(), entry.getValue());
        }
        return jsonObject;
    }
}
