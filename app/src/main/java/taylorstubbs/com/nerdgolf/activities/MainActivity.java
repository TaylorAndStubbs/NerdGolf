package taylorstubbs.com.nerdgolf.activities;

import android.support.v4.app.Fragment;

import taylorstubbs.com.nerdgolf.fragments.MainFragment;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class MainActivity extends SingleFragmentActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }
}
