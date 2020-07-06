package com.pentagon.p01_android_proj.login.forget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.pentagon.p01_android_proj.util.UserPreferenceUtil;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener  {

    private LinearLayout editLayout;
    private Toolbar toolbar;
    private ImageView rainbowImage;
    private EditText userAccountEdit;
    private EditText verificationCodeEdit;
    private FloatingActionButton fab;
    private ImageView securityCode;
    private TextView registerText;
    private TextView forgetText;
    private LinearLayout wrongLayout;
    private TextView wrongTipsTextView;
    private TextView wrongOkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        initViews();
        loadImage();
        iniToolBar();
    }

    private void initViews() {
        editLayout = findViewById(R.id.edit_layout);
        toolbar = findViewById(R.id.toolbar);
        rainbowImage = findViewById(R.id.rainbow);
        userAccountEdit = findViewById(R.id.usr_account);
        verificationCodeEdit = findViewById(R.id.verification_code);
        securityCode = findViewById(R.id.security_code);
        fab = findViewById(R.id.login);
        registerText = findViewById(R.id.register);
        wrongLayout = findViewById(R.id.wrong);
        wrongTipsTextView = findViewById(R.id.wrong_tips);
        wrongOkTextView = findViewById(R.id.wrong_ok);

        toolbar.setOnClickListener(this);
        userAccountEdit.setOnClickListener(this);
        verificationCodeEdit.setOnClickListener(this);
        editLayout.setOnClickListener(this);
    }

    private void loadImage() {

        Glide.with(this).load(getDrawable(R.drawable.verification_result)).into(securityCode);

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

    public void forgetEvent(View view) {
        if (userAccountEdit.getText().toString().equals("")) {
            wrongTipsTextView.setText("账号不能为空");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userAccountEdit.setEnabled(false);
            verificationCodeEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        if (verificationCodeEdit.getText().toString().equals("")) {
            wrongTipsTextView.setText("验证文字不能为空");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userAccountEdit.setEnabled(false);
            verificationCodeEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        if (!verificationCodeEdit.getText().toString().equals("通源")) {
            wrongTipsTextView.setText("验证文字不匹配");
            wrongLayout.setVisibility(View.VISIBLE);
            toolbar.setEnabled(false);
            userAccountEdit.setEnabled(false);
            verificationCodeEdit.setEnabled(false);
            editLayout.setEnabled(false);
            return;
        }
        ResetPasswordActivity.actionStart(this, UserPreferenceUtil.getUserId(this));
        finish();
    }

    public void tipsLayoutGone(View view) {
        wrongLayout.setVisibility(View.GONE);
        toolbar.setEnabled(true);
        userAccountEdit.setEnabled(true);
        verificationCodeEdit.setEnabled(true);
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

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ForgetPasswordActivity.class);
        activity.startActivity(intent);
    }

}
