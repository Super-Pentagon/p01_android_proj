package com.pentagon.p01_android_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pentagon.p01_android_proj.flutter.utils.FlutterEngineUtils;
import com.pentagon.p01_android_proj.flutter.utils.FlutterOperationUtil;
import com.pentagon.p01_android_proj.order.OrderFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.page_take_out:
                            FlutterOperationUtil.init().initFragmentById(FlutterEngineUtils.Home.HOME_PAGE_ROUTE).initLayoutId(R.id.main_container).startFlutterTransation(getSupportFragmentManager());
                            return true;

                        case R.id.page_order:
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new OrderFragment()).commit();
                            return true;

                        case R.id.page_mine:
                            // TODO
                            Toast.makeText(MainActivity.this, "page_mine", Toast.LENGTH_SHORT).show();
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

    }

}
