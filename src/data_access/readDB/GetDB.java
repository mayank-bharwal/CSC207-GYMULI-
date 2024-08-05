package data_access.readDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for reading database connection details from files.
 * This class provides static methods to retrieve various database-related configurations,
 * such as URI, database name, collection name, and collection ID.
 */

public class GetDB {

    /**
     * Retrieves the database URI from the specified file.
     *
     * @return The database URI as a string.
     */
    public static String getURI(){
        return readTokenFromFile("src/data_access/readDB/DB_files/DB_uri");
    }

    /**
     * Retrieves the database name from the specified file.
     *
     * @return The database name as a string.
     */

    public static String getDBName(){
        return readTokenFromFile("src/data_access/readDB/DB_files/DB_name");
    }


    /**
     * Retrieves the collection name from the specified file.
     *
     * @return The collection name as a string.
     */



    public static String getCollectionName(){
        return readTokenFromFile("src/data_access/readDB/DB_files/CollectionName");
    }

    /**
     * Retrieves the collection ID from the specified file.
     *
     * @return The collection ID as a string.
     */



    public static String getID(){
        return readTokenFromFile("src/data_access/readDB/DB_files/CollectionID");
    }


    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param fileName The path to the file to read.
     * @return The content of the file as a string.
     */

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
