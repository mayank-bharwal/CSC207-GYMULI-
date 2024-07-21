package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import data_access.similarityMapUpdaterFacade.Facade;
import data_access.similarityMapUpdaterFacade.FacadeInterface;
import entity.*;
import org.bson.Document;
import use_case.account_creation.AccountCreationUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import use_case.recommendations.RecommendationDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.update_profile.UpdateProfileUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static data_access.similarityMapUpdaterFacade.mapUpdater.readDB.GetDB.*;
import static data_access.similarityMapUpdaterFacade.mapUpdater.readDB.GetDB.getCollectionID;
import static data_access.userMap_ignore.getMap;

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface,
        SendMessageUserDataAccessInterface, UpdateProfileUserDataAccessInterface, RecommendationDataAccessInterface {

//    String uri = getURI();
//    MongoClient mongoClient = MongoClients.create(uri);
//    MongoDatabase database = mongoClient.getDatabase(getDBName());
//    MongoCollection<Document> MessageCollection = database.getCollection("messages");
//    MongoCollection<Document> UserCollection = database.getCollection("users");
//    MongoCollection<Document> similarityCollection = database.getCollection(getCollectionName());
    private MongoClient mongoConnection;
    private Map<String, User> accounts = new HashMap<>();
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
                List<String> chats = (List<String>) doc.get("chats");
                Date date = doc.getDate("dateCreated");

                LocalDateTime dateCreated = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                User user = userFactory.createUser(username, password, bio, age, programOfStudy, interests, friends, chats, dateCreated);
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

    public Map<String, User> getAccounts() {
        return accounts;
    }

    @Override
    public void updateUser(String oldUsername, String newUsername, String password, String bio, String programOfStudy, Integer age,
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

    @Override
    public List<User> getNSimilarUsers(User user, int N) {

        Document doc = similarityCollection.find(new Document("_id", getCollectionID())).first();

        if (doc != null) {
            List<UserSimilarity> userSimilarities = new ArrayList<>();
            String username = user.getUsername();

            for (String key : doc.keySet()) {

                if (key == null || key.equals("_id")) continue; // Skip the _id field

                String[] users = key.replace("(", "").replace(")", "").split(", ");

                if (users.length == 2) {
                    String user1 = users[0];
                    String user2 = users[1];
                    Double scoreWrapper = doc.getDouble(key);

                    if (scoreWrapper == null) {
                        System.out.println("Null score for key: " + key);
                        continue;
                    }

                    double score = scoreWrapper.doubleValue();

                    if (user1.equals(username) || user2.equals(username)) {
                        String otherUser = user1.equals(username) ? user2 : user1;
                        userSimilarities.add(new UserSimilarity(otherUser, score));
                    }
                }
            }

            Collections.sort(userSimilarities, Comparator.comparingDouble(UserSimilarity::getScore).reversed());

            List<String> topNUsernames = userSimilarities.stream()
                    .limit(N)
                    .map(UserSimilarity::getUsername)
                    .collect(Collectors.toList());

            List<User> topNUsers = new ArrayList<>();
            for (String topUsername : topNUsernames) {
                User similarUser = getUser(topUsername);
                if (similarUser != null & !similarUser.getUsername().equals(username)) {
                    topNUsers.add(similarUser);
                }
            }

            return topNUsers;
        }
        System.out.println("Document Does not exist");
        return new ArrayList<>();
    }


    public static void main(String[] args) {
        // Example usage
        UserFactory userFactory = new CommonUserFactory();
        Map<String, Message> messages = new HashMap<>();
        MessageFactory messageFactory = new MessageFactory();

        User user = userFactory.createUser("Gippy", "love123", "Audiophile", 34, "Cinema Studies",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now());

        FacadeInterface facade = new Facade();
        //facade.UpdateDB(user, getMap());

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, getMap(), messages, messageFactory);
        //RecommendationDataAccessObject dao = new RecommendationDataAccessObject();

        User user1 = userDataAccessObject.getUser("Alice");
        if (user1 != null) {
            List<User> similarUsers = userDataAccessObject.getNSimilarUsers(user1,3);
            similarUsers.forEach(u -> System.out.println(u));
        } else {
            System.out.println("User 'Alice' not found.");
        }
    }
}
