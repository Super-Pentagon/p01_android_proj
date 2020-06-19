package com.pentagon.p01_android_proj.util;

import android.util.Log;

public final class LogHelper {
    public static void log(Object o) {
        Log.i("tagg", o == null ? "object logged is null" : o.toString());
    }
}
