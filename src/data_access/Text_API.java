// Using the dandelion API to find similarity score between two users and upload it on a
// global dictionary on the database

package data_access;

import okhttp3.*;
import org.json.*;
import java.io.*;
import java.util.*;


public class Text_API {
    private static final String API_URL = "https://api.dandelion.eu/datatxt/sim/v1";
    private static final String API_TOKEN = "f001a9316b3b43fdac2fb8e88d9898ff";

    public static String getApiToken(){
        return API_TOKEN;
    }
    public static String getApiURL(){
        return API_URL;
    }

    public Float getScore(String text1, String text2) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://grade-logging-api.chenpan.ca/grade?course=%s&utorid=%s", course, utorid))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                JSONObject grade = responseBody.getJSONObject("grade");
                return Grade.builder()
                        .utorid(grade.getString("utorid"))
                        .course(grade.getString("course"))
                        .grade(grade.getInt("grade"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
//package api;
//
//import entity.Grade;
//import entity.Team;
//import okhttp3.*;
//        import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//public class MongoGradeDB implements GradeDB {
//    private static final String API_URL = "https://grade-logging-api.chenpan.ca/api/grade";
//    // load API_TOKEN from env variable.
//    private static final String API_TOKEN = System.getenv("API_TOKEN");
//
//    public static String getApiToken() {
//        return API_TOKEN;
//    }
//
//    @Override
//    public Grade getGrade(String utorid, String course) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(String.format("https://grade-logging-api.chenpan.ca/grade?course=%s&utorid=%s", course, utorid))
//                .addHeader("Authorization", API_TOKEN)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt("status_code") == 200) {
//                JSONObject grade = responseBody.getJSONObject("grade");
//                return Grade.builder()
//                        .utorid(grade.getString("utorid"))
//                        .course(grade.getString("course"))
//                        .grade(grade.getInt("grade"))
//                        .build();
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
//        } catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//    @Override
//    public Grade logGrade(String course, int grade) throws JSONException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("course", course);
//        requestBody.put("grade", grade);
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        Request request = new Request.Builder()
//                .url("https://grade-logging-api.chenpan.ca/grade")
//                .method("POST", body)
//                .addHeader("Authorization", API_TOKEN)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt("status_code") == 200) {
//                return null;
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
//        }
//        catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Team formTeam(String name) throws JSONException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("name", name);
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        Request request = new Request.Builder()
//                .url("https://grade-logging-api.chenpan.ca/team")
//                .method("POST", body)
//                .addHeader("Authorization", API_TOKEN)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt("status_code") == 200) {
//                JSONObject team = responseBody.getJSONObject("team");
//                JSONArray membersArray = team.getJSONArray("members");
//                String[] members = new String[membersArray.length()];
//                for (int i = 0; i < membersArray.length(); i++) {
//                    members[i] = membersArray.getString(i);
//                }
//
//                return Team.builder()
//                        .name(team.getString("name"))
//                        .members(members)
//                        .build();
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
//        }
//        catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    public Team joinTeam(String name) throws JSONException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("name", name);
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        Request request = new Request.Builder()
//                .url("https://grade-logging-api.chenpan.ca/team")
//                .method("PUT", body)
//                .addHeader("Authorization", API_TOKEN)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt("status_code") == 200) {
//                return null;
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
//        }
//        catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void leaveTeam() throws JSONException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        Request request = new Request.Builder()
//                .url("https://grade-logging-api.chenpan.ca/leaveTeam")
//                .method("PUT", body)
//                .addHeader("Authorization", API_TOKEN)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt("status_code") == 200) {
//                return;
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
//        }
//        catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    // TODO: Implement this method
//    //       Hint: Read apiDocuments/getMyTeam.md and refer to the above
//    //             methods to help you write this code (copy-and-paste + edit as needed).
//    public Team getMyTeam() {
//        return null;
//    }
//}