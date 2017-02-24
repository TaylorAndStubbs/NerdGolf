package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.support.v4.app.Fragment;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.GameInfoFragment;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class GameInfoActivity extends SingleFragmentActivity {
    private static final String TAG = "GameInfoActivity";


    @Override
    protected Fragment createFragment() {
        return GameInfoFragment.newInstance();
    }
}
