package DataAccess.MongoDB;

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
import use_case.login.LoginUserDataAccessInterface;

import use_case.send_message.SendMessageUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface,
        SendMessageUserDataAccessInterface {
    String uri = "mongodb+srv://UmerFarooqui:RealMadrid123Canon@cluster0.vbtnfad.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    MongoClient mongoClient = MongoClients.create(uri);
    MongoDatabase database = mongoClient.getDatabase("GYMULI");
    MongoCollection<Document> MessageCollection = database.getCollection("messages");
    MongoCollection<Document> UserCollection = database.getCollection("users");
    private  Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;
    private Map<String, Message> messages = new HashMap<>();
    private MessageFactory messageFactory;


    public UserDataAccessObject(UserFactory userFactory, Map<String, User> accounts, Map<String, Message> messages, MessageFactory messageFactory) {

        this.userFactory = userFactory;
        this.accounts = accounts;
        this.messages = messages;
        this.messageFactory = messageFactory;

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
                Date date = doc.getDate("dateCreated");

                LocalDateTime dateCreated = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                User user = userFactory.createUser(username, password, bio, age, programOfStudy, interests, friends, dateCreated);
                accounts.put(username, user);

                try (MongoCursor<Document> messageCursor = MessageCollection.find().iterator()) {
                    while (messageCursor.hasNext()) {
                        Document messageDoc = messageCursor.next();
                        String chatName = messageDoc.getString("chatName");
                        String sender = messageDoc.getString("username");
                        String receiver = messageDoc.getString("receiver");
                        String messageText = messageDoc.getString("message");
                        Date sendDate = messageDoc.getDate("dateCreated");

                        LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());

                        Message message = messageFactory.createMessage(chatName, sender, receiver, messageText, dateCreatedM);
                        messages.put(chatName, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


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
    public void saveMessage(Message message) {
        Document document = new Document();
        document.append("chatName", message.getChatName());
        document.append("username", message.getSender());
        document.append("receiver", message.getReceiver());
        document.append("message", message.getMessage());
        document.append("dateCreated", LocalDateTime.now());
        MessageCollection.insertOne(document);

        messages.put(message.getChatName(), message);
    }

    @Override
    public User getUser(String username) {
        return accounts.get(username);
    }

         @Override
    public void updateUser(String oldUsername ,String newUsername, String password, String bio, String programOfStudy, Integer age,
                           List<String> interests) {

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

