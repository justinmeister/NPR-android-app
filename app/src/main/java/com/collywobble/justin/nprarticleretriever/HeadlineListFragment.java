package com.collywobble.justin.nprarticleretriever;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class HeadlineListFragment extends ListFragment {

    private String mRequestUrl = makeRequestUrl(20, 1007);
    private ArrayList<String> mHeadlines = new ArrayList<String>();
    private static final String TAG = "HeadlineListFragment";
    private HeadlineDatabase mHeadlineDatabase = new HeadlineDatabase(getActivity());
    private static final String DATA = "data";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.headline_title);
        if (savedInstanceState == null) {
            new FetchItemsTask().execute();
        } else {
            mHeadlines = savedInstanceState.getStringArrayList(DATA);
            setupAdapter();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (mHeadlines != null) {
            savedInstanceState.putStringArrayList(DATA, mHeadlines);
        }
    }

    private String makeRequestUrl(int numOfItems, int topicIdint) {
        String nprUrl = "http://api.npr.org/query?apiKey=";
        String key = "MDE1MTExMTM3MDE0MDQ0OTEwMDQyNzAyYQ001";
        String numOfResults = "&numResults=" + numOfItems;
        String format = "&format=json";
        String topicId = "&id=" + topicIdint;

        return nprUrl + key + numOfResults + format + topicId;
    }

    private void setupAdapter() {
        ArrayAdapter<String> adapter = new HeadlineAdapter(mHeadlines);
        setListAdapter(adapter);
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
            mHeadlineDatabase.setRawStringData(rawStringData);
            mHeadlines = mHeadlineDatabase.getHeadlineStringArray();
            setupAdapter();
        }
    }

    private class HeadlineAdapter extends ArrayAdapter<String> {
        public HeadlineAdapter(ArrayList<String> headlines) {
            super(getActivity(), 0, headlines);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_headline, null);
            }

            String currentHeadline = getItem(position);

            TextView headlineTextView =
                    (TextView)convertView.findViewById(R.id.headline_text_view);
            headlineTextView.setText(currentHeadline);

            return convertView;
        }
    }
}
