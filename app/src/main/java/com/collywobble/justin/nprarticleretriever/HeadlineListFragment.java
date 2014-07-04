package com.collywobble.justin.nprarticleretriever;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class HeadlineListFragment extends ListFragment {
    private String mNprUrl = "http://api.npr.org/query?apiKey=";
    private String key = "MDE1MTExMTM3MDE0MDQ0OTEwMDQyNzAyYQ001";
    private ArrayList<String> mHeadlines;
    private ArrayAdapter<String> mAdapter;
    private static final String TAG = "HeadlineListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.headline_title);
        mHeadlines = HeadlineDatabase.get(getActivity()).getHeadlineList();
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

                String result = new NprRequest().getUrl("http://www.google.com");
                Log.i(TAG, "Fetched contents of URL: " + result);
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return null;
        }
    }
}
