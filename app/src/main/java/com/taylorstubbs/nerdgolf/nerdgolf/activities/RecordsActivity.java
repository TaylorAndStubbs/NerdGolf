package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.taylorstubbs.nerdgolf.nerdgolf.fragments.EmptyRecordsFragment;
import com.taylorstubbs.nerdgolf.nerdgolf.fragments.RecordFragment;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.FragmentUtil;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An activity that controls displaying RecordFragments.
 */

public class RecordsActivity extends SingleFragmentActivity implements RecordFragment.RecordFragmentCallbacks {
    private static final String TAG = "RecordsActivity";

    List<Game> mGames;

    @Override
    public void onCreate(Bundle saveState) {
        mGames = SQLUtil.getAllGames();

        super.onCreate(saveState);
    }

    @Override
    protected Fragment createFragment() {
        if (mGames.size() == 0) {
            return EmptyRecordsFragment.newInstance();
        }

        return RecordFragment.newInstance(mGames.get(0));
    }

    @Override
    public void nextGame(Game game) {
        changeGame(game, 1);
    }

    @Override
    public void prevGame(Game game) {
        changeGame(game, -1);
    }

    @Override
    public void deleteGame(Game game) {
        if (mGames.size() - 1 == 0) {
            FragmentUtil.replaceFragment(this, EmptyRecordsFragment.newInstance());
        } else {
            if (isLastGameInList(game)) {
                changeGame(game, 1);
            } else {
                changeGame(game, -1);
            }
        }

        game.deleteHoles();
        game.delete();
        mGames = SQLUtil.getAllGames();
    }

    /**
     * Get the index of the game from the list of games
     *
     * @param id    the id of the game
     * @return the index
     */
    private int getGameIndexFromId(long id) {
        for (int i = 0; i < mGames.size(); i++) {
            Game game = mGames.get(i);
            if (game.getId() == id) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Determine if game is last in array of games.
     *
     * @param game  the game to check
     * @return whether or not the game is the last in the list
     */
    private boolean isLastGameInList(Game game) {
        return mGames.size() >= getGameIndexFromId(game.getId()) + 2;
    }

    /**
     * Change the game.
     *
     * @param delta number to change index by
     */
    private void changeGame(Game game, int delta) {
        try {
            FragmentUtil.replaceFragment(RecordsActivity.this,
                    RecordFragment.newInstance(mGames.get(getGameIndexFromId(game.getId()) + (delta))));
        } catch (IndexOutOfBoundsException e) {
            //Just easier to catch exception
        }
    }
}
