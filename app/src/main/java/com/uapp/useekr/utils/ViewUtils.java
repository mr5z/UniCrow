package com.uapp.useekr.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by root on 12/2/17.
 */

public class ViewUtils {

    private Context context;

    public ViewUtils(Context context) {
        this.context = context;
    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}