package com.pentagon.p01_android_proj.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.search.ProductSearchListAdapter;

import java.io.Serializable;

public class ProductDetailActivity extends AppCompatActivity implements IProductDetailView {
    private int quantity = 0;

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
        TextView quantityText = findViewById(R.id.quantity);
        TextView subtotal = findViewById(R.id.subtotal);
        TextView pay = findViewById(R.id.pay);
        TextView kindQuantity = findViewById(R.id.kindQuantity);
        TextView name=findViewById(R.id.name);
        LinearLayout backLayout = findViewById(R.id.backLinearLayout);

        Product product = (Product) getIntent().getSerializableExtra(
                ProductSearchListAdapter.PRODUCT_KEY
        );

        backLayout.setOnClickListener(v -> finish());

        price.setText("￥" + product.getPrice());
        monthlySales.setText("月售 " + product.getMonthlySales());
        des.setText(product.getDes());
        name.setText(product.getName());

        minus.setOnClickListener(v -> {
            if (quantity > 0) quantity--;
            quantityText.setText(String.valueOf(quantity));
        });

        plus.setOnClickListener(v -> {
            if (quantity < 1000) quantity++;
            quantityText.setText(String.valueOf(quantity));
        });

        Glide.with(this)
                .load(product.getPictureUrl())
                .into(picture);

        pay.setOnClickListener(v -> {
            //TODO start order confirm activity
        });
    }
}