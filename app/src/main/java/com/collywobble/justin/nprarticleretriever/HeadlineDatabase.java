package com.collywobble.justin.nprarticleretriever;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HeadlineDatabase {
    private String mRawStringData;
    private ArrayList<String> mHeadlineList;
    private static HeadlineDatabase sHeadlines;
    private Context mAppContext;


    public String getRawStringData() {
        return mRawStringData;
    }

    public void setRawStringData(String rawStringData) {
        this.mRawStringData = rawStringData;
    }


    public HeadlineDatabase(Context appContext) {
        mAppContext = appContext;
    }

    private ArrayList<String> makeHeadlines() {
        ArrayList<String> newList = new ArrayList<String>();
        newList.add("Monkeys escape from the Metropolitan Zoo.");
        newList.add("Barack Obama signs historic bill.");
        newList.add("Same-sex marriage declared legal in Mississippi.");

        return newList;
    }


    public ArrayList<String> getHeadlineList() {
        return mHeadlineList;
    }

    public String getHeadline(int pos) {
        return mHeadlineList.get(pos);
    }


    public ArrayList<String> getHeadlineStringArray() {
        ArrayList<String> headlines = new ArrayList<String>();
        try {
            JSONArray storyJsonArray = new JSONObject(mRawStringData)
                    .getJSONObject("list")
                    .getJSONArray("story");
            for (int i = 0; i < storyJsonArray.length(); i++) {
                try {
                    JSONObject newStory = storyJsonArray.getJSONObject(i).getJSONObject("title");
                    headlines.add(newStory.getString("$text"));
                } catch (JSONException je) {}

            }
        } catch(JSONException je) {}

        return headlines;
    }

}
