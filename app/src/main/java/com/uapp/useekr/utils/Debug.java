package com.uapp.useekr.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 12/2/17.
 */

public class Debug {

    private static final String TAG = "useekr";

    private static final DateFormat dateFormatter = new SimpleDateFormat("h:mm a", Locale.getDefault());

    public static void log(String message, Object ...args) {
        Log.d(TAG, String.format(timestamp() + " > " + message, args));
    }

    private static String timestamp() {
        return dateFormatter.format(new Date());
    }
}