package com.pentagon.p01_android_proj.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.detail.ProductDetailActivity;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.util.LogHelper;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchListAdapter
        extends RecyclerView.Adapter<ProductSearchListAdapter.ProductHolder> {
    public static final String PRODUCT_KEY = "product";
    private Context mContext;
    private List<Product> mProducts;

    public static class ProductHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Product mProduct;
        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mMonthlySalesTextView;
        private TextView mPriceTextView;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.pictureImageView);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            mMonthlySalesTextView = itemView.findViewById(R.id.monthlySalesTextView);
            mPriceTextView = itemView.findViewById(R.id.priceTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
            intent.putExtra(PRODUCT_KEY,mProduct);
            v.getContext().startActivity(intent);
        }
    }

    public ProductSearchListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater
                .inflate(R.layout.item_view_product_search_list, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = mProducts.get(position);
        ImageView imageView = holder.mImageView;
        Glide.with(mContext)
                .clear(imageView);
        Glide.with(mContext)
                .load(product.getPictureUrl())
                .into(imageView);
        holder.mProduct = product;
        holder.mNameTextView.setText(product.getName());
        holder.mMonthlySalesTextView.setText("月售 " + product.getMonthlySales());
        holder.mPriceTextView.setText("￥" + product.getPrice() + "/份");
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }
}