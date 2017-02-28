package com.taylorstubbs.nerdgolf.nerdgolf.utils;

import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Util for working with the SQLite database.
 *
 * If column is named exampleThing you need to query it with example_Thing
 * false = 0
 * true = 1
 */

public enum SQLUtil {;
    private static final String TAG = "SQLUtil";

    /**
     * Get an object from the database based on its id.
     *
     * @param c     the class of object
     * @param id    the object's id
     * @return the object
     */
    public static Object getObjectFromId(Class c, Long id) {
        return Select.from(c).where(Condition.prop("id").eq(id)).list().get(0);
    }

    /**
     * Game a game from the database based on its id.
     *
     * @param id    the game's id
     * @return the game
     */
    public static Game getGameFromId(Long id) {
        return (Game) getObjectFromId(Game.class, id);
    }

    /**
     * Get a hole from the database based on its id.
     *
     * @param id    the hole's id
     * @return the hole
     */
    public static Hole getHoleFromId(Long id) {
        return (Hole) getObjectFromId(Hole.class, id);
    }

    /**
     * Get all holes from a game based on its id
     *
     * @param id    the game's id
     * @return  the holes
     */
    public static List<Hole> getAllHolesFromGame(Long id) {
        return Select.from(Hole.class).where(Condition.prop("game").eq(id)).list();
    }

    public static Game getGameInProgress() {
        try {
            return Select.from(Game.class).where(Condition.prop("in_Progress")
                    .eq(1)).list().get(0);
        } catch (IndexOutOfBoundsException | SQLiteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Game> getAllGames() {
        List<Game> games = Select.from(Game.class).orderBy("id").list();
        Collections.reverse(games);

        return games;
    }
}
