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

    private boolean use_paid = true;

    @Override
    public float getSimilarityScore(String text1, String text2) {

        String json = String.format("{\"text_1\": \""+text1.replaceAll("[^a-zA-Z ]", "")+"\", \"text_2\": \""+text2.replaceAll("[^a-zA-Z ]", "")+"\"}");

        RequestBody body = RequestBody.create(
                json,
                MediaType.get("application/json; charset=utf-8")
        );
        Request request = new Request.Builder()
                .url(getBackupAPI())
                .header("X-Api-Key", getProfKey())
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                //System.out.println(response); // debugging
                //System.out.println("First API failed, Trying the backup API");
                Request request2 = new Request.Builder()
                        .url(getAPI(text1, text2))
                        .build();

                response = client.newCall(request2).execute();

                if (!response.isSuccessful()) {
                    //System.out.println(response);//debugging
                    if (use_paid == true) {
                        //System.out.println("Second API failed, Trying the Third PAID API");
                        Request request3 = new Request.Builder()
                                .url(getThirdAPI(text1, text2))
                                .get()
                                .addHeader("x-rapidapi-key", getKey())
                                .addHeader("x-rapidapi-host", getBody())
                                .build();

                        response = client.newCall(request3).execute();
                        if (!response.isSuccessful()) {System.out.println("Third Paid API used and Failed");}
                    } else {
                        //System.out.println("Second API failed and NOT using the PAID API");
                        //pass;
                    }
                }
            } else {
                //System.out.println("Primary API successful");
            }

            if (response.isSuccessful()) {
                JSONObject responseBody = new JSONObject(response.body().string());
                if (responseBody.has("similarity")) {
                    return (float) responseBody.getDouble("similarity") + 0.01f;
                } else {
                    throw new JSONException("Response does not contain 'similarity' field");
                }
            } else {
                /*
                Will never be used in production since third api would be called
                 */
                return (float) Math.random() * 0.5f;
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
                //System.out.println("Profanity API failed"+ response.body().string());
                return text1;
            }else{
                JSONObject responseBody = new JSONObject(response.body().string());
                if (responseBody.has("censored")) {
                    return responseBody.getString("censored");
                } else {
                    //System.out.println("Response does not contain 'censored' field");
                    return text1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void use_paid(boolean paid) {
        this.use_paid = paid;
    }

    public static void main (String[]args){
            APICaller apicaller = new APICaller();
            Float score = apicaller.getSimilarityScore("Mike tyson Loves Boxing $$$$ ##@@9881989^^&&!!!++]{{", "My dog tyson");
            System.out.println("Similarity Score: " + score);
            System.out.println(getBackupAPI());

            //System.out.println(apicaller.filterProfanity("Damn, Mike Tyson is a boxer and shit"));
        }
    }

