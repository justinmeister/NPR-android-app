package com.collywobble.justin.nprarticleretriever;


import android.content.Context;

import java.util.ArrayList;

public class HeadlineDatabase {
    private ArrayList<String> mHeadlineList;

    private static HeadlineDatabase sHeadlines;
    private Context mAppContext;

    private HeadlineDatabase(Context appContext) {
        mAppContext = appContext;
        mHeadlineList = makeHeadlines();

    }

    private ArrayList<String> makeHeadlines() {
        ArrayList<String> newList = new ArrayList<String>();
        newList.add("Monkeys escape from the Metropolitan Zoo.");
        newList.add("Barack Obama signs historic bill.");
        newList.add("Same-sex marriage declared legal in Mississippi.");

        return newList;
    }

    public static HeadlineDatabase get(Context c) {
        if (sHeadlines == null) {
            sHeadlines = new HeadlineDatabase(c.getApplicationContext());
        }
        return sHeadlines;
    }

    public ArrayList<String> getHeadlineList() {
        return mHeadlineList;
    }

    public String getHeadline(int pos) {
        return mHeadlineList.get(pos);

    }

}
