package com.pentagon.p01_android_proj.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.model.ShoppingCart;
import com.pentagon.p01_android_proj.product.ShoppingCartActivity;
import com.pentagon.p01_android_proj.search.ProductSearchListAdapter;
import com.pentagon.p01_android_proj.util.LogHelper;
import com.pentagon.p01_android_proj.util.MeasureUtil;

import java.math.BigDecimal;

public class ProductDetailActivity extends AppCompatActivity implements IProductDetailView {
    private int mQuantity = 0;
    private IProductDetailPresenter mProductDetailPresenter;
    private TextView mSubtotalText;
    private TextView mKindQuantityText;
    private TextView mQuantityText;
    private LottieAnimationView mCartAnimView;
    private ImageView mPlus;
    private float[] mTranslation = new float[2];
    private int mIconsCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Window window = getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        ImageView picture = findViewById(R.id.picture);
        ImageView minus = findViewById(R.id.minus);
        TextView price = findViewById(R.id.price);
        TextView monthlySales = findViewById(R.id.monthlySales);
        TextView des = findViewById(R.id.des);
        TextView pay = findViewById(R.id.pay);
        TextView name = findViewById(R.id.name);
        mSubtotalText = findViewById(R.id.subtotal);
        mKindQuantityText = findViewById(R.id.kindQuantity);
        mQuantityText = findViewById(R.id.quantity);
        mCartAnimView = findViewById(R.id.shoppingCart);
        mPlus = findViewById(R.id.plus);
        FrameLayout backLayout = findViewById(R.id.backLinearLayout);

        int height = 0;
        int resourceId = getApplicationContext()
                .getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }

        ConstraintLayout.MarginLayoutParams params=
                (ConstraintLayout.MarginLayoutParams) backLayout.getLayoutParams();
        params.topMargin+=height;
        backLayout.setLayoutParams(params);

        Product product = (Product) getIntent().getSerializableExtra(
                ProductSearchListAdapter.PRODUCT_KEY
        );

        mProductDetailPresenter = new ProductDetailPresenter(
                product.getId(),
                product.getPrice(),
                this
        );

        backLayout.setOnClickListener(v -> finish());

        price.setText("￥" + product.getPrice());
        monthlySales.setText("月售 " + product.getMonthlySales());
        des.setText(product.getDes());
        name.setText(product.getName());

        minus.setOnClickListener(v -> {
            if (mQuantity > 0) {
                mQuantity--;
                mQuantityText.setText(String.valueOf(mQuantity));
                mProductDetailPresenter.updateShoppingCart(mQuantity);
            }
        });

        mPlus.setOnClickListener(v -> {
            if (mQuantity < 1000) {
                startAnimation();
                mQuantity++;
                mQuantityText.setText(String.valueOf(mQuantity));
                mProductDetailPresenter.updateShoppingCart(mQuantity);
            }
        });

        String transitionName = getIntent().getStringExtra(ProductSearchListAdapter.TRANSITION_KEY);
        if (transitionName != null) {
            picture.setTransitionName(transitionName);
        }
        Glide.with(this)
                .load(product.getPictureUrl())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(picture);

        pay.setOnClickListener(v -> {
            //TODO start order confirm activity
            ShoppingCartActivity.actionStart(this);
        });

        mCartAnimView.setProgress(1);

        mProductDetailPresenter.initWithShoppingCart();
    }

    @Override
    protected void onDestroy() {
        mProductDetailPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onUpdateCartCompleted(int kindQuantity, BigDecimal subtotal) {
        mKindQuantityText.setText(String.valueOf(kindQuantity));
        mSubtotalText.setText("￥" + subtotal);
    }

    @Override
    public void onInitCompleted(int quantity, int kindQuantity, BigDecimal subtotal) {
        mQuantityText.setText(String.valueOf(quantity));
        mKindQuantityText.setText(String.valueOf(kindQuantity));
        mSubtotalText.setText("￥" + subtotal);
    }

    private void startAnimation() {
        ImageView productIcon = new ImageView(this);
        productIcon.setImageResource(R.drawable.shape_blue_ball);
        int size = MeasureUtil.dp2px(this, 30);
        ViewGroup contentView = findViewById(android.R.id.content);
        ConstraintLayout root = (ConstraintLayout) contentView.getChildAt(0);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size, size);
        productIcon.setLayoutParams(params);
        root.addView(productIcon);

        int[] rootLocation = new int[2];
        int[] plusLocation = new int[2];
        int[] cartLocation = new int[2];
        root.getLocationOnScreen(rootLocation);
        mPlus.getLocationOnScreen(plusLocation);
        mCartAnimView.getLocationOnScreen(cartLocation);
        int animViewHeight = mCartAnimView.getMeasuredHeight();
        int animViewWidth = mCartAnimView.getMeasuredWidth();

        productIcon.setId(mIconsCounter);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(root);
        constraintSet.connect(mIconsCounter, ConstraintSet.START, ConstraintSet.PARENT_ID,
                ConstraintSet.START, plusLocation[0] - rootLocation[0]);
        constraintSet.connect(mIconsCounter, ConstraintSet.TOP, ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, plusLocation[1] - rootLocation[1]);
        constraintSet.applyTo(root);
        mIconsCounter++;

        int startX = 0;
        int startY = 0;
        int endX = cartLocation[0] - plusLocation[0] + animViewWidth / 2 - size / 2;
        int endY = cartLocation[1] - plusLocation[1] + animViewHeight / 2 - size / 2;
        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo((endX - startX) * 0.8f + startX, startY, endX, endY);
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                float playPercent =
                        (float) animation.getCurrentPlayTime() / animation.getTotalDuration();
                pathMeasure.getPosTan(value, mTranslation, null);
                productIcon.setTranslationX(mTranslation[0]);
                productIcon.setTranslationY(mTranslation[1]);
                float cartScaleRatio = 0.2f + 0.8f * (1 - playPercent);
                productIcon.setScaleX(cartScaleRatio);
                productIcon.setScaleY(cartScaleRatio);
                float kqScaleRatio = 1;
                if (playPercent < 0.25) {
                    kqScaleRatio = (0.5f - playPercent) * 2;
                } else if (playPercent < 0.5) {
                    kqScaleRatio = playPercent * 2;
                }
                mKindQuantityText.setScaleX(kqScaleRatio);
                mKindQuantityText.setScaleY(kqScaleRatio);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                root.removeView(productIcon);
            }
        });
        valueAnimator.start();
        mCartAnimView.playAnimation();
    }
}