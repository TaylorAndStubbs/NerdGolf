package com.taylorstubbs.nerdgolf.nerdgolf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by taylorstubbs on 2/26/17.
 */

public enum DateUtil {;

    /**
     * Create a date from a string.
     *
     * @param string    the string to make a date from
     * @return the date
     */
    public static Date createDate(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());

        try {
            return sdf.parse(string);
        } catch (ParseException e) {
            //TODO SOMETHING WITH THIS EXCEPTION
        }

        return null;
    }
}
