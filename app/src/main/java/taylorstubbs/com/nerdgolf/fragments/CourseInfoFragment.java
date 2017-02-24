package taylorstubbs.com.nerdgolf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import taylorstubbs.com.nerdgolf.R;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class CourseInfoFragment extends Fragment {
    private static final String TAG = "CourseInfoFragment";

    public static CourseInfoFragment newInstance() {
        return new CourseInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_info_course, container, false);

        return view;
    }
}
