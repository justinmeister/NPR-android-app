package com.collywobble.justin.nprarticleretriever;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
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
    private ArrayList<Headline> mHeadlines;
    private ArrayList<String> mIds;
    private static final String TAG = "HeadlineListFragment";
    private HeadlineDatabase mHeadlineDatabase = new HeadlineDatabase(getActivity());
    private static final String DATA = "data";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getActivity().setTitle(R.string.headline_title);
        new FetchItemsTask().execute();
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
        ArrayAdapter<Headline> adapter = new HeadlineAdapter(mHeadlines);
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
            mHeadlines = mHeadlineDatabase.getHeadlineObjects();
            setupAdapter();
        }
    }

    private class HeadlineAdapter extends ArrayAdapter<Headline> {
        public HeadlineAdapter(ArrayList<Headline> headlines) {
            super(getActivity(), 0, headlines);
        }
        @TargetApi(16)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_headline, null);
            }

            final Headline currentHeadline = getItem(position);

            TextView headlineTextView =
                    (TextView)convertView.findViewById(R.id.headline_text_view);
            headlineTextView.setText(currentHeadline.getHeadline());
            headlineTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), ArticleActivity.class);
                    i.putExtra(articleFragment.STORY_ID, currentHeadline.getStoryId());

                    if (Build.VERSION.SDK_INT >= 16) {
                        Bundle translateBundle =
                                ActivityOptions.makeCustomAnimation(getActivity(),
                                        R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
                        startActivity(i, translateBundle);
                    } else {
                        startActivity(i);
                    }
                }
            });

            return convertView;
        }
    }
}
