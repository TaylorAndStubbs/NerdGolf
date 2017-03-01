package com.taylorstubbs.nerdgolf.nerdgolf.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by taylorstubbs on 3/1/17.
 */

public class BiggerNumberPicker extends NumberPicker {
    private static final String TAG = "BiggerNumberPicker";

    public BiggerNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
        makeViewBigger(view);
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams params) {
        super.addView(view, params);
        makeViewBigger(view);
    }

    @Override
    public void addView(View view, int index, ViewGroup.LayoutParams params) {
        super.addView(view, index, params);
        makeViewBigger(view);
    }

    private void makeViewBigger(View view) {
        if (view instanceof EditText) {
            ((TextView) view).setTextSize(25);
        }
    }
}
