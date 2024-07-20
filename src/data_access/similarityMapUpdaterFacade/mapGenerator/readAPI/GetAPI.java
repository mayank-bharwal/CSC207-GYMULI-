package data_access.similarityMapUpdaterFacade.mapGenerator.readAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetAPI {

    private static final String API_URL;
    private static final String API_TOKEN;
    private static final String API_BODY;

    static {
        API_URL = readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapGenerator/readAPI/API_files/API_uri.txt");
        API_TOKEN = readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapGenerator/readAPI/API_files/API_key.txt");
        API_BODY = readTokenFromFile("src/data_access/similarityMapUpdaterFacade/mapGenerator/readAPI/API_files/API_body.txt");
    }

    public static String readTokenFromFile(String fileName) {
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

    public static String getAPI(String text1, String text2) {
        return String.format(API_URL + API_BODY, text1, text2, API_TOKEN);
    }
}