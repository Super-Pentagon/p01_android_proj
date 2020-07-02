package com.pentagon.p01_android_proj.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.search.ProductSearchListAdapter;

import java.math.BigDecimal;

public class ProductDetailActivity extends AppCompatActivity implements IProductDetailView {
    private int quantity = 0;
    private IProductDetailPresenter mProductDetailPresenter;
    private TextView subtotalText;
    private TextView kindQuantityText;
    private TextView quantityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView picture = findViewById(R.id.picture);
        ImageView minus = findViewById(R.id.minus);
        ImageView plus = findViewById(R.id.plus);
        TextView price = findViewById(R.id.price);
        TextView monthlySales = findViewById(R.id.monthlySales);
        TextView des = findViewById(R.id.des);
        TextView pay = findViewById(R.id.pay);
        TextView name = findViewById(R.id.name);
        subtotalText = findViewById(R.id.subtotal);
        kindQuantityText = findViewById(R.id.kindQuantity);
        quantityText = findViewById(R.id.quantity);
        LinearLayout backLayout = findViewById(R.id.backLinearLayout);

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
            if (quantity > 0){
                quantity--;
                quantityText.setText(String.valueOf(quantity));
                mProductDetailPresenter.updateShoppingCart(quantity);
            }
        });

        plus.setOnClickListener(v -> {
            if (quantity < 1000) {
                quantity++;
                quantityText.setText(String.valueOf(quantity));
                mProductDetailPresenter.updateShoppingCart(quantity);
            }
        });

        Glide.with(this)
                .load(product.getPictureUrl())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(picture);

        pay.setOnClickListener(v -> {
            //TODO start order confirm activity
        });

        mProductDetailPresenter.initWithShoppingCart();
    }

    @Override
    protected void onDestroy() {
        mProductDetailPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onUpdateCartCompleted(int kindQuantity, BigDecimal subtotal) {
        kindQuantityText.setText(String.valueOf(kindQuantity));
        subtotalText.setText("￥"+subtotal);
    }

    @Override
    public void onInitCompleted(int quantity, int kindQuantity, BigDecimal subtotal) {
        quantityText.setText(String.valueOf(quantity));
        kindQuantityText.setText(String.valueOf(kindQuantity));
        subtotalText.setText("￥"+subtotal);
    }
}