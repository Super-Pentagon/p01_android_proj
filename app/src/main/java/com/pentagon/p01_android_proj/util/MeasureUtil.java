package com.pentagon.p01_android_proj.util;

import android.content.Context;

public final class MeasureUtil {
    public static int dp2px(Context context, float dips) {
        return (int) (dips * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
