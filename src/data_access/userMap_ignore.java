/*IGNORE THIS CLASS, IT'S FOR TESTING FACADE ONLY*/

package data_access;

import entity.CommonUserFactory;
import entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class userMap_ignore {
    public static Map<String, User> getMap() {

        Map<String, User> userMap = new HashMap<>();

        // Create an instance of CommonUserFactory
        CommonUserFactory userFactory = new CommonUserFactory();

        // CommonUser(String username, String password, String bio, Integer age, String programOfStudy,
        //                      List<String> interests, List<String> friends, List<String> chats, LocalDateTime dateCreated)

        // Create an ArrayList for each user attribute
        List<String> hobbies1 = new ArrayList<>();
        hobbies1.add("Reading");
        hobbies1.add("Traveling");

        List<String> friends1 = new ArrayList<>();
        friends1.add("Bob");
        friends1.add("Charlie");

        List<String> chats1 = new ArrayList<>();
        chats1.add("Chat1");
        chats1.add("Chat2");

        User user1 = userFactory.createUser(
                "Alice", "password123", "Bio for Alice", 25, "Computer Science",
                hobbies1, friends1, chats1, LocalDateTime.now()
        );
        userMap.put(user1.getUsername(), user1);

        List<String> hobbies2 = new ArrayList<>();
        hobbies2.add("Hiking");
        hobbies2.add("Chess");

        List<String> friends2 = new ArrayList<>();
        friends2.add("Alice");
        friends2.add("David");

        List<String> chats2 = new ArrayList<>();
        chats2.add("Chat3");
        chats2.add("Chat4");

        User user2 = userFactory.createUser(
                "Bob", "password456", "Bio for Bob", 30, "Mathematics",
                hobbies2, friends2, chats2, LocalDateTime.now()
        );
        userMap.put(user2.getUsername(), user2);

        List<String> hobbies3 = new ArrayList<>();
        hobbies3.add("Cooking");
        hobbies3.add("Gaming");

        List<String> friends3 = new ArrayList<>();
        friends3.add("Alice");
        friends3.add("Eve");

        List<String> chats3 = new ArrayList<>();
        chats3.add("Chat5");
        chats3.add("Chat6");

        User user3 = userFactory.createUser(
                "Charlie", "password789", "Bio for Charlie", 22, "Physics",
                hobbies3, friends3, chats3, LocalDateTime.now()
        );
        userMap.put(user3.getUsername(), user3);

        List<String> hobbies4 = new ArrayList<>();
        hobbies4.add("Photography");
        hobbies4.add("Music");

        List<String> friends4 = new ArrayList<>();
        friends4.add("Bob");
        friends4.add("Eve");

        List<String> chats4 = new ArrayList<>();
        chats4.add("Chat7");
        chats4.add("Chat8");

        User user4 = userFactory.createUser(
                "David", "password101112", "Bio for David", 27, "Biology",
                hobbies4, friends4, chats4, LocalDateTime.now()
        );
        userMap.put(user4.getUsername(), user4);

        List<String> hobbies5 = new ArrayList<>();
        hobbies5.add("Dancing");
        hobbies5.add("Painting");

        List<String> friends5 = new ArrayList<>();
        friends5.add("Charlie");
        friends5.add("David");

        List<String> chats5 = new ArrayList<>();
        chats5.add("Chat9");
        chats5.add("Chat10");

        User user5 = userFactory.createUser(
                "Eve", "password131415", "Bio for Eve", 29, "Chemistry",
                hobbies5, friends5, chats5, LocalDateTime.now()
        );
        userMap.put(user5.getUsername(), user5);


        List<String> hobbies6 = new ArrayList<>();
        hobbies5.add("Boxing");
        hobbies5.add("Music");

        List<String> friends6 = new ArrayList<>();
        friends5.add("Bob");
        friends5.add("David");

        List<String> chats6 = new ArrayList<>();
        chats5.add("Chat9");
        chats5.add("Chat10");

        User user6 = userFactory.createUser(
                "Mayank", "password131415", "Punjabi", 29, "Computer Science",
                hobbies5, friends5, chats5, LocalDateTime.now()
        );
        userMap.put(user6.getUsername(), user6);

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
        Map<String, User> Map = userMap_ignore.getMap();
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