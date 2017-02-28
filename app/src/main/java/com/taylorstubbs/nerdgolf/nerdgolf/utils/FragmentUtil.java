package com.taylorstubbs.nerdgolf.nerdgolf.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.taylorstubbs.nerdgolf.nerdgolf.R;

/**
 * Utility for working with fragments.
 */

public enum FragmentUtil {;
    private static final String TAG = "FragmentUtil";

    /**
     * Replace a fragment with another fragment.
     *
     * @param fragmentActivity  the activity hosting the fragment
     * @param fragment          the fragment to replace existing fragment with
     */
    public static void replaceFragment(FragmentActivity fragmentActivity, Fragment fragment) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
