
package DataAccess;


import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongo {
    public static void main( String[] args ) {

        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "mongodb+srv://UmerFarooqui:RealMadrid123Canon@cluster0.vbtnfad.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Bookstore");
            MongoCollection<Document> collection = database.getCollection("books");
            if (collection.countDocuments() == 0) {
                System.out.println("No books found");
            }

            Document doc = collection.find(eq("title", "Harry Potter and the Goblet of Fire")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
}