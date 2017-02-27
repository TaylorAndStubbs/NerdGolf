package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;

import java.util.List;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class RecordsFragment extends Fragment {
    private static final String TAG = "RecordsFragment";

    private TextView mRecordsView;
    private Button mDeleteRecordsButton;

    public static RecordsFragment newInstance() {
        return new RecordsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_records, container, false);

        mDeleteRecordsButton = (Button) view.findViewById(R.id.delete_records);
        mDeleteRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.deleteAll(Game.class);
            }
        });

        mRecordsView = (TextView) view.findViewById(R.id.records);
        mRecordsView.setText(String.valueOf(getGames().get(0).getInProgress()));

        return view;
    }

    private List<Game> getGames() {
        return Select.from(Game.class).list();
    }
}
