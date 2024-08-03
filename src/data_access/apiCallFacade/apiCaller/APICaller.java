package data_access.apiCallFacade.apiCaller;

import okhttp3.*;
import org.json.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data_access.apiCallFacade.apiCaller.readAPI.GetAPI.*;

public class APICaller implements APICallerInterface {
    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    @Override
    public float getSimilarityScore(String text1, String text2) {

        String URL = getAPI(text1, text2);
        Request request = new Request.Builder()
                .url(URL)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                System.out.println("First API failed, Trying the backup API");

                String URL2 = getBackupAPI(text1, text2);
                Request request2 = new Request.Builder()
                        .url(URL2)
                        .build();
                response = client.newCall(request2).execute();

                if (!response.isSuccessful()) {
                    System.out.println("Second API failed, Trying the Third PAID API");
                    Request request3 = new Request.Builder()
                            .url(getThirdAPI(text1, text2))
                            .get()
                            .addHeader("x-rapidapi-key", getKey())
                            .addHeader("x-rapidapi-host", getBody())
                            .build();

                    response = client.newCall(request3).execute();
                }
            }

            JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.has("similarity")) {
                return (float) responseBody.getDouble("similarity") + 0.01f;
            } else {
                throw new JSONException("Response does not contain 'similarity' field");
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException("API call failed -- All three", e);
        }
    }

    @Override
    public String filterProfanity(String text1) {

        Request request = new Request.Builder()
                .url(getProfanityAPI(text1))
                .get()
                .addHeader("X-Api-Key", getProfKey())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("Profanity API failed"+ response.body().string());
                return text1;
            }else{
                JSONObject responseBody = new JSONObject(response.body().string());
                if (responseBody.has("censored")) {
                    return responseBody.getString("censored");
                } else {
                    System.out.println("Response does not contain 'censored' field");
                    return text1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[]args){
            APICaller apicaller = new APICaller();
            //Float score = apicaller.getSimilarityScore("Mike tyson Loves Boxing $$$$ ##@@9881989^^&&!!!++]{{", "My dog tyson");
            //System.out.println("Similarity Score: " + score);

            System.out.println(apicaller.filterProfanity("Damn, Mike Tyson is a boxer and shit"));
        }
    }

