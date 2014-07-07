package com.collywobble.justin.nprarticleretriever;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HeadlineDatabase {
    private String mRawStringData;
    private Context mAppContext;
    private ArrayList<Headline> mHeadlineObjects;

    public String getRawStringData() {
        return mRawStringData;
    }

    public void setRawStringData(String rawStringData) {
        this.mRawStringData = rawStringData;
        this.mHeadlineObjects = getHeadlineArray();
    }

    public ArrayList<Headline> getHeadlineObjects() {
        return mHeadlineObjects;
    }

    public HeadlineDatabase(Context appContext) {
        mAppContext = appContext;
    }

    public ArrayList<String> getHeadlineStrings() {
        ArrayList<String> headlines = new ArrayList<String>();

        for (Headline newHeadline : mHeadlineObjects) {
            headlines.add(newHeadline.getHeadline());
        }
        return headlines;
    }

    public ArrayList<String> getIds() {
        ArrayList<String> ids = new ArrayList<String>();

        for (Headline newHeadline : mHeadlineObjects) {
            ids.add(newHeadline.getStoryId());
        }
        return ids;
    }


    public ArrayList<Headline> getHeadlineArray() {
        String headlineString;
        String id;
        ArrayList<Headline> headlines = new ArrayList<Headline>();
        try {
            JSONArray storyJsonArray = new JSONObject(mRawStringData)
                    .getJSONObject("list")
                    .getJSONArray("story");
            for (int i = 0; i < storyJsonArray.length(); i++) {
                try {
                    headlineString = storyJsonArray
                            .getJSONObject(i)
                            .getJSONObject("title")
                            .getString("$text");

                    id = storyJsonArray
                            .getJSONObject(i)
                            .getString("id");

                    Headline newHeadline = new Headline(headlineString, id);
                    headlines.add(newHeadline);

                } catch (JSONException je) {}
            }
        } catch (JSONException je) {}

        return headlines;
    }

}
