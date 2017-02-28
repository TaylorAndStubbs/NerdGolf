package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.nerdgolf.nerdgolf.R;

/**
 * Fragment to inform users they don't have any games.
 */

public class EmptyRecordsFragment extends Fragment {
    private static final String TAG = "EmptyRecordsFragment";

    /**
     * Create new instance of fragment.
     *
     * @return  the fragment
     */
    public static EmptyRecordsFragment newInstance() {
        return new EmptyRecordsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        return inflater.inflate(R.layout.fragment_records_empty, container, false);
    }
}
