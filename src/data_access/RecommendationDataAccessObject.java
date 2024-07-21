package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import data_access.similarityMapUpdaterFacade.Facade;
import data_access.similarityMapUpdaterFacade.FacadeInterface;
import entity.*;
import org.bson.Document;
import use_case.recommendations.RecommendationDataAccessInterface;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static data_access.similarityMapUpdaterFacade.mapUpdater.readDB.GetDB.*;
import static data_access.userMap_ignore.getMap;

public class RecommendationDataAccessObject implements RecommendationDataAccessInterface {
    @Override
    public List<User> getNSimilarUsers(User user, int N) {

        MongoConnection mongoConnection = new MongoConnection();
        Document doc = mongoConnection.getSimilarityCollection().find(new Document("_id", getCollectionID())).first();

        if (doc != null) {
            List<UserSimilarity> userSimilarities = new ArrayList<>();
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
                        userSimilarities.add(new UserSimilarity(otherUser, score));
                    }
                }
            }

            Collections.sort(userSimilarities, Comparator.comparingDouble(UserSimilarity::getScore).reversed());

            List<String> topNUsernames = userSimilarities.stream()
                    .limit(N)
                    .map(UserSimilarity::getUsername)
                    .collect(Collectors.toList());

            UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

            List<User> topNUsers = new ArrayList<>();
            for (String topUsername : topNUsernames) {
                User similarUser = userDataAccessObject.getUser(topUsername);
                if (similarUser != null & !similarUser.getUsername().equals(username)) {
                    topNUsers.add(similarUser);
                }
            }

            return topNUsers;
        }
        System.out.println("Document Does not exist");
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Example usage
        UserFactory userFactory = new CommonUserFactory();
        Map<String, Message> messages = new HashMap<>();
        MessageFactory messageFactory = new MessageFactory();

        User user = userFactory.createUser("Gippy", "love123", "Audiophile", 34, "Cinema Studies",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now());

        FacadeInterface facade = new Facade();
        //facade.UpdateDB(user, getMap());

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, getMap(), messages, messageFactory);
        //RecommendationDataAccessObject dao = new RecommendationDataAccessObject();

        User user1 = userDataAccessObject.getUser("Alice");
        if (user1 != null) {
            List<User> similarUsers = userDataAccessObject.getNSimilarUsers(user1, 3);
            similarUsers.forEach(u -> System.out.println(u));
        } else {
            System.out.println("User 'Alice' not found.");
        }
    }
}