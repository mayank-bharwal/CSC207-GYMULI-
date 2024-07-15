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

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface, SendMessageUserDataAccessInterface {
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

                Document mDoc = cursor.next();
                String chatName = mDoc.getString("chatName");
                String sender = mDoc.getString("username");
                String receiver = mDoc.getString("receiver");
                String messageText = mDoc.getString("message");
                Date sendDate = mDoc.getDate("dateCreated");

                LocalDateTime dateCreatedM = sendDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                Message message = messageFactory.createMessage(chatName, sender, receiver, messageText, dateCreatedM);
                messages.put(chatName, message);


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

}


