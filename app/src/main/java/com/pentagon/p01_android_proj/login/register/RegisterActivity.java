package com.pentagon.p01_android_proj.login.register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
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

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView rainbowImage;
    private EditText userAccountEdit;
    private EditText userPasswordEdit;
    private EditText passwordAgainEdit;
    private FloatingActionButton fab;
    private TextView registerText;
    private TextView forgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        loadImage();
        iniToolBar();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        rainbowImage = findViewById(R.id.rainbow);
        userAccountEdit = findViewById(R.id.usr_account);
        userPasswordEdit = findViewById(R.id.usr_password);
        passwordAgainEdit = findViewById(R.id.usr_password_again);
        fab = findViewById(R.id.login);
        registerText = findViewById(R.id.register);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
