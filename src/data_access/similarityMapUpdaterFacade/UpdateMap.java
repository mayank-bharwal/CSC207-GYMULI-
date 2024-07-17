package data_access.similarityMapUpdaterFacade;

import data_access.similarityMapUpdaterFacade.mapGenerator.MapGenerator;
import data_access.similarityMapUpdaterFacade.mapUpdater.MapUpdater;
import entity.User;

/*Update the global dictionary on the MongoDB for this user*/

public class UpdateMap implements UpdateMapInterface {
    public void update(User user){
        // call map generator and map updater to update global map in the DB
        MapGenerator mapGenerator = new MapGenerator();
        MapUpdater mapUpdater = new MapUpdater();
        mapUpdater(mapGenerator.generateMap(user));

    }
}
