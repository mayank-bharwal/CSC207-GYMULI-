package data_access.similarityMapUpdaterFacade;

import data_access.similarityMapUpdaterFacade.mapGenerator.MapGenerator;
import data_access.similarityMapUpdaterFacade.mapUpdater.MapUpdater;
import entity.User;

import java.util.Map;

public class UpdateDB implements UpdateDBInterface {

    public void UpdateDB(User user, Map<String, User> accounts){
        MapGenerator mapGenerator = new MapGenerator();
        MapUpdater mapUpdater = new MapUpdater();
        mapUpdater.updateMap(mapGenerator.generateMap(user, accounts)) ;
    }
}
