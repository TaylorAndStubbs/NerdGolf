package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.nerdgolf.nerdgolf.R;

/**
 * Created by taylorstubbs on 2/27/17.
 */

public class EmptyRecordsFragment extends Fragment {
    private static final String TAG = "EmptyRecordsFragment";

    public static EmptyRecordsFragment newInstance() {
        return new EmptyRecordsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_records_empty, container, false);

        return view;
    }
}
