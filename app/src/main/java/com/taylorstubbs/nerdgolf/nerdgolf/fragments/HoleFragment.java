package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

/**
 * Individual hole. User can set score, par, and navigate back and forth through the holes. User can
 * also finish the game.
 */

public class HoleFragment extends Fragment {
    private static final String TAG = "HoleFragment";
    private static final String ARG_HOLE_ID = "holeId";

    private HoleFragmentCallbacks mCallbacks;
    private Hole mHole;

    private TextView mHoleNumberView;
    private TextView mParView;
    private TextView mScoreView;

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
         * Go to next hole.
         *
         * @param holeNum   the hole number
         */
        void nextHole(int holeNum);

        /**
         * Go to previous hole.
         *
         * @param holeNum   the hole number
         */
        void prevHole(int holeNum);
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        mHole = SQLUtil.getHoleFromId(getArguments().getLong(ARG_HOLE_ID));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof HoleFragmentCallbacks)) {
            throw new IllegalStateException("Activity must implement HoleFragmentCallbacks");
        }

        mCallbacks = (HoleFragmentCallbacks) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_hole, container, false);

        mHoleNumberView = (TextView) view.findViewById(R.id.hole);
        mParView = (TextView) view.findViewById(R.id.par);
        mScoreView = (TextView) view.findViewById(R.id.score);

        mHoleNumberView.setText(String.valueOf(mHole.getHoleNum()));

        return view;
    }
}
