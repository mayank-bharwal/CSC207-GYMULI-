package DataAccess.MongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import entity.User;
import entity.UserFactory;
import org.bson.Document;
import use_case.account_creation.AccountCreationUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import java.util.*;

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface {
    String uri = "mongodb+srv://UmerFarooqui:RealMadrid123Canon@cluster0.vbtnfad.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    MongoClient mongoClient = MongoClients.create(uri);
    MongoDatabase database = mongoClient.getDatabase("GYMULI");
    MongoCollection<Document> MessageCollection = database.getCollection("messages");
    MongoCollection<Document> UserCollection = database.getCollection("users");
    private final Map<String, User> accounts = new HashMap<>();

    public UserDataAccessObject() {
        try (MongoCursor<Document> cursor = UserCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String username = doc.getString("username");
                String password = doc.getString("password");
                String bio = doc.getString("bio");
                Integer age = doc.getInteger("age");
                String programOfStudy = doc.getString("programOfStudy");
                List<String> interests = (List<String>) doc.get("interests");
                List<String> friends = (List<String>) doc.get("friends");
                Date dateCreated = doc.getDate("dateCreated");

                User user = UserFactory.create(username, password, bio, age, programOfStudy, interests, friends, dateCreated);
                accounts.put(username, user);
            }
        }
    }

    @Override
    public boolean AccountExists(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public void save(User user) {

        Document document = new Document();
        document.append("username", user.getUsername());
        document.append("password", user.getPassword());
        document.append("bio", user.getBio());
        document.append("age", user.getAge());
        document.append("programOfStudy", user.getProgramOfStudy());
        document.append("interests", user.getInterests());
        document.append("friends", user.getFriends());
        document.append("dateCreated", user.getDateCreated());
        UserCollection.insertOne(document);

        accounts.put(user.getUsername(), user);

    }

    @Override
    public boolean userExists(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public User getUser(String username) {
            return accounts.get(username);
    }








}


