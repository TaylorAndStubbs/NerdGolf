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

public class GameInfoFragment extends Fragment {
    private static final String TAG = "GameInfoFragment";

    public static GameInfoFragment newInstance() {
        return new GameInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_info_game, container, false);

        return view;
    }
}
