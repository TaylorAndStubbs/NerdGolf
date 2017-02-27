package com.taylorstubbs.nerdgolf.nerdgolf.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.fragments.HoleFragment;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by taylorstubbs on 2/25/17.
 */

public class GameActivity extends SingleFragmentActivity {
    private static final String TAG = "GameActivity";
    private static final String EXTRA_GAME_ID = "gameId";

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

    /**
     * Interface for HoleFragment to communicate with GameActivity.
     */
    public interface GameInterface {
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
    protected Fragment createFragment() {
        Intent intent = getIntent();
        Long gameId = intent.getLongExtra(EXTRA_GAME_ID, 0);

        //Only one game will have this date so get the first result that comes back
        Game game = Select.from(Game.class).where(Condition.prop("id").eq(gameId)).list().get(0);

        //Create HoleFragment with first hole
        return HoleFragment.newInstance(game.getHoles().get(0));
    }
}
