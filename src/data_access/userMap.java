package data_access;

import entity.CommonUserFactory;
import entity.User;

import java.time.LocalDateTime;
//import java.util.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userMap {
    public static Map<String, User> getMap() {
        Map<String, User> userMap = new HashMap<>();

        // Create an instance of CommonUserFactory
        CommonUserFactory userFactory = new CommonUserFactory();

        // Create 5 users for testing
        User user1 = userFactory.createUser(
                "Alice", "password123", "Bio for Alice", 25, "Computer Science",
                Arrays.asList("Reading", "Traveling"), Arrays.asList("Bob", "Charlie"),
                Arrays.asList("Chat1", "Chat2"), LocalDateTime.now()
        );
        userMap.put(user1.getUsername(), user1);

        User user2 = userFactory.createUser(
                "Bob", "password456", "Bio for Bob", 30, "Mathematics",
                Arrays.asList("Hiking", "Chess"), Arrays.asList("Alice", "David"),
                Arrays.asList("Chat3", "Chat4"), LocalDateTime.now()
        );
        userMap.put(user2.getUsername(), user2);

        User user3 = userFactory.createUser(
                "Charlie", "password789", "Bio for Charlie", 22, "Physics",
                Arrays.asList("Cooking", "Gaming"), Arrays.asList("Alice", "Eve"),
                Arrays.asList("Chat5", "Chat6"), LocalDateTime.now()
        );
        userMap.put(user3.getUsername(), user3);

        User user4 = userFactory.createUser(
                "David", "password101112", "Bio for David", 27, "Biology",
                Arrays.asList("Photography", "Music"), Arrays.asList("Bob", "Eve"),
                Arrays.asList("Chat7", "Chat8"), LocalDateTime.now()
        );
        userMap.put(user4.getUsername(), user4);

        User user5 = userFactory.createUser(
                "Eve", "password131415", "Bio for Eve", 29, "Chemistry",
                Arrays.asList("Dancing", "Painting"), Arrays.asList("Charlie", "David"),
                Arrays.asList("Chat9", "Chat10"), LocalDateTime.now()
        );
        userMap.put(user5.getUsername(), user5);

//        // Add more users as needed to reach at least 10 elements
//        // Example:
//        List<String> interests6 = new ArrayList<>();
//        interests6.add("Sports");
//        interests6.add("Cooking");
//        User user6 = new User("Yasamanro", "passwordGHI", "Sports enthusiast and chef", 29, "Sports Management", interests6, new ArrayList<>(), now);
//        userMap.put(user6.getUsername(), user6);
//
//        List<String> interests7 = new ArrayList<>();
//        interests7.add("Gardening");
//        interests7.add("Environment");
//        User user7 = new User("Sadia", "passwordJKL", "Nature lover and gardener", 26, "Environmental Studies", interests7, new ArrayList<>(), now);
//        userMap.put(user7.getUsername(), user7);
//
//        List<String> interests8 = new ArrayList<>();
//        interests8.add("Fashion");
//        interests8.add("Design");
//        User user8 = new User("Justin", "passwordMNO", "Fashionista and designer", 31, "Fashion Design", interests8, new ArrayList<>(), now);
//        userMap.put(user8.getUsername(), user8);
//
//        List<String> interests9 = new ArrayList<>();
//        interests9.add("Film");
//        interests9.add("Cinema");
//        User user9 = new User("Trudeau", "passwordPQR", "Cinephile and filmmaker", 33, "Film Studies", interests9, new ArrayList<>(), now);
//        userMap.put(user9.getUsername(), user9);
//
//        List<String> interests10 = new ArrayList<>();
//        interests10.add("Music");
//        interests10.add("Singing");
//        User user10 = new User("Biden", "passwordSTU", "Music lover and vocalist", 24, "Music Performance", interests10, new ArrayList<>(), now);
//        userMap.put(user10.getUsername(), user10);

//        for (Map.Entry<String, User> entry : userMap.entrySet()) {
//            System.out.println("Username: " + entry.getKey());
//            System.out.println("User Info: " + entry.getValue());
//            System.out.println();
//        }
        return userMap;
    }

    public static void main(String []args) {
        Map<String, User> Map = userMap.getMap();
        for (Map.Entry<String, User> entry : Map.entrySet()) {
            String key = entry.getKey();
            User user = entry.getValue();
            System.out.println("Key: " + key);
            System.out.println("Value: " + user.getUsername() + ", " + user.getBio() + ", " + user.getAge()
                    + ", " + user.getProgramOfStudy() + ", " + user.getInterests() + ", " + user.getFriends()
                    + ", " + user.getChats() + ", " + user.getDateCreated());
            System.out.println();
        }
    }
}