package com.collywobble.justin.nprarticleretriever;

public class Headline {
    String mHeadline;
    String mStoryId;


    public Headline (String headline, String id) {
        mHeadline = headline;
        mStoryId = id;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String mHeadline) {
        this.mHeadline = mHeadline;
    }

    public String getStoryId() {
        return mStoryId;
    }

    public void setStoryId(String mStoryId) {
        this.mStoryId = mStoryId;
    }

}
