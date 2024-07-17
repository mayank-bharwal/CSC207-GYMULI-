package data_access.similarityMapUpdaterFacade.mapUpdater;

import data_access.similarityMapUpdaterFacade.mapGenerator.MapGeneratorInterface;

public interface MapUpdaterInterface {
    public void updateMap(MapGeneratorInterface map);
}
