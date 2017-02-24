package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.nerdgolf.nerdgolf.R;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class RecordsFragment extends Fragment {
    private static final String TAG = "RecordsFragment";

    public static RecordsFragment newInstance() {
        return new RecordsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_records, container, false);

        return view;
    }
}
