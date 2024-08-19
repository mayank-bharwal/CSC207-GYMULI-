package data_access.apiCallFacade;

/**
 * Facade design pattern to interact with complex subsystems
 * (Various API calls, Database Read & Write, Data manipulation etc.)
*/

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
    MapGeneratorInterface mapGenerator = new MapGenerator();
    MapUpdaterInterface mapUpdater = new MapUpdater();
    APICallerInterface apiCaller = new APICaller();
    readDBInterface mongoConnection;

    /**
     * Constructor for using Facade for Database implementation
     * @param mongoConnection
     */
    public Facade(readDBInterface mongoConnection){
        this.mongoConnection = mongoConnection;
    }

    /**
     * Constructor overloaded to provide an alternate implementation without Database
     */
    public Facade (){};

    /**
     * Returns a map between a user and it's similarity score to
     * the current user( who is calling the method)
     * @param user
     * @param accounts
     * @param n
     * @return
     */
    @Override
    public Map<User, Double> getMap(User user,Map<String,User> accounts, int n){
        return mapGenerator.generateDoc(user,n,accounts);
    }

    /**
     * Obsolete method from an earlier implementation of Similarity Scores
     * @param user
     * @param accounts
     */
    @Override
    public void UpdateDB(User user, Map<String,User> accounts){
        mapUpdater.updateMap(mapGenerator.generateMap(user, accounts), mongoConnection);
    }

    /**
     * Filters profanity from a text with an API call
     * @param text
     * @return
     */
    @Override
    public String filter(String text){
        return apiCaller.filterProfanity(text);
    }

    /**
     * Switch to turn paid api on/off to save costs
     * @param paid
     */
    @Override
    public void use_paid(boolean paid){
        apiCaller.use_paid(paid);
    }
}
