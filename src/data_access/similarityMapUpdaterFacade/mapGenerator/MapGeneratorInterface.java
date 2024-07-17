package data_access.similarityMapUpdaterFacade.mapGenerator;

import entity.User;

public interface MapGeneratorInterface {
    public MapGeneratorInterface generateMap(User user); //returns an object of itself
}
