//package data_access;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import entity.*;
//import org.bson.Document;
//import use_case.recommendations.RecommendationDataAccessInterface;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static com.mongodb.client.model.Filters.eq;
//import static data_access.similarityMapUpdaterFacade.mapUpdater.readDB.GetDB.*;
//
//public class RecommendationDataAccessObject implements RecommendationDataAccessInterface {
//    @Override
//    public List<User> getNSimilarUsers(User user, int N) {
//        // get top N users from the database by converting it into MAP and according to similarity score and make a list
//        // of users and return
//        try (MongoClient mongoClient = MongoClients.create(getURI())) {
//            MongoDatabase database = mongoClient.getDatabase(getDBName());
//            MongoCollection<Document> similarityCollection = database.getCollection(getCollectionName());
//
//            MongoCollection<Document> userCollection = database.getCollection("users");
//
//            UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
//
//            Document doc = similarityCollection.find(new Document("_id", getCollectionID())).first();
//
//            if (doc != null) {
//                List<UserSimilarity> userSimilarities = new ArrayList<>();
//                String username = user.getUsername();
//
//                for (String key : doc.keySet()) {
//
//                    if (key.equals("_id")) continue; // Skip the _id field
//
//                    String[] users = key.replace("(", "").replace(")", "").split(", ");
//
//                    if (users.length == 2) {
//                        String user1 = users[0];
//                        String user2 = users[1];
//                        double score = doc.getDouble(key);
//
//                        if (user1.equals(username) || user2.equals(username)) {
//                            String otherUser = user1.equals(username) ? user2 : user1;
//                            userSimilarities.add(new UserSimilarity(otherUser, score));
//                        }
//                    }
//                }
//
//                Collections.sort(userSimilarities, Comparator.comparingDouble(UserSimilarity::getScore).reversed());
//
//                List<String> topNUsernames = userSimilarities.stream()
//                        .limit(N)
//                        .map(UserSimilarity::getUsername)
//                        .collect(Collectors.toList());
//
//                List<User> topNUsers = new ArrayList<>();
//                for (String topUsername : topNUsernames) {
//                    User similarUser = userDataAccessObject.getUser(topUsername);
//                    if (similarUser != null) {
//                        topNUsers.add(similarUser);
//                    }
//                }
//
//                return topNUsers;
//            }
//        }
//                catch (Exception e) {
//            System.out.println("An error occurred while connecting to MongoDB" + e.getMessage());
//        }
//        return new ArrayList<>();
//    }
//
//    public static void main(String[] args) {
//        // Example usage
//        UserFactory userFactory = new CommonUserFactory();
//        Map<String, User> accounts = new HashMap<>();
//        Map<String, Message> messages = new HashMap<>();
//        MessageFactory messageFactory = new MessageFactory();
//
//        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, accounts, messages, messageFactory);
//        RecommendationDataAccessObject dao = new RecommendationDataAccessObject();
//
//        User user = userDataAccessObject.getUser("Alice");
//        List<User> similarUsers = dao.getNSimilarUsers(user, 3);
//        similarUsers.forEach(u -> System.out.println(u.getUsername()));
//    }
//}
