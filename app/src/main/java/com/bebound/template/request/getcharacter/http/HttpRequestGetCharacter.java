package com.bebound.template.request.getcharacter.http;

import com.bebound.template.MainActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Locale;

public class HttpRequestGetCharacter {

    private final static String TAG = HttpRequestGetCharacter.class.getCanonicalName();
    private static String output = "";


    public static void send(MainActivity activity, int id){
        output = "ID " + id + " is ";
        OkHttpClient client = new OkHttpClient();
        String url = String.format(Locale.ENGLISH, "https://swapi.co/api/people/%d/", id);
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String json = response.body().string();
                    JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
                    String charName = obj.getAsJsonPrimitive("name").getAsString();
                    String homeWorldUrl = obj.getAsJsonPrimitive("homeworld").getAsString();
                    output = output + charName;
                    Request request = new Request.Builder()
                            .url(homeWorldUrl)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String json = response.body().string();
                            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
                            String planetName = obj.getAsJsonPrimitive("name").getAsString();
                            output = output + ", coming from " + planetName;
                            activity.showToast(output);
                        }
                    });
                }
            }
        });
    }

}