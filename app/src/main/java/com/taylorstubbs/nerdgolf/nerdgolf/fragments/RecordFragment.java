package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.adapters.HoleAdapter;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.DateUtil;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Fragment to display the information about a game.
 */

public class RecordFragment extends Fragment {
    private static final String TAG = "RecordFragment";
    private static final String ARGS_GAME_ID = "gameId";

    private Game mGame;
    private List<Hole> mHoles;
    private int mTotalPar = 0;
    private int mTotalScore = 0;
    private int mFinalScore;
    private RecordFragmentCallbacks mCallbacks;

    private Button mDeleteButton;
    private ImageButton mNextGameButton;
    private ImageButton mPrevGameButton;
    private TextView mDateView;
    private TextView mCourseNameView;
    private TextView mTotalScoreView;
    private TextView mTotalParView;
    private TextView mFinalScoreView;
    private RecyclerView mHoleListView;

    /**
     * Create new instance of fragment.
     *
     * @param game  the game to display the information of
     * @return the fragment
     */
    public static RecordFragment newInstance(Game game) {
        Bundle args = new Bundle();
        RecordFragment recordFragment = new RecordFragment();
        args.putLong(ARGS_GAME_ID, game.getId());
        recordFragment.setArguments(args);

        return recordFragment;
    }

    /**
     * Callback interface for RecordFragment.
     */
    public interface RecordFragmentCallbacks {
        /**
         * Go to the next game.
         *
         * @param game  the current game
         */
        void nextGame(Game game);

        /**
         * Go to the previous game.
         *
         * @param game  the current game
         */
        void prevGame(Game game);

        /**
         * Delete the current game.
         *
         * @param game the current game
         */
        void deleteGame(Game game);
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);
        mGame = SQLUtil.getGameFromId(getArguments().getLong(ARGS_GAME_ID));
        mHoles = mGame.getHoles();

        calculateTotals();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy @ hh:mm");

        mNextGameButton = (ImageButton) view.findViewById(R.id.next_game);
        mPrevGameButton = (ImageButton) view.findViewById(R.id.prev_game);
        mDeleteButton = (Button) view.findViewById(R.id.delete_game_button);
        mDateView = (TextView) view.findViewById(R.id.date);
        mCourseNameView = (TextView) view.findViewById(R.id.course_name);
        mTotalScoreView = (TextView) view.findViewById(R.id.total_score);
        mTotalParView = (TextView) view.findViewById(R.id.total_par);
        mHoleListView = (RecyclerView) view.findViewById(R.id.hole_list);
        mFinalScoreView = (TextView) view.findViewById(R.id.final_score);

        mHoleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHoleListView.setAdapter(new HoleAdapter(getContext(), mHoles));

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setCancelable(true)
                        .setMessage("Are you sure you want to delete this game?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCallbacks.deleteGame(mGame);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();

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

        mDateView.setText(simpleDateFormat.format(DateUtil.createDate(mGame.getDate())));
        mCourseNameView.setText(mGame.getCourseName());
        mTotalScoreView.setText(String.valueOf(mTotalScore));
        mTotalParView.setText(String.valueOf(mTotalPar));
        mFinalScoreView.setText(formatFinalScore(mFinalScore));

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

    /**
     * Calculate and set totals for score and par.
     */
    private void calculateTotals() {
        for (int i = 0; i < mHoles.size(); i++) {
            Hole hole = mHoles.get(i);
            mTotalScore += hole.getScore();
            mTotalPar += hole.getPar();

            mFinalScore = mTotalScore - mTotalPar;
        }
    }

    private String formatFinalScore(int finalScore) {
        String formattedFinalScore = String.valueOf(finalScore);
        if (finalScore > 0) {
            formattedFinalScore = "+" + formattedFinalScore;
        }

        return formattedFinalScore;
    }
}
