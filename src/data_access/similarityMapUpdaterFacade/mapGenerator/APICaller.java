package data_access.similarityMapUpdaterFacade.mapGenerator;


import okhttp3.*;
import org.json.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data_access.similarityMapUpdaterFacade.mapGenerator.readAPI.GetAPI.getAPI;
import static data_access.similarityMapUpdaterFacade.mapGenerator.readAPI.GetAPI.getBackupAPI;

public class APICaller {

    public Float getSimilarityScore(String text1, String text2) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS) // Increase timeout to 60 seconds
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        String url = getAPI(text1, text2);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("Primary API failed, Trying the backup API");
                String backupUrl = getBackupAPI(text1, text2);
                System.out.println("Backup API URL: " + backupUrl);
                request = new Request.Builder()
                        .url(backupUrl)
                        .build();

                response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    throw new IOException(response.body().string());
                }
            }

            JSONObject responseBody = new JSONObject(response.body().string());
            //System.out.println(responseBody);

            // Extract and return the similarity score if it exists
            if (responseBody.has("similarity")) {
                return (float) responseBody.getDouble("similarity") + 0.01f;
            } else {
                throw new JSONException("Response does not contain 'similarity' field");
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException("API call failed -- both primary and backup", e);
        }
    }
}
