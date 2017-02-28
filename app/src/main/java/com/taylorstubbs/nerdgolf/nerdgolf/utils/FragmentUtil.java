package com.taylorstubbs.nerdgolf.nerdgolf.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.taylorstubbs.nerdgolf.nerdgolf.R;

/**
 * Created by taylorstubbs on 2/27/17.
 */

public enum FragmentUtil {;
    private static final String TAG = "FragmentUtil";

    public static void replaceFragment(FragmentActivity fragmentActivity, Fragment fragment) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
