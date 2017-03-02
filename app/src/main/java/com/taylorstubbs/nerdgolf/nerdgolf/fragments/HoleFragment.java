package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;
import com.taylorstubbs.nerdgolf.nerdgolf.widgets.BiggerNumberPicker;

/**
 * Individual hole. User can set score, par, and navigate back and forth through the holes. User can
 * also finish the game.
 */

public class HoleFragment extends Fragment {
    private static final String TAG = "HoleFragment";
    private static final String ARG_HOLE_ID = "holeId";
    private static final String STATE_SCORE = "score";
    private static final String STATE_PAR = "par";

    private HoleFragmentCallbacks mCallbacks;
    private Hole mHole;
    private Game mGame;

    private TextView mHoleNumberView;
    private TextView mParView;
    private TextView mScoreView;
    private ImageButton mNextHoleButton;
    private ImageButton mPrevHoleButton;
    private Button mFinishGameButton;
    private ImageButton mIncreaseParButton;
    private ImageButton mDecreaseParButton;
    private ImageButton mIncreaseScoreButton;
    private ImageButton mDecreaseScoreButton;

    /**
     * Create new instance of fragment.
     *
     * @param hole  the hole to display
     * @return the fragment
     */
    public static HoleFragment newInstance(Hole hole) {
        HoleFragment holeFragment = new HoleFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_HOLE_ID, hole.getId());
        holeFragment.setArguments(args);
        return holeFragment;
    }

    /**
     * Interface for HoleFragment to communicate with GameActivity.
     */
    public interface HoleFragmentCallbacks {
        /**
         * Finish the game.
         */
        void finishGame();

        /**
         * Go to hole.
         *
         * @param holeNum   the hole number
         */
        void goToHole(int holeNum);
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        mHole = SQLUtil.getHoleFromId(getArguments().getLong(ARG_HOLE_ID));
        mGame = SQLUtil.getGameFromId(mHole.getGame());

        if (saveState != null) {
            mHole.setScore(saveState.getInt(STATE_SCORE));
            mHole.setPar(saveState.getInt(STATE_PAR));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (HoleFragmentCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_hole, container, false);
        String score = "#" + String.valueOf(mHole.getHoleNum() + 1);

        mHoleNumberView = (TextView) view.findViewById(R.id.hole);
        mParView = (TextView) view.findViewById(R.id.par);
        mScoreView = (TextView) view.findViewById(R.id.score);
        mNextHoleButton = (ImageButton) view.findViewById(R.id.next_hole_button);
        mPrevHoleButton = (ImageButton) view.findViewById(R.id.prev_hole_button);
        mFinishGameButton = (Button) view.findViewById(R.id.finish_game_button);
        mIncreaseScoreButton = (ImageButton) view.findViewById(R.id.increase_score_button);
        mDecreaseScoreButton = (ImageButton) view.findViewById(R.id.decrease_score_button);
        mIncreaseParButton = (ImageButton) view.findViewById(R.id.increase_par_button);
        mDecreaseParButton = (ImageButton) view.findViewById(R.id.decrease_par_button);

        mNextHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHole.getHoleNum() < mGame.getTotalHoleNumber() - 1) {
                    mHole.save();
                    mCallbacks.goToHole(mHole.getHoleNum() + 1);
                } else {
                    //TODO
                }

            }
        });

        mPrevHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHole.getHoleNum() > 0) {
                    mHole.save();
                    mCallbacks.goToHole(mHole.getHoleNum() - 1);
                }
            }
        });

        mFinishGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("Are you sure you want to finish the game?")
                        .setCancelable(true)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHole.save();
                                mCallbacks.finishGame();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();

            }
        });

        mIncreaseScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHole.increaseScore();
                updateHoleInfo();
            }
        });

        mDecreaseScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHole.getScore() > 0) {
                    mHole.decreaseScore();
                    updateHoleInfo();
                }
            }
        });

        mIncreaseParButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHole.increasePar();
                updateHoleInfo();
            }
        });

        mDecreaseParButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHole.getPar() > 1) {
                    mHole.decreasePar();
                    updateHoleInfo();
                }
            }
        });

        mHoleNumberView.setText(score);

        updateHoleInfo();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        saveState.putInt(STATE_SCORE, mHole.getScore());
        saveState.putInt(STATE_PAR, mHole.getPar());
    }

    @Override
    public void onPause() {
        mHole.save();

        super.onPause();
    }

    /**
     * Update the score and par.
     */
    private void updateHoleInfo() {
        mScoreView.setText(String.valueOf(mHole.getScore()));
        mParView.setText(String.valueOf(mHole.getPar()));
    }
}
