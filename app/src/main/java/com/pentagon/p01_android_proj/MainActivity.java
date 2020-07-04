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
import com.google.gson.Gson;
import com.pentagon.p01_android_proj.detail.ProductDetailActivity;
import com.pentagon.p01_android_proj.flutter.utils.FlutterEngineUtils;
import com.pentagon.p01_android_proj.flutter.utils.FlutterOperationUtil;
import com.pentagon.p01_android_proj.login.login.LoginActivity;
import com.pentagon.p01_android_proj.mine.MineFragment;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.model.ShoppingCart;
import com.pentagon.p01_android_proj.order.OrderFragment;
import com.pentagon.p01_android_proj.product.ProductModel;
import com.pentagon.p01_android_proj.search.ProductSearchActivity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterMain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Object> {

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
                                            List<String> msg = (List<String>) call.arguments;
                                            new ProductModel().getProductById(msg.get(msg.size()-1), MainActivity.this);
                                            Product product = new Product();
                                            product.setId(msg.toString());
                                            product.setPrice(new BigDecimal(9.9));
                                            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                                            intent.putExtra("product", product);
                                            startActivity(intent);
                                            Toast.makeText(MainActivity.this, "jumpToDetail" + msg.get(msg.size()-1), Toast.LENGTH_SHORT).show();
                                            break;
                                        case "jumpToSearch":
                                            result.success("成功啦");
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

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, new Gson().toJson(ShoppingCart.getInstance().getOrderItems()), Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", new Gson().toJson(ShoppingCart.getInstance().getOrderItems()));
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        Map<String, Object> map = new Gson().fromJson("{success=true, code=20000.0, message=成功, data={product={pid=12345678900, price=1.0, sid=778, des=卫龙好吃, purl=http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg, thumbnailurl=http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg, pname=卫龙, monthlysales=10.0}}}", Map.class);

        Log.d("MainActivity---》", map.get("data").toString());
        Toast.makeText(this, response.body().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        Toast.makeText(this, "onFailure", Toast.LENGTH_LONG).show();
    }
}
