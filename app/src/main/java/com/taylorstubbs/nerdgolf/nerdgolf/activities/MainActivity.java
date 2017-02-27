package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.MainFragment;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class MainActivity extends SingleFragmentActivity {
    private static final String TAG = "MainActivity";

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }
}
