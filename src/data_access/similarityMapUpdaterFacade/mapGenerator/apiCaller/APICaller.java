/*
getScore method of this class calculates the similarity score between two texts
*/

package data_access.similarityMapUpdaterFacade.mapGenerator.apiCaller;

import okhttp3.*;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class APICaller implements APICallerInterface {
    private static final String API_URL = "https://api.dandelion.eu/datatxt/sim/v1/";
    private static final String API_TOKEN = "f001a9316b3b43fdac2fb8e88d9898ff";

        //calculates the similarity score between two texts
    public Float getSimilarityScore(String text1, String text2) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "?text1=%s&text2=%s&token=%s",text1, text2, API_TOKEN))
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            return((float)responseBody.getDouble("similarity"));
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getApiToken(){
        return API_TOKEN;
    }
    public static String getApiURL(){
        return API_URL;
    }

    private static String readTokenFromFile(String filename) { // might use later
        String token = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            token = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static void main(String[] args){ // testing
        APICaller api = new APICaller();
        String text1 = "I love feeding birds";
        String text2 = "Everyone hates feeding birds";
        System.out.println(getApiToken());
        System.out.println(getApiURL());
        System.out.println(api.getSimilarityScore(text1, text2));
    }
}