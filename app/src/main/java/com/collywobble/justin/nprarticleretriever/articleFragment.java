package com.collywobble.justin.nprarticleretriever;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class articleFragment extends Fragment {

    public static final String STORY_ID = "com.collywobble.justin.nprarticleretriever.ID";
    public static final String TAG = "Article Fragment";
    private String mId;
    private String mKey = "MDE1MTExMTM3MDE0MDQ0OTEwMDQyNzAyYQ001";
    private String mRequestUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getActivity().setTitle("");
        mId = getActivity().getIntent()
                .getStringExtra(STORY_ID);
        mRequestUrl = makeRequestURL();
    }

    private String makeRequestURL() {
        String nprUrl = "http://api.npr.org/query?apiKey=";
        String key = "MDE1MTExMTM3MDE0MDQ0OTEwMDQyNzAyYQ001";

        return nprUrl + key + "&id=" + mId;
    }


    private class FetchItemsTask extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                return new NprRequest().getUrl(mRequestUrl);

            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String rawStringData) {
            //Pass
        }
    }

 }
