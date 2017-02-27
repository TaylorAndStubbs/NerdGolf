package com.taylorstubbs.nerdgolf.nerdgolf.utils;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;

import java.util.List;

/**
 * Util for working with the SQLite database.
 */

public enum SQLUtil {;

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
}
