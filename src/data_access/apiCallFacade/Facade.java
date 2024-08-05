package data_access.apiCallFacade;

import data_access.readDB.MongoConnection;
import data_access.apiCallFacade.apiCaller.APICaller;
import data_access.apiCallFacade.apiCaller.APICallerInterface;
import data_access.apiCallFacade.mapGenerator.MapGenerator;
import data_access.apiCallFacade.mapGenerator.MapGeneratorInterface;
import data_access.apiCallFacade.dbUpdater.MapUpdater;
import data_access.apiCallFacade.dbUpdater.MapUpdaterInterface;
import data_access.readDB.readDBInterface;
import entity.User;

import java.util.*;

public class Facade implements FacadeInterface {

    public void UpdateDB(User user, Map<String, User> accounts, readDBInterface mongoConnection){
        MapGeneratorInterface mapGenerator = new MapGenerator();
        MapUpdaterInterface mapUpdater = new MapUpdater();
        mapUpdater.updateMap(mapGenerator.generateMap(user, accounts), mongoConnection);
    }

    public String filter(String text){
        APICallerInterface apiCaller = new APICaller();
        return apiCaller.filterProfanity(text);
    }

    public void use_paid(boolean paid){
        APICallerInterface apiCaller = new APICaller();
        apiCaller.use_paid(paid);
    }

// ******************************
    // ignore this, testing purposes only
    public static void main(String[]args){
        // Create an instance of CommonUserFactory
//        CommonUserFactory userFactory = new CommonUserFactory();
//
//        List<String> hobbies5 = new ArrayList<>();
//        hobbies5.add("Dancing");
//        hobbies5.add("Painting");
//
//        List<String> friends5 = new ArrayList<>();
//        friends5.add("Charlie");
//        friends5.add("David");
//
//        List<String> chats5 = new ArrayList<>();
//        chats5.add("Chat9");
//        chats5.add("Chat10");
//
//        User user = userFactory.createUser(
//                "Mayank", "password131415", "Audiophile", 29, "Cinema Studies",
//                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now()
//        );
//
//        Map<String, User> accounts = new HashMap<>();
//        accounts = getMap();
//
//        MongoConnection mongoConnection = new MongoConnection();
        Facade facade = new Facade();
//        facade.UpdateDB(user, accounts, mongoConnection);
        System.out.println(facade.filter(" You are an 0, you piece of "));
    }
}
