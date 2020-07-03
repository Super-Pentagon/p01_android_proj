package com.pentagon.p01_android_proj.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.detail.ProductDetailActivity;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.util.MeasureUtil;

import java.util.List;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProductSearchListAdapter
        extends RecyclerView.Adapter<ProductSearchListAdapter.ProductHolder> {
    public static final String PRODUCT_KEY = "product";
    public static final String TRANSITION_KEY = "transition";
    private Context mContext;
    private List<Product> mProducts;
    private MultiTransformation mMultiTransformation;

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
            String transitionName = "transition" + getAdapterPosition();
            mImageView.setTransitionName(transitionName);
            intent.putExtra(PRODUCT_KEY, mProduct);
            intent.putExtra(TRANSITION_KEY, transitionName);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    (Activity) v.getContext(),
                    mImageView,
                    transitionName
            );
            v.getContext().startActivity(intent, options.toBundle());
        }
    }

    public ProductSearchListAdapter(Context context) {
        mContext = context;
        int radius = MeasureUtil.dp2px(mContext, 120);
        CropTransformation cropTransformation =
                new CropTransformation(
                        radius,
                        radius,
                        CropTransformation.CropType.CENTER
                );
        RoundedCornersTransformation roundedCornersTransformation =
                new RoundedCornersTransformation(
                        32,
                        0,
                        RoundedCornersTransformation.CornerType.ALL
                );
        mMultiTransformation =
                new MultiTransformation(cropTransformation, roundedCornersTransformation);
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
                .load(product.getThumbnailUrl())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .apply(RequestOptions.bitmapTransform(mMultiTransformation))
                .into(imageView);
        holder.mProduct = product;
        holder.mNameTextView.setText(product.getName());
        holder.mMonthlySalesTextView.setText("月售 " + product.getMonthlySales());
        holder.mPriceTextView.setText("￥" + product.getPrice());
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