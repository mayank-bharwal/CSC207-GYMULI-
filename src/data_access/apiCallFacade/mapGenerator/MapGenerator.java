package data_access.apiCallFacade.mapGenerator;

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

import data_access.apiCallFacade.apiCaller.APICaller;
import data_access.apiCallFacade.apiCaller.APICallerInterface;
import entity.User;
import org.bson.Document;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class MapGenerator implements MapGeneratorInterface {

    // generates a json dictionary
    @Override
    public JSONObject generateMap(User user, Map<String, User> accounts) {
        APICallerInterface apiCaller = new APICaller();

        Map<Tuple, Float> similarityMap = new HashMap<>();

        accounts.forEach((key, value) -> {
            if(value != user && value.getUsername() != user.getUsername()){
            String text1 = user.getBio() + " " + user.getProgramOfStudy() + " " + String.join(" ",user.getInterests())
                     + " " + String.join(" ",user.getFriends());

            String text2 = value.getBio() + " " + value.getProgramOfStudy() + " " + String.join(" ",value.getInterests())
                    + " " + String.join(" ",value.getFriends());

            Float similarityScore = apiCaller.getSimilarityScore(text1, text2);
            similarityMap.put(new Tuple(user.getUsername(), key), similarityScore);}
        });

        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<Tuple, Float> entry : similarityMap.entrySet()) {
            jsonObject.put(entry.getKey().toString(), entry.getValue());
        }
        return jsonObject;
    }

    @Override
    public Map<User, Double> generateDoc(User user, int N, Map<String,User> accounts){

        JSONObject json = generateMap(user, accounts);
        String jsonStringFromObject = json.toString();

        // Convert JSON string to Document
        Document doc = Document.parse(jsonStringFromObject);

        if (doc != null) {
            List<Map.Entry<User, Double>> userSimilarities = new ArrayList<>();
            String username = user.getUsername();

            for (String key : doc.keySet()) {
                if (key == null || key.equals("_id")) continue; // Skip the _id field

                String[] users = key.replace("(", "").replace(")", "").split(", ");
                if (users.length == 2) {
                    String user1 = users[0];
                    String user2 = users[1];
                    Double scoreWrapper = doc.getDouble(key);

                    if (scoreWrapper == null) {
                        System.out.println("Null score for key: " + key);
                        continue;
                    }

                    double score = scoreWrapper.doubleValue();
                    if (user1.equals(username) || user2.equals(username)) {
                        String otherUser = user1.equals(username) ? user2 : user1;
                        User similarUser = accounts.get(otherUser);
                        if (similarUser != null) {
                            userSimilarities.add(new AbstractMap.SimpleEntry<>(similarUser, score));
                        }
                    }
                }
            }

            userSimilarities.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

            Map<User, Double> topNUsers = userSimilarities.stream()
                    .limit(N)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            System.out.println("Found similar users for " + username + ": " + topNUsers.size());
            return topNUsers;
        }
        System.out.println("Document does not exist for user: " + user.getUsername());
        return new HashMap<>();
    }
}
