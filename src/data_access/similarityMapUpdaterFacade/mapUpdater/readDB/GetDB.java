package data_access.similarityMapUpdaterFacade.mapUpdater.readDB;

import data_access.similarityMapUpdaterFacade.mapGenerator.apiCaller.readAPI.GetAPI;

public class GetDB {
    public static String getURI(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/DB_uri.txt");
    }

    public static String getDBName(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/DB_name.txt");
    }

    public static String getCollectionName(){
        return readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapUpdater/readDB/DB_files/CollectionName.txt");
    }
    private static String readTokenFromFile(String fileName) {
        return GetAPI.readTokenFromFile(fileName);
    }
}
