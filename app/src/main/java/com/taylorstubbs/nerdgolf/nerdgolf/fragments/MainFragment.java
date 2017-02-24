package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.activities.GameInfoActivity;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private Button mNewGameButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mNewGameButton = (Button) view.findViewById(R.id.new_game_button);

        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent courseInfoActivityIntent = new Intent(getActivity(), GameInfoActivity.class);
                getActivity().startActivity(courseInfoActivityIntent);
            }
        });

        return view;
    }
}
