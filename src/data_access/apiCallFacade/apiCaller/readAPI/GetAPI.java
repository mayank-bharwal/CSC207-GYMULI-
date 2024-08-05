package data_access.apiCallFacade.apiCaller.readAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetAPI {

    private static final String API;
    private static final String API_TOKEN;
    private static final String BACKUP_TOKEN;

    private static final String API_URL;
    private static final String API_BODY;
    private static final String API_KEY;

    private static final String API_PROF;

    private static final String PROF_KEY;
    private static final String API_BACKUP;

    static {
        API = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_");
        API_TOKEN = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_key_2");
        BACKUP_TOKEN = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_key_3");
        API_BACKUP = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_BACKUP");

        API_URL = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_uri");
        API_KEY = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_key");
        API_BODY = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/API_body");

        API_PROF = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/Prof_API");
        PROF_KEY = readTokenFromFile("src/data_access/apiCallFacade/apiCaller/readAPI/API_files/Prof_Key");
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
        return String.format(API, text1.replaceAll("[^a-zA-Z ]", ""), text2.replaceAll("[^a-zA-Z ]", ""), API_TOKEN);
    }

    public static String getBackupAPI() {
        return API_BACKUP;
    }

    public static String getThirdAPI(String text1, String text2) {
        return String.format(API_URL, text1.replaceAll("[^a-zA-Z ]", ""), text2.replaceAll("[^a-zA-Z ]", ""));
    }

    public static String getProfanityAPI(String text1) {
        return String.format(API_PROF + text1);
    }

    public static String getProfKey(){
        return PROF_KEY;
    }

    public static String getKey(){
        return API_KEY;
    }

    public static String getBody() {
        return API_BODY;
    }

    public static void main(String[] args) {
        //System.out.println(callAPI("Mike tyson Loves Boxing", "My dog is named Tyson"));
        System.out.println(getProfKey());
    }
}