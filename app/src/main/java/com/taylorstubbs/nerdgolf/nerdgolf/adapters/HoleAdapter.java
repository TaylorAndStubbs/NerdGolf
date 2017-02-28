package com.taylorstubbs.nerdgolf.nerdgolf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.holders.HoleHolder;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;

import java.util.List;

/**
 * Adapter for the holes.
 */

public class HoleAdapter extends RecyclerView.Adapter<HoleHolder> {
    private static final String TAG = "HoleAdapter";

    private List<Hole> mHoles;
    private Context mContext;

    /**
     * Constructor.
     *
     * @param context   the context of the activity the adapter is in
     * @param holes     the list of holes to adapt
     */
    public HoleAdapter(Context context, List<Hole> holes) {
        mContext = context;
        mHoles = holes;
    }

    @Override
    public HoleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_hole, parent, false);

        return new HoleHolder(view);
    }

    @Override
    public void onBindViewHolder(HoleHolder holder, int position) {
        Hole hole = mHoles.get(position);
        holder.bindHole(hole);
    }

    @Override
    public int getItemCount() {
        return mHoles.size();
    }
}
