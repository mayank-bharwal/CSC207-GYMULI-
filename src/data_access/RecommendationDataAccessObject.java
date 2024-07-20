//package data_access;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import entity.User;
//import org.bson.Document;
//import use_case.recommendations.RecommendationDataAccessInterface;
//
//import java.util.List;
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
//            MongoCollection<Document> collection = database.getCollection(getCollectionName());
//            Document doc = collection.find(eq("title", "Back to the Future")).first();
//            if (doc != null) {
//                System.out.println(doc.toJson());
//            } else {
//                System.out.println("No matching documents found.");
//            }
//        } catch (Exception e) {
//            System.out.println("An error occurred while connecting to MongoDB" + e.getMessage());
//        }
//    }
//}
