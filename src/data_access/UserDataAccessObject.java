package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.similarityMapUpdaterFacade.Facade;
import data_access.similarityMapUpdaterFacade.FacadeInterface;
import entity.*;
import org.bson.Document;
import use_case.account_creation.AccountCreationUserDataAccessInterface;
import use_case.add_friends.AddFriendsUserDataAccessObject;
import use_case.login.LoginUserDataAccessInterface;

import use_case.recommendations.RecommendationsDataAccessInterface;
import use_case.update_profile.UpdateProfileUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static data_access.readDB.GetDB.getCollectionID;
import static data_access.userMap_ignore.getMap;

/**
 * Data Access Object for User-related operations.
 */

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface,
        UpdateProfileUserDataAccessInterface, AddFriendsUserDataAccessObject, RecommendationsDataAccessInterface {
    private MongoConnection mongoConnection;
    private MongoCollection<Document> UserCollection;
    private Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    /**
     * Constructor for UserDataAccessObject.
     *
     * @param userFactory        Factory to create User objects.
     * @param accounts           Map of User objects.
     * @param mongoConnection    MongoConnection instance.
     */


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

    /**
     * Retrieves the map of User objects.
     *
     * @return the map of User objects.
     */

    public Map<String, User> getAccounts() {
        return accounts;
    }

    /**
     * Checks if an account exists by username.
     *
     * @param username the username to check.
     * @return true if the account exists, false otherwise.
     */

    @Override
    public boolean AccountExists(String username) {
        return accounts.containsKey(username);
    }

    /**
     * Saves a User object to the database.
     *
     * @param user the User object to save.
     */

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

        //FacadeInterface Facade = new Facade();
        //Facade.UpdateDB(user, accounts, mongoConnection);

        accounts.put(user.getUsername(), user);
    }

    /**
     * Checks if a user exists by username.
     *
     * @param username the username to check.
     * @return true if the user exists, false otherwise.
     */

    @Override
    public boolean userExists(String username) {
        return accounts.containsKey(username);
    }

    /**
     * Adds a friend to the list of friends for both the current user and the specified friend.
     *
     * @param currentUser the username of the current user.
     * @param friend      the username of the friend to add.
     */

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

    /**
     * Retrieves a User object by username.
     *
     * @param username the username of the user to retrieve.
     * @return the User object if found, null otherwise.
     */

    @Override
    public User getUser(String username) {
        return accounts.get(username);
    }

    /**
     * Updates user information for a given username.
     *
     * @param oldUsername      the current username of the user.
     * @param newUsername      the new username to set, if provided.
     * @param password         the new password to set, if provided.
     * @param bio              the new bio to set, if provided.
     * @param programOfStudy   the new program of study to set, if provided.
     * @param age              the new age to set, if provided.
     * @param interests        the new interests to set, if provided.
     */

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

        Map<String, User> acct = new HashMap<>(accounts);
        acct.remove(user.getUsername());

        FacadeInterface Facade = new Facade();
        Facade.UpdateDB(user, acct, mongoConnection);

        System.out.println("user updated");

    }

    /**
     * Retrieves a list of N users most similar to the given user based on similarity scores.
     *
     * @param user the user for whom to find similar users.
     * @param N    the number of similar users to retrieve.
     * @return a list of the top N most similar User objects.
     */

    @Override
    public List<User> getNSimilarUsers(User user, int N) {

        Document doc = mongoConnection.getSimilarityCollection().find(new Document("_id", getCollectionID())).first();

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
                assert similarUser != null;
                if (!similarUser.getUsername().equals(username)) {
                    topNUsers.add(similarUser);
                }
            }

            return topNUsers;
        }
        System.out.println("Document Does not exist");
        return new ArrayList<>();
    }


    public static void main(String[] args) { // TESTING PURPOSES ONLY, REDUNDANT MAIN METHOD
        // Example usage
        UserFactory userFactory = new CommonUserFactory();
        Map<String, Message> messages = new HashMap<>();
        MessageFactory messageFactory = new MessageFactory();

        User user = userFactory.createUser("Gippy", "love123", "Audiophile", 34, "Cinema Studies",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now());

        FacadeInterface facade = new Facade();

        MongoConnection mongoConnection = new MongoConnection();
        facade.UpdateDB(user, getMap(), mongoConnection);

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, getMap(), mongoConnection);

        User user1 = userDataAccessObject.getUser("Alice");
        if (user1 != null) {
            List<User> similarUsers = userDataAccessObject.getNSimilarUsers(user1, 5);
            similarUsers.forEach(u -> System.out.println(u.getUsername()));
        } else {
            System.out.println("User 'Alice' not found.");
        }
    }

}
