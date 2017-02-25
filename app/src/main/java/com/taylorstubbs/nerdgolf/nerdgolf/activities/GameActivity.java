package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.support.v4.app.Fragment;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.GameFragment;

/**
 * Created by taylorstubbs on 2/25/17.
 */

public class GameActivity extends SingleFragmentActivity {
    private static final String TAG = "GameActivity";

    @Override
    protected Fragment createFragment() {
        return GameFragment.newInstance();
    }
}
