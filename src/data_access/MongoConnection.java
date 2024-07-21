package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import entity.User;
import org.bson.Document;

import static data_access.similarityMapUpdaterFacade.mapUpdater.readDB.GetDB.getCollectionName;

public class MongoConnection {


    private String uri = "mongodb+srv://UmerFarooqui:RealMadrid123Canon@cluster0.vbtnfad.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private MongoClient mongoClient = MongoClients.create(uri);
    private MongoDatabase database = mongoClient.getDatabase("GYMULI");
    private MongoCollection<Document> MessageCollection = database.getCollection("messages");
    private MongoCollection<Document> UserCollection = database.getCollection("users");
    private MongoCollection<Document> ChatCollection = database.getCollection("chats");
    private MongoCollection<Document> similarityCollection = database.getCollection(getCollectionName());


    public MongoCollection<Document> getMessageCollection() {
        return MessageCollection;
    }

    public MongoCollection<Document> getUserCollection() {
        return UserCollection;
    }

    public MongoCollection<Document> getChatCollection() {
        return ChatCollection;
    }

    public MongoCollection<Document> getSimilarityCollection() {
        return similarityCollection;
    }
}