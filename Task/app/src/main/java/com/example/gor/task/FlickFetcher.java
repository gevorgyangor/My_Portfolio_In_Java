package com.example.gor.task;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GOR on 28.09.2018.
 */

public class FlickFetcher {
    private static final String TAG = "FlickFetcher";
    private static final String API_KEY = "75fa614c6d2b5e3fd2e9bf62d8b8fe4a";

    public String getJSONString(String urlSpec) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlSpec)
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
        return result;
    }

    public List<PhotoItem> fatchItem() {
        List<PhotoItem> photoItems = new ArrayList<>();
        try {
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            String jsonString = getJSONString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(photoItems, jsonBody );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photoItems;
    }

    private void parseItems(List<PhotoItem> items, JSONObject jsonObject) throws IOException, JSONException {
        JSONObject photoJsonObject = jsonObject.getJSONObject("photos");
        JSONArray photoJsonArry = photoJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArry.length(); i++) {
            JSONObject photoJson = photoJsonArry.getJSONObject(i);
            PhotoItem item = new PhotoItem();
            item.setId(photoJson.getString("id"));
            item.setTitle(photoJson.getString("title"));


            if (!photoJson.has("url_s")) {
                continue;
            }
            item.setUrl(photoJson.getString("url_s"));
            items.add(item);
        }
    }
}
