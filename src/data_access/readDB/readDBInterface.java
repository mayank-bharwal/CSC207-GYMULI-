package data_access.readDB;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface readDBInterface {
    public String getCollectionID();
    public MongoCollection<Document> getMessageCollection();
    public MongoCollection<Document> getUserCollection();
    public MongoCollection<Document> getChatCollection();
    public MongoCollection<Document> getSimilarityCollection();
}
