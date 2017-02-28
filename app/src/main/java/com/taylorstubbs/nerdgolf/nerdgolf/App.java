package com.taylorstubbs.nerdgolf.nerdgolf;

import com.orm.SugarApp;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.FontUtil;

/**
 * The app.
 */

public class App extends SugarApp {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        FontUtil.setDefaultFont(this, "MONOSPACE", "fonts/lato.ttf");
    }
}
