package data_access.readDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetDB {
    public static String getURI(){
        return readTokenFromFile("src/data_access/readDB/DB_files/DB_uri");
    }

    public static String getDBName(){
        return readTokenFromFile("src/data_access/readDB/DB_files/DB_name");
    }

    public static String getCollectionName(){
        return readTokenFromFile("src/data_access/readDB/DB_files/CollectionName");
    }

    public static String getCollectionID(){
        return readTokenFromFile("src/data_access/readDB/DB_files/CollectionID");
    }

    private static String readTokenFromFile(String fileName) {
        StringBuilder token = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                token.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token.toString();
    }
}
