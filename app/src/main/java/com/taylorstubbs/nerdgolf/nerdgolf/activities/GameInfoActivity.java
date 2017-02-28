package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.support.v4.app.Fragment;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.GameInfoFragment;

/**
 * Host the GameInfoFragment.
 */

public class GameInfoActivity extends SingleFragmentActivity {
    private static final String TAG = "GameInfoActivity";


    @Override
    protected Fragment createFragment() {
        return GameInfoFragment.newInstance();
    }
}
