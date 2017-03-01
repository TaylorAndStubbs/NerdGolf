package com.taylorstubbs.nerdgolf.nerdgolf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.taylorstubbs.nerdgolf.nerdgolf.R;
import com.taylorstubbs.nerdgolf.nerdgolf.activities.GameActivity;
import com.taylorstubbs.nerdgolf.nerdgolf.models.Game;
import com.taylorstubbs.nerdgolf.nerdgolf.widgets.BiggerNumberPicker;

/**
 * Creates the game object. User can set course name and number of holes.
 */

public class GameInfoFragment extends Fragment {
    private static final String TAG = "GameInfoFragment";
    private static final String STATE_TOTAL_NUMBER_HOLES = "totalNumberHoles";

    private String mCourseName;
    private int mTotalNumberHoles = 1;

    //inputs
    private EditText mCourseNameInput;
    private BiggerNumberPicker mTotalNumberHolesInput;
    private Button mStartButton;

    /**
     * New instance of fragment.
     *
     * @return the fragment
     */
    public static GameInfoFragment newInstance() {
        return new GameInfoFragment();
    }

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        if (saveState != null) {
            mTotalNumberHoles = saveState.getInt(STATE_TOTAL_NUMBER_HOLES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveState) {
        View view = inflater.inflate(R.layout.fragment_info_game, container, false);

        mCourseNameInput = (EditText) view.findViewById(R.id.course_name_input);
        mTotalNumberHolesInput = (BiggerNumberPicker) view.findViewById(R.id.total_number_holes_input);
        mStartButton = (Button) view.findViewById(R.id.start_game_button);

        mCourseNameInput.setSingleLine();
        mCourseNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //NA
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCourseName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //NA
            }
        });

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateGameInfo(mCourseName)) {
                    Game game = new Game(mCourseName, mTotalNumberHoles);
                    game.save();

                    getActivity().startActivity(GameActivity.createIntent(getActivity(), game));
                } else {
                    Toast.makeText(getContext(), "Course Name cannot be blank.", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        initNumberPicker(mTotalNumberHolesInput);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        saveState.putInt(STATE_TOTAL_NUMBER_HOLES, mTotalNumberHoles);
    }

    /**
     * Checks if information is incomplete.
     *
     * @param courseName the name of the course
     * @return whether or not the info is valid
     */
    private boolean validateGameInfo(String courseName) {
        return courseName != null && !courseName.isEmpty();
    }

    /**
     * Create the number picker.
     *
     * @param numberPicker a NumberPicker
     */
    private void initNumberPicker(NumberPicker numberPicker) {
        numberPicker.setMaxValue(18);
        numberPicker.setMinValue(1);

        if (mTotalNumberHoles != 0) {
            numberPicker.setValue(mTotalNumberHoles);
        }

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mTotalNumberHoles = newVal;
            }
        });
    }
}
