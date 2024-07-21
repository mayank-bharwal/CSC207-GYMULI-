package data_access.similarityMapUpdaterFacade.mapUpdater;

import data_access.readDB.MongoConnection;
import org.json.JSONObject;

public interface MapUpdaterInterface {
    public void updateMap(JSONObject map, MongoConnection mongoConnection);
}
