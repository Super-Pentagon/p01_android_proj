package com.pentagon.p01_android_proj.login.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.login.LoginModel;
import com.pentagon.p01_android_proj.login.LoginResponse;
import com.pentagon.p01_android_proj.login.forget.ForgetPassActivity;
import com.pentagon.p01_android_proj.login.register.RegisterActivity;
import com.pentagon.p01_android_proj.model.User;
import com.pentagon.p01_android_proj.util.UserPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback<LoginResponse> {

    private LinearLayout editLayout;
    private Toolbar toolbar;
    private ImageView usrHeadImage;
    private ImageView rainbowImage;
    private EditText userAccountEdit;
    private EditText userPasswordEdit;
    private FloatingActionButton fab;
    private TextView registerText;
    private TextView forgetText;
    private LinearLayout wrongLayout;
    private TextView wrongTipsTextView;
    private TextView wrongOkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        loadImage();
        iniToolBar();
    }

    private void initViews() {
        editLayout = findViewById(R.id.edit_layout);
        toolbar = findViewById(R.id.toolbar);
        usrHeadImage = findViewById(R.id.usr_head);
        rainbowImage = findViewById(R.id.rainbow);
        userAccountEdit = findViewById(R.id.usr_account);
        userPasswordEdit = findViewById(R.id.usr_password);
        fab = findViewById(R.id.login);
        registerText = findViewById(R.id.register);
        wrongLayout = findViewById(R.id.wrong);
        wrongTipsTextView = findViewById(R.id.wrong_tips);
        wrongOkTextView = findViewById(R.id.wrong_ok);

        toolbar.setOnClickListener(this);
        userAccountEdit.setOnClickListener(this);
        userPasswordEdit.setOnClickListener(this);
        editLayout.setOnClickListener(this);
    }

    private void loadImage() {

        Glide.with(this).load(getDrawable(R.drawable.head_icon)).apply(RequestOptions.circleCropTransform()).into(usrHeadImage);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                //.placeholder(R.mipmap.ic_launcher_round)
                .error(android.R.drawable.stat_notify_error)
                .priority(Priority.HIGH)
                //.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(this)
                .load(R.drawable.rainbow)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(options)
                //.thumbnail(Glide.with(this).load(R.mipmap.ic_launcher))
                .into(rainbowImage);
    }

    private void iniToolBar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.deepskyblue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
    }

    public void loginEvent(View view) {
        if (userAccountEdit.getText().toString().equals("")) {
            wrongTipsTextView.setText("账号不能为空");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userAccountEdit.setEnabled(false);
            userPasswordEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        if (userPasswordEdit.getText().toString().equals("")) {
            wrongTipsTextView.setText("密码不能为空");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userAccountEdit.setEnabled(false);
            userPasswordEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        User user = User.init().username(userAccountEdit.getText().toString()).password(userPasswordEdit.getText().toString()).build();

        try {
            new LoginModel().login(this, user);
        } catch (Exception e) {
            Log.e("LoginActivity.login", e.toString());
            e.printStackTrace();
        }
    }

    public void jumpToRegister(View view) {
        RegisterActivity.actionStart(this);
    }

    public void jumpToForget(View view) {
        ForgetPassActivity.actionStart(this);
    }

    public void tipsLayoutGone(View view) {
        wrongLayout.setVisibility(View.GONE);
        toolbar.setEnabled(true);
        userAccountEdit.setEnabled(true);
        userPasswordEdit.setEnabled(true);
        editLayout.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            if (wrongLayout.getVisibility() == View.VISIBLE) {
                return true;
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        Log.i("hornhuang-test-info", "" + response.body().toString());
        LoginResponse loginResponse = response.body();
        if (loginResponse.isSuccess()) {
            UserPreferenceUtil.setUserId(this, loginResponse.getData().getBuyer().getBid());
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "用户信息错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Log.e("LoginActivity.login",t.toString());
        Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
    }

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
