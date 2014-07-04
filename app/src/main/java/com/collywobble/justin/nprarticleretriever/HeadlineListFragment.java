package com.collywobble.justin.nprarticleretriever;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class HeadlineListFragment extends ListFragment {
    private ArrayList<String> mHeadlines;
    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.headline_title);
        mHeadlines = HeadlineDatabase.get(getActivity()).getHeadlineList();
        mAdapter = new ArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_list_item_1,
                                            mHeadlines);
        setListAdapter(mAdapter);


    }
}
