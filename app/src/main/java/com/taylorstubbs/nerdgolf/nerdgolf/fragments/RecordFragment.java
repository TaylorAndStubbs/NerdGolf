package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.adapters.HoleAdapter;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

import java.util.List;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class RecordFragment extends Fragment {
    private static final String TAG = "RecordFragment";
    private static final String ARGS_GAME_ID = "gameId";

    private Game mGame;
    private List<Hole> mHoles;
    private int mTotalPar = 0;
    private int mTotalScore = 0;
    private RecordFragmentCallbacks mCallbacks;

    private Button mDeleteButton;
    private Button mNextGameButton;
    private Button mPrevGameButton;
    private TextView mDateView;
    private TextView mCourseNameView;
    private TextView mTotalScoreView;
    private TextView mTotalParView;
    private RecyclerView mHoleListView;

    public static RecordFragment newInstance(Game game) {
        Bundle args = new Bundle();
        RecordFragment recordFragment = new RecordFragment();
        args.putLong(ARGS_GAME_ID, game.getId());
        recordFragment.setArguments(args);

        return recordFragment;
    }

    public interface RecordFragmentCallbacks {
        void nextGame(Game game);
        void prevGame(Game game);
        void deleteGame(Game game);
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);
        mGame = SQLUtil.getGameFromId(getArguments().getLong(ARGS_GAME_ID));
        mHoles = mGame.getHoles();

        calculateTotal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        mNextGameButton = (Button) view.findViewById(R.id.next_game);
        mPrevGameButton = (Button) view.findViewById(R.id.prev_game);
        mDeleteButton = (Button) view.findViewById(R.id.delete);
        mDateView = (TextView) view.findViewById(R.id.date);
        mCourseNameView = (TextView) view.findViewById(R.id.course_name);
        mTotalScoreView = (TextView) view.findViewById(R.id.total_score);
        mTotalParView = (TextView) view.findViewById(R.id.total_par);
        mHoleListView = (RecyclerView) view.findViewById(R.id.hole_list);

        mHoleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHoleListView.setAdapter(new HoleAdapter(getContext(), mHoles));

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.deleteGame(mGame);
            }
        });

        mNextGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.nextGame(mGame);
            }
        });

        mPrevGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.prevGame(mGame);
            }
        });

        mDateView.setText(mGame.getDate());
        mCourseNameView.setText(mGame.getCourseName());
        mTotalScoreView.setText(String.valueOf(mTotalScore));
        mTotalParView.setText(String.valueOf(mTotalPar));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (RecordFragmentCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void calculateTotal() {
        for (int i = 0; i < mHoles.size(); i++) {
            Hole hole = mHoles.get(i);
            mTotalScore += hole.getScore();
            mTotalPar += hole.getPar();
        }
    }

    private List<Game> getGames() {
        return Select.from(Game.class).list();
    }
}
