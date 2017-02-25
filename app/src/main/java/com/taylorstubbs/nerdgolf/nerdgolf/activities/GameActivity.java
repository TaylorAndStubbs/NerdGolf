package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.GameFragment;

import java.util.Date;

/**
 * Created by taylorstubbs on 2/25/17.
 */

public class GameActivity extends SingleFragmentActivity {
    private static final String TAG = "GameActivity";
    private static final String EXTRA_DATE = "date";

    /**
     * Create Intent for GameActivity.
     *
     * @param date the date of the game
     * @return the Intent
     */
    public static Intent createIntent(Context context, Date date) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(EXTRA_DATE, date.toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return GameFragment.newInstance();
    }
}
