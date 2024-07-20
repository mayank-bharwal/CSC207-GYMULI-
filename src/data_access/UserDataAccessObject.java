package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import entity.Message;
import entity.MessageFactory;
import entity.User;
import entity.UserFactory;
import org.bson.Document;
import use_case.account_creation.AccountCreationUserDataAccessInterface;
import use_case.add_friends.AddFriendsUserDataAccessObject;
import use_case.login.LoginUserDataAccessInterface;

import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.update_profile.UpdateProfileUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface,
         UpdateProfileUserDataAccessInterface, AddFriendsUserDataAccessObject {
    private MongoConnection mongoConnection;
    private MongoCollection<Document> UserCollection;
    private  Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;



    public UserDataAccessObject(UserFactory userFactory, Map<String, User> accounts, MongoConnection mongoConnection) {

        this.userFactory = userFactory;
        this.accounts = accounts;
        this.mongoConnection = mongoConnection;
        this.UserCollection = mongoConnection.getUserCollection();


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
                List<String> chats = (List<String>) doc.get("chats");
                Date date = doc.getDate("dateCreated");

                LocalDateTime dateCreated = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                User user = userFactory.createUser(username, password, bio, age, programOfStudy, interests, friends, chats, dateCreated);
                accounts.put(username, user);


            }
        }
    }

    public Map<String, User> getAccounts() {
        return accounts;
    }

    @Override
    public boolean AccountExists(String username) {return accounts.containsKey(username);}

    @Override


    public void save(User user) { // will call Text API here
        Document document = new Document();
        document.append("username", user.getUsername());
        document.append("password", user.getPassword());
        document.append("bio", user.getBio());
        document.append("age", user.getAge());
        document.append("programOfStudy", user.getProgramOfStudy());
        document.append("interests", user.getInterests());
        document.append("friends", user.getFriends());
        document.append("chats", user.getChats());
        document.append("dateCreated", user.getDateCreated());
        UserCollection.insertOne(document);

        accounts.put(user.getUsername(), user);

    }

    @Override
    public boolean userExists(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public void addFriend(String currentUser, String friend) {
        accounts.get(currentUser).getFriends().add(friend);
        accounts.get(friend).getFriends().add(currentUser);

        Document filter = new Document("username", currentUser);
        Document update = new Document("$push", new Document("friends", friend));
        UserCollection.updateOne(filter, update);

        Document filter2 = new Document("username", friend);
        Document update2 = new Document("$push", new Document("friends", currentUser));
        UserCollection.updateOne(filter2, update2);
    }


    @Override
    public User getUser(String username) {
        return accounts.get(username);
    }

    @Override
    public void updateUser(String oldUsername ,String newUsername, String password, String bio, String programOfStudy, Integer age,
                           List<String> interests) { // maybe call text api here too

        Document filter = new Document("username", oldUsername);

        Document updateFields = new Document();

        if (newUsername != null && !newUsername.trim().isEmpty()) {
            updateFields.append("username", newUsername);
        }
        if (password != null && !password.trim().isEmpty()) {
            updateFields.append("password", password);
        }
        if (bio != null && !bio.trim().isEmpty()) {
            updateFields.append("bio", bio);
        }
        if (programOfStudy != null && !programOfStudy.trim().isEmpty()) {
            updateFields.append("programOfStudy", programOfStudy);
        }
        if (age != null) {
            updateFields.append("age", age);
        }
        if (interests != null && !interests.isEmpty()) {
            updateFields.append("interests", interests);
        }

        Document update = new Document("$set", updateFields);
        UserCollection.updateOne(filter, update);


        User user = accounts.get(oldUsername);

        user.setAge(age);
        user.setUsername(newUsername);
        user.setPassword(password);
        user.setBio(bio);
        user.setInterests(interests);
        user.setProgramOfStudy(programOfStudy);


    }

}

