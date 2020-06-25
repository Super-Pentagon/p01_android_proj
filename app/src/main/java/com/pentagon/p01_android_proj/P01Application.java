package com.pentagon.p01_android_proj;

import android.app.Application;

import com.pentagon.p01_android_proj.flutter.utils.FlutterEngineUtils;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.view.FlutterMain;

public class P01Application extends Application {

    private static P01Application instance;
    public static P01Application  getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Flutter
        FlutterMain.Settings settings=new FlutterMain.Settings();
        settings.setLogTag("MyFlutter");
        FlutterMain.startInitialization(this,settings);
        String[] args = {"info", "data"};
        FlutterMain.ensureInitializationComplete(this,args);
        instance=this;

        try {
            // 初始化 FlutterEngine.
            FlutterEngine flutterEngine = new FlutterEngine(this);
            // Configure an initial route.
            flutterEngine.getNavigationChannel().setInitialRoute(FlutterEngineUtils.Home.HOME_PAGE_ROUTE + "?{\"message\":\"StephenCurry\"}");
            // Start executing Dart code to pre-warm the FlutterEngine.
            flutterEngine.getDartExecutor().executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            );
            // Cache the FlutterEngine to be used by FlutterActivity or FlutterFragment.
            FlutterEngineCache
                    .getInstance()
                    .put(FlutterEngineUtils.Home.HOME_PAGE_ROUTE, flutterEngine);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
