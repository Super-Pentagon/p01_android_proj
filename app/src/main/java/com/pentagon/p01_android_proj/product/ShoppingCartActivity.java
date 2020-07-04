package com.pentagon.p01_android_proj.product;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.model.ShoppingCart;
import com.pentagon.p01_android_proj.order.OrderFragment;

public class ShoppingCartActivity extends AppCompatActivity {

    private WebView webview;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            double number = Math.random();
            webview.loadUrl("javascript:javaCallJs('" + "[{\"orderId\":\"a9c8d760-6036-48a8-ab22-f7e86ff89937\",\"productId\":\"[goods_id, 12345678900]\",\"quantity\":2},{\"orderId\":\"a9cf621f-f892-4d4c-b98f-1017cbbc8654\",\"productId\":\"[goods_id, 1278246495526612993]\",\"quantity\":1}]" + "')");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        //方法2：在Activity的layout文件里添加webview控件：
        webview = findViewById(R.id.shop_cart_web_view);
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {

        //声明WebSettings子类
        WebSettings webSettings = webview.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //支持插件
//        webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //------------
        webview.addJavascriptInterface(new AndroidAndJSInterface(), "Android");
        webview.loadUrl("file:///android_asset/html/ShoppingCartInterface.html");
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        Toast.makeText(this, "666", Toast.LENGTH_SHORT).show();
    }

    class AndroidAndJSInterface {

        @JavascriptInterface
        public void registration1() {
            Toast.makeText(ShoppingCartActivity.this, "registration1", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void loading() {
            Toast.makeText(ShoppingCartActivity.this, "loading", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(0x0);
        }

        @JavascriptInterface
        public void registration2() {
            Toast.makeText(ShoppingCartActivity.this, "registration2", Toast.LENGTH_SHORT).show();
        }

    }

    public static void actionStart(Activity activity) {
        activity.startActivity(new Intent(activity, ShoppingCartActivity.class));
    }

}
