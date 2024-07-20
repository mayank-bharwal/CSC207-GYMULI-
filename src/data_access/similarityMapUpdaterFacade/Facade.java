package data_access.similarityMapUpdaterFacade;

import data_access.similarityMapUpdaterFacade.mapGenerator.MapGenerator;
import data_access.similarityMapUpdaterFacade.mapUpdater.MapUpdater;
import entity.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.CommonUserFactory;

import static data_access.userMap.getMap;

public class Facade implements FacadeInterface {

    public void UpdateDB(User user, Map<String, User> accounts){
        MapGenerator mapGenerator = new MapGenerator();
        MapUpdater mapUpdater = new MapUpdater();
        mapUpdater.updateMap(mapGenerator.generateMap(user, accounts)) ;
    }

    public static void main(String[]args){
        // Create an instance of CommonUserFactory
        CommonUserFactory userFactory = new CommonUserFactory();

        User user = userFactory.createUser(
                "Mayank", "password123", "I love movies", 45, "Computer Science",
                Arrays.asList("Coding", "Traveling"), Arrays.asList("Bob", "Charlie"),
                Arrays.asList("Chat1", "Chat2"), LocalDateTime.now()
        );

        Map<String, User> accounts = new HashMap<>();
        accounts = getMap();

        FacadeInterface facade = new Facade();
        facade.UpdateDB(user, accounts);
    }
}
