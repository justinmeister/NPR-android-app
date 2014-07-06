package com.collywobble.justin.nprarticleretriever;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class HeadlineListFragment extends ListFragment {
    private String mNprUrl = "http://api.npr.org/query?apiKey=";
    private String mKey = "MDE1MTExMTM3MDE0MDQ0OTEwMDQyNzAyYQ001";
    private String mNumOfResults = "&numResults=10";
    private String mFormat = "&format=json";
    private String mTopicId = "&id=1007";
    private String mRequestUrl = mNprUrl + mKey + mNumOfResults + mFormat + mTopicId;
    private ArrayList<String> mHeadlines;
    private ArrayAdapter<String> mAdapter;
    private static final String TAG = "HeadlineListFragment";
    private ArrayList<String> mHeadlineStrings;
    private HeadlineDatabase mHeadlineDatabase = new HeadlineDatabase(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.headline_title);
        mHeadlines = mHeadlineDatabase.getHeadlineList();
        mAdapter = new ArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_list_item_1,
                                            mHeadlines);
        setListAdapter(mAdapter);
        new FetchItemsTask().execute();
    }


    private class FetchItemsTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String result = new NprRequest().getUrl(mRequestUrl);
                mHeadlineDatabase.setRawStringData(result);

            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return null;
        }
    }
}
