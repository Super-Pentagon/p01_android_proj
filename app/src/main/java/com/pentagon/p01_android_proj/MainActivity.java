package com.pentagon.p01_android_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pentagon.p01_android_proj.flutter.utils.FlutterEngineUtils;
import com.pentagon.p01_android_proj.flutter.utils.FlutterOperationUtil;
import com.pentagon.p01_android_proj.login.login.LoginActivity;
import com.pentagon.p01_android_proj.mine.MineFragment;
import com.pentagon.p01_android_proj.order.OrderFragment;
import com.pentagon.p01_android_proj.search.ProductSearchActivity;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterMain;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mainContainer;

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.page_take_out:
                            FlutterOperationUtil.init().initFragmentById(FlutterEngineUtils.Home.HOME_PAGE_ROUTE).initLayoutId(R.id.main_container).startFlutterTransation(getSupportFragmentManager());
                            FlutterEngine flutterEngine = FlutterEngineCache
                                    .getInstance()
                                    .get(FlutterEngineUtils.Home.HOME_PAGE_ROUTE);
                            MethodChannel  mc = new MethodChannel(flutterEngine.getDartExecutor(), "com.cc.flutter.native"); //此处名称应与Flutter端保持一致
                            //接收Flutter消息
                            mc.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
                                @Override
                                public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                                    System.out.println("MethodChannel call.method:"+call.method+ "  call arguments:"+call.arguments);
                                    switch (call.method){
                                        case "jumpToDetail":
                                            result.success("2");
                                            Toast.makeText(MainActivity.this, "jumpToDetail", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "jumpToSearch":
                                            result.success("成功啦");
                                            String msg = call.argument("msg");
                                            Toast.makeText(MainActivity.this, "jumpToSearch", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent( MainActivity.this, ProductSearchActivity.class));
                                            break;
                                        default:
                                            Toast.makeText(MainActivity.this, "未匹配到对应的方法", Toast.LENGTH_SHORT).show();
                                            result.error("404", "未匹配到对应的方法"+call.method, null);
                                    }
                                }
                            });
                            return true;

                        case R.id.page_order:
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new OrderFragment()).commit();
                            return true;

                        case R.id.page_mine:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_container, new MineFragment())
                                    .commit();
                            return true;

                        default:
                            return true;
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
        navigationView.setSelectedItemId(R.id.page_take_out);
        mainContainer = findViewById(R.id.main_container);
    }

}
