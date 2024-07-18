package data_access.similarityMapUpdaterFacade;

import data_access.similarityMapUpdaterFacade.mapGenerator.MapGenerator;
import data_access.similarityMapUpdaterFacade.mapUpdater.MapUpdater;
import entity.User;

import java.util.Map;

/*Update the global dictionary on the MongoDB for this user*/

public class UpdateMap implements UpdateMapInterface {
    public static void DBupdate(User user, Map<String,User> accounts){
        // call map generator and map updater to DBupdate global map in the DB
        MapGenerator mapGenerator = new MapGenerator();
        MapUpdater mapUpdater = new MapUpdater();
        mapUpdater.updateMap(mapGenerator.generateMap(user, accounts));

    }
}
