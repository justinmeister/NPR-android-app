package com.collywobble.justin.nprarticleretriever;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.collywobble.justin.nprarticleretriever.R;

public class ArticleActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new articleFragment();}
}
