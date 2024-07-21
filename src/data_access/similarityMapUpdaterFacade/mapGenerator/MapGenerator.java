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

import entity.User;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapGenerator implements MapGeneratorInterface {

    // generates a json dictionary
    @Override
    public JSONObject generateMap(User user, Map<String, User> accounts) {
        APICallerInterface apiCaller = new APICaller();
        Map<Tuple, Float> similarityMap = new HashMap<>();
        accounts.forEach((key, value) -> {
            String text1 = user.getBio() + " " + user.getProgramOfStudy() + " " + String.join(" ",user.getInterests())
                    + " " + user.getAge().toString() + " " + String.join(" ",user.getFriends());

            String text2 = value.getBio() + " " + value.getProgramOfStudy() + " " + String.join(" ",value.getInterests())
                    + " " + value.getAge().toString() + " " + String.join(" ",value.getFriends());

            Float similarityScore = apiCaller.getSimilarityScore(text1, text2);
            similarityMap.put(new Tuple(user.getUsername(), key), similarityScore);
        });

        // Calculate similarity between all users in accounts
        String[] keys = accounts.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                String key1 = keys[i];
                String key2 = keys[j];
                User user1 = accounts.get(key1);
                User user2 = accounts.get(key2);

                String text1 = user1.getBio() + " " + user1.getProgramOfStudy() + " " + String.join(" ",user1.getInterests())
                        + " " + user1.getAge().toString();
                String text2 = user2.getBio() + " " + user2.getProgramOfStudy() + " " + String.join(" ",user2.getInterests())
                        + " " + user2.getAge().toString();
                Float similarityScore = apiCaller.getSimilarityScore(text1, text2);
                similarityMap.put(new Tuple(key1, key2), similarityScore);
            }
        }

        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<Tuple, Float> entry : similarityMap.entrySet()) {
            jsonObject.put(entry.getKey().toString(), entry.getValue());
        }
        return jsonObject;
    }
}
