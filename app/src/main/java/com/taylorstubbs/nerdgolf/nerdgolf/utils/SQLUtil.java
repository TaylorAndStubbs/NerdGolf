package com.taylorstubbs.nerdgolf.nerdgolf.utils;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Hole;

/**
 * Created by taylorstubbs on 2/26/17.
 */

public enum SQLUtil {;
    public static Object getObjectFromId(Class c, Long id) {
        return Select.from(c).where(Condition.prop("id").eq(id)).list().get(0);
    }
    public static Game getGameFromId(Long id) {
        return (Game) getObjectFromId(Game.class, id);
    }

    public static Hole getHoleFromId(Long id) {
        return (Hole) getObjectFromId(Hole.class, id);
    }
}
