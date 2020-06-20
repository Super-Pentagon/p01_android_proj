package com.pentagon.p01_android_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pentagon.p01_android_proj.flutter.utils.FlutterEngineUtils;
import com.pentagon.p01_android_proj.flutter.utils.FlutterOperationUtil;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView.OnNavigationItemReselectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.page_take_out:
                            FlutterOperationUtil.init().initFragmentById(FlutterEngineUtils.Home.HOME_PAGE_ROUTE).initLayoutId(R.id.flutter_container).startFlutterTransation(getSupportFragmentManager());
                            break;

                        case R.id.page_order:
                            // TODO
                            Toast.makeText(MainActivity.this, "page_order", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.page_mine:
                            // TODO
                            Toast.makeText(MainActivity.this, "page_mine", Toast.LENGTH_SHORT).show();
                            break;

                        default:
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemReselectedListener(navigationItemReselectedListener);
        navigationView.setSelectedItemId(R.id.page_take_out);

    }

}
