package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.support.v4.app.Fragment;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.RecordFragment;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class RecordsActivity extends SingleFragmentActivity {
    private static final String TAG = "RecordsActivity";

    @Override
    protected Fragment createFragment() {
        //TEMP
        return RecordFragment.newInstance(SQLUtil.getAllGames().get(0));
    }
}
