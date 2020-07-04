package com.pentagon.p01_android_proj.login.forget;

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
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.login.LoginModel;
import com.pentagon.p01_android_proj.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassActivity extends AppCompatActivity implements View.OnClickListener, Callback<ForgetResponse> {

    private final static String USER_PASSWORD = "user_password";

    private LinearLayout editLayout;
    private Toolbar toolbar;
    private ImageView rainbowImage;
    private EditText userPasswordEdit;
    private EditText userPasswordAgainEdit;
    private FloatingActionButton fab;
    private TextView registerText;
    private TextView forgetText;
    private LinearLayout wrongLayout;
    private TextView wrongTipsTextView;
    private TextView wrongOkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        initViews();
        loadImage();
        iniToolBar();
    }

    private void initViews() {
        editLayout = findViewById(R.id.edit_layout);
        toolbar = findViewById(R.id.toolbar);
        rainbowImage = findViewById(R.id.rainbow);
        userPasswordEdit = findViewById(R.id.usr_password);
        userPasswordAgainEdit = findViewById(R.id.usr_password_again);
        fab = findViewById(R.id.login);
        registerText = findViewById(R.id.register);
        wrongLayout = findViewById(R.id.wrong);
        wrongTipsTextView = findViewById(R.id.wrong_tips);
        wrongOkTextView = findViewById(R.id.wrong_ok);

        toolbar.setOnClickListener(this);
        userPasswordEdit.setOnClickListener(this);
        userPasswordAgainEdit.setOnClickListener(this);
        editLayout.setOnClickListener(this);
    }

    private void loadImage() {

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

    public void resetEvent(View view) {
        if (userPasswordEdit.getText().toString().equals("")) {
            wrongTipsTextView.setText("密码不能为空");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userPasswordEdit.setEnabled(false);
            userPasswordAgainEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        if (userPasswordAgainEdit.getText().toString().equals("")) {
            wrongTipsTextView.setText("确认密码不能为空");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userPasswordEdit.setEnabled(false);
            userPasswordAgainEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        if (userPasswordAgainEdit.getText().toString().equals(userPasswordEdit.getText().toString())) {
            wrongTipsTextView.setText("两次密码不一样");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userPasswordEdit.setEnabled(false);
            userPasswordAgainEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        User user = new User(getIntent().getStringExtra(USER_PASSWORD), userPasswordAgainEdit.getText().toString());
        try {
            new LoginModel().reset(this, user);
        } catch (Exception e) {
            Log.e("ResetPassActivity.reset", e.getMessage());
            e.printStackTrace();
        }
    }

    public void tipsLayoutGone(View view) {
        wrongLayout.setVisibility(View.GONE);
        toolbar.setEnabled(true);
        userPasswordEdit.setEnabled(true);
        userPasswordAgainEdit.setEnabled(true);
        editLayout.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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
    public void onResponse(Call<ForgetResponse> call, Response<ForgetResponse> response) {
        Log.i("hornhuang-test-info", "" + response.body());
        ForgetResponse loginResponse = response.body();
        if (loginResponse.isSuccess() == false) {
            Toast.makeText(this, "用户信息错误", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "密码重置成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onFailure(Call<ForgetResponse> call, Throwable t) {
        Log.e("ResetPassActivity.reset",t.toString());
        Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
    }

    public static void actionStart(Activity activity, String user_password) {
        Intent intent = new Intent(activity, ResetPassActivity.class);
        intent.putExtra(USER_PASSWORD, user_password);
        activity.startActivity(intent);
    }

}