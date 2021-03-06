package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.activities.GameActivity;
import com.taylorstubbs.nerdgolf.nerdgolf.activities.GameInfoActivity;
import com.taylorstubbs.nerdgolf.nerdgolf.activities.MainActivity;
import com.taylorstubbs.nerdgolf.nerdgolf.activities.RecordsActivity;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

/**
 * The opening menu. Users can start a new game or check their records.
 */

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private Button mNewGameButton;
    private Button mRecordsbutton;
    private LinearLayout mPortfolioLink;

    /**
     * Create new instance of fragment.
     *
     * @return the fragment
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        Game game = SQLUtil.getGameInProgress();
        if (game != null) {
            startActivity(GameActivity.createIntent(getContext(), game));
        } else {
            //TODO
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mNewGameButton = (Button) view.findViewById(R.id.new_game_button);
        mRecordsbutton = (Button) view.findViewById(R.id.records_button);
        mPortfolioLink = (LinearLayout) view.findViewById(R.id.portfolio_link);

        mPortfolioLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taylorstubbs.com")));
            }
        });

        mRecordsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recordsActivityIntent = new Intent(getActivity(), RecordsActivity.class);
                getActivity().startActivity(recordsActivityIntent);
            }
        });

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
