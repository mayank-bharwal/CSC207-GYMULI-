package data_access.readDB;

import data_access.similarityMapUpdaterFacade.mapGenerator.readAPI.GetAPI;

public class GetDB {
    public static String getURI(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/DB_uri");
    }

    public static String getDBName(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/DB_name");
    }

    public static String getCollectionName(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/CollectionName");
    }

    public static String getCollectionID(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/CollectionID");
    }

    private static String readTokenFromFile(String fileName) {
        return GetAPI.readTokenFromFile(fileName);
    }
}
