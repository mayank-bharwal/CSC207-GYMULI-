package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import data_access.apiCallFacade.apiCaller.APICaller;
import data_access.readDB.readDBInterface;
import data_access.apiCallFacade.Facade;
import data_access.apiCallFacade.FacadeInterface;
import static com.mongodb.client.model.Filters.eq;

import entity.*;
import org.bson.Document;
import use_case.account_creation.AccountCreationUserDataAccessInterface;
import use_case.add_friends.AddFriendsUserDataAccessObject;
import use_case.login.LoginUserDataAccessInterface;

import use_case.recommendations.RecommendationsDataAccessInterface;
import use_case.remove_friends.RemoveFriendsUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.update_profile.UpdateProfileUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Data Access Object for User-related operations.
 */

public class UserDataAccessObject implements AccountCreationUserDataAccessInterface, LoginUserDataAccessInterface,
        UpdateProfileUserDataAccessInterface, AddFriendsUserDataAccessObject, RecommendationsDataAccessInterface,
        RemoveFriendsUserDataAccessInterface, SearchUserDataAccessInterface {
          
    private readDBInterface mongoConnection;
    private MongoCollection<Document> UserCollection;
    private Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;
    private FacadeInterface facade = new Facade();

    /**
     * Constructor for UserDataAccessObject.
     *
     * @param userFactory        Factory to create User objects.
     * @param accounts           Map of User objects.
     * @param mongoConnection    MongoConnection instance.
     */


    public UserDataAccessObject(UserFactory userFactory, Map<String, User> accounts, readDBInterface mongoConnection) {

        this.userFactory = userFactory;
        this.accounts = accounts;
        this.mongoConnection = mongoConnection;
        this.UserCollection = mongoConnection.getUserCollection();

        /**
         * CHANGE facade.use_paid to true when NOT TESTING
         */
        facade.use_paid(false); // false in TESTING, true in PRODUCTION


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

     public void userUpdate(String user){

        User cU = accounts.get(user);
        Document queryResult = UserCollection.find(eq("username", user)).first();

        String username = queryResult.getString("username");
        String password = queryResult.getString("password");
        String bio = queryResult.getString("bio");
        Integer age = queryResult.getInteger("age");
        String programOfStudy = queryResult.getString("programOfStudy");
        List<String> interests = (List<String>) queryResult.get("interests");
        List<String> friends = (List<String>) queryResult.get("friends");
        List<String> chats = (List<String>) queryResult.get("chats");
        Date date = queryResult.getDate("dateCreated");

         LocalDateTime dateCreated = date.toInstant()
                 .atZone(ZoneId.systemDefault())
                 .toLocalDateTime();

         cU.setChats(chats);
         cU.setFriends(friends);

         System.out.println("work done");


//         user.setUsername(username);
//         user.setPassword(password);
//         user.setBio(bio);
//         user.setAge(age);
//         user.setProgramOfStudy(programOfStudy);
//         user.setInterests(interests);
//         user.setFriends(friends);
//         user.setChats(chats);
//         user.setDateCreated(dateCreated);


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

        facade.UpdateDB(user, accounts, mongoConnection);

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

        List<String> frnds = user.getFriends();

        for (String f: frnds) {
            UserCollection.updateOne(
                    Filters.eq("username", f),
                    Updates.pull("friends", oldUsername)
            );
            UserCollection.updateOne(
                    Filters.eq("username", f),
                    Updates.addToSet("friends", newUsername)
            );

            User fUser = accounts.get(f);
            fUser.getFriends().remove(oldUsername);
            fUser.getFriends().add(newUsername);

        }

        User newUser = userFactory.createUser(newUsername, password, bio, age, programOfStudy, user.getInterests(), user.getFriends(), user.getChats(), user.getDateCreated());
        accounts.remove(oldUsername);

        facade.UpdateDB(user, accounts, mongoConnection);// 2

        accounts.put(newUsername, newUser);
        System.out.println("user updated");
        System.out.println(accounts.get(newUsername).getUsername());

    }

    /**
     * Retrieves a list of N users most similar to the given user based on similarity scores.
     *
     * @param user the user for whom to find similar users.
     * @param N    the number of similar users to retrieve.
     * @return a list of the top N most similar User objects.
     */

    @Override
    public Map<User, Double> getNSimilarUsers(User user, int N) {
        Document doc = mongoConnection.getSimilarityCollection().find(new Document("_id",mongoConnection.getCollectionID())).first();
        System.out.println("getNSimilarUsers for " + user.getUsername() + ": Document exists: " + (doc != null));

        if (doc != null) {
            List<Map.Entry<User, Double>> userSimilarities = new ArrayList<>();
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
                        User similarUser = getUser(otherUser);
                        if (similarUser != null) {
                            userSimilarities.add(new AbstractMap.SimpleEntry<>(similarUser, score));
                        }
                    }
                }
            }

            userSimilarities.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

            Map<User, Double> topNUsers = userSimilarities.stream()
                    .limit(N)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            System.out.println("Found similar users for " + username + ": " + topNUsers.size());
            return topNUsers;
        }
        System.out.println("Document does not exist for user: " + user.getUsername());
        return new HashMap<>();
    }


    @Override
    public boolean isFriend(String user1, String user2) {
        User user = getUser(user1);
        return user.getFriends().contains(user2);
    }

    @Override
    public void remove(String user1, String user2) {
        User user = getUser(user1);
        User friend = getUser(user2);

        if (user != null && friend != null) {


            UserCollection.updateOne(
                    Filters.eq("username", user1),
                    Updates.pull("friends", user2)
            );

            UserCollection.updateOne(
                    Filters.eq("username", user2),
                    Updates.pull("friends", user1)


            );
            user.getFriends().remove(user2);
            friend.getFriends().remove(user1);

        } else {
            System.out.println("User not found");
        }


    }
}
