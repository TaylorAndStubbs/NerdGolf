package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.fragments.HoleFragment;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

import java.util.List;

/**
 * This class hosts a HoleFragment and acts as the controller for the game. It contains an interface
 * that the HoleFragment uses to communicate with the GameActivity.
 */

public class GameActivity extends SingleFragmentActivity implements HoleFragment.HoleFragmentCallbacks {
    private static final String TAG = "GameActivity";
    private static final String EXTRA_GAME_ID = "gameId";

    private Game mGame;
    private List<Hole> mHoles;

    /**
     * Create an intent from a Game
     *
     * @param context   the context
     * @param game      the Game
     * @return intent
     */
    public static Intent createIntent(Context context, Game game) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(EXTRA_GAME_ID, game.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        //Create HoleFragment with first hole
        return HoleFragment.newInstance(mHoles.get(0));
    }

    @Override
    public void finishGame() {
        //TODO
    }

    @Override
    public void goToHole(int holeNum) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Hole hole = mHoles.get(holeNum);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HoleFragment.newInstance(hole))
                .commit();
    }

    @Override
    public void onCreate(Bundle saveState) {
        Intent intent = getIntent();
        Long gameId = intent.getLongExtra(EXTRA_GAME_ID, 0);
        mGame = SQLUtil.getGameFromId(gameId);
        mHoles = mGame.getHoles();

        super.onCreate(saveState);
    }
}
