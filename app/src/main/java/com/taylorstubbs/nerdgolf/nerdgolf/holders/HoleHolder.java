package com.taylorstubbs.nerdgolf.nerdgolf.holders;

import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;

import org.w3c.dom.Text;

/**
 * ViewHolder for the hole.
 */

public class HoleHolder extends RecyclerView.ViewHolder {
    private TextView mHoleNumView;
    private TextView mParView;
    private TextView mScoreView;

    public HoleHolder(View view) {
        super(view);

        mHoleNumView = (TextView) view.findViewById(R.id.hole_num);
        mParView = (TextView) view.findViewById(R.id.par);
        mScoreView = (TextView) view.findViewById(R.id.score);
    }

    public void bindHole(Hole hole) {
        mHoleNumView.setText(String.valueOf(hole.getHoleNum()));
        mParView.setText((String.valueOf(hole.getPar())));
        mScoreView.setText(String.valueOf(hole.getScore()));
    }
}
