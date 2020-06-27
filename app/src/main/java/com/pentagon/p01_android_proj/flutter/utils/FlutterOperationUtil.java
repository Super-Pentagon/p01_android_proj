package com.pentagon.p01_android_proj.flutter.utils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;

public class FlutterOperationUtil {

    private FlutterFragment flutterFragment = new FlutterFragment();

    private @IdRes
    int containerViewId;

    private FlutterOperationUtil() { }

    public void startFlutterTransation(@NonNull FragmentManager fragmentManager) {
        fragmentManager
                .beginTransaction()
                .add(containerViewId, flutterFragment)
                .commit();
    }

    public FlutterOperationUtil initFragmentById(String EngineId) {
        this.flutterFragment = FlutterFragment.withCachedEngine(EngineId)
                .build();
        return this;
    }

    public FlutterOperationUtil initLayoutId(@IdRes int containerViewId) {
        this.containerViewId = containerViewId;
        return this;
    }

    public static FlutterOperationUtil init() {
        return new FlutterOperationUtil();
    }
}
