package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.taylorstubbs.nerdgolf.nerdgolf.R;

/**
 * Created by taylorstubbs on 2/23/17.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
    private static final String TAG = "SingleFragmentActivity";
    protected abstract Fragment createFragment();

    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);
        setContentView(getLayoutResId());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
