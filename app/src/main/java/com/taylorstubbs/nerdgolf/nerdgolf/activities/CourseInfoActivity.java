package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.support.v4.app.Fragment;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.CourseInfoFragment;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class CourseInfoActivity extends SingleFragmentActivity {
    private static final String TAG = "CourseInfoActivity";


    @Override
    protected Fragment createFragment() {
        return CourseInfoFragment.newInstance();
    }
}
