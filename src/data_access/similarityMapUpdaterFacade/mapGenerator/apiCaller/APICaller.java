/*
getScore method of this class calculates the similarity score between two texts
*/

package data_access.similarityMapUpdaterFacade.mapGenerator.apiCaller;

import okhttp3.*;
import org.json.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data_access.similarityMapUpdaterFacade.mapGenerator.apiCaller.readAPI.GetAPI.getAPI;


public class APICaller {

    public Float getSimilarityScore(String text1, String text2) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS) // Increase timeout to 60 seconds
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        String url = getAPI(text1,text2); // makes our application API agnostic since we just need to change text files
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            JSONObject responseBody = new JSONObject(response.body().string());
            //System.out.println(responseBody);

            // Extract and return the similarity score if it exists
            if (responseBody.has("similarity")) {
                return (float) responseBody.getDouble("similarity");
            } else {
                throw new JSONException("Response does not contain 'similarity' field");
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
