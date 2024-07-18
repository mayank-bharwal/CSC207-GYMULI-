package data_access.similarityMapUpdaterFacade.mapGenerator;

/* Sample Return JSON object
{
"(Mayank, Liban)" :  "0.6546"
"(Mayank, Grant)" :  "0.7544"
"(Mayank, Trudeau)" :  "0.3482"
"(Mayank, Biden)" :  "0.0"
"(Mayank, Farooqi)" :  "0.6627"
"(Mayank, Yuechen)" :  "0.4837"
"(Mayank, Yasamanro)" :  "0.2831"
"(Mayank, Justin)" :  "0.2534"
"(Mayank, Sadia)" :  "0.3783"
"(Mayank, Ishaan)" :  "0.3631"
}
*/

import data_access.similarityMapUpdaterFacade.mapGenerator.apiCaller.*;
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
