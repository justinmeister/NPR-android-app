package com.collywobble.justin.nprarticleretriever;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HeadlineActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new HeadlineListFragment();
    }

}
