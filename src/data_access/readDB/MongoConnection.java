package data_access.readDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import static data_access.readDB.GetDB.*;

public class MongoConnection implements readDBInterface{

    // new db: mongodb+srv://gymuli07:csc207gymuli@cluster0.zjadzk8.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0

    private MongoClient mongoClient = MongoClients.create(getURI()); // still using Umer's db, will change later
    private MongoDatabase database = mongoClient.getDatabase(getDBName());
    private MongoCollection<Document> similarityCollection = database.getCollection(getCollectionName());

    private MongoCollection<Document> MessageCollection = database.getCollection("messages");
    private MongoCollection<Document> UserCollection = database.getCollection("users");
    private MongoCollection<Document> ChatCollection = database.getCollection("chats");


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

    public String getCollectionID(){
        return getID();
    }

    public static void main(String[] args) {
        MongoConnection mongoConnection = new MongoConnection();
        System.out.println(mongoConnection.getSimilarityCollection().toString());
    }

}
