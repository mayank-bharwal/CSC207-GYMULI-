package data_access.similarityMapUpdaterFacade.mapUpdater;

import data_access.similarityMapUpdaterFacade.mapGenerator.MapGeneratorInterface;
import org.json.JSONObject;

public interface MapUpdaterInterface {
    public void updateMap(JSONObject map );
}
