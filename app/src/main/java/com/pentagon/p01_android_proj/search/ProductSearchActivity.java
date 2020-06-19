package com.pentagon.p01_android_proj.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.util.LogHelper;


import java.util.List;

public class ProductSearchActivity extends AppCompatActivity implements IProductSearchView
        , View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ProductSearchListAdapter mAdapter;
    private IProductSearchPresenter mProductSearchPresenter;
    private Editable mInputEditable;
    private Button[] mButtons = new Button[8];
    private EditText mEditText;
    private ConstraintLayout mListLayout;
    private ConstraintLayout mQuickSearchLayout;
    private CheckBox mSalesCheckBox;
    private CheckBox mPriceCheckBox;
    private int mTintColorChecked;
    private int tintColorUnchecked;
    private Drawable mDrawableChecked;
    private Drawable mDrawableUnchecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);

        mProductSearchPresenter = new ProductSearchPresenter(this);

        mButtons[0] = findViewById(R.id.button);
        mButtons[1] = findViewById(R.id.button2);
        mButtons[2] = findViewById(R.id.button3);
        mButtons[3] = findViewById(R.id.button4);
        mButtons[4] = findViewById(R.id.button5);
        mButtons[5] = findViewById(R.id.button6);
        mButtons[6] = findViewById(R.id.button7);
        mButtons[7] = findViewById(R.id.button8);
        mEditText = findViewById(R.id.editText);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ProductSearchListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this
                , DividerItemDecoration.VERTICAL));
        LinearLayout backLinearLayout = findViewById(R.id.backLinearLayout);
        mListLayout = findViewById(R.id.listLayout);
        mQuickSearchLayout = findViewById(R.id.quickSearchLayout);
        mSalesCheckBox = findViewById(R.id.salesCheckBox);
        mPriceCheckBox = findViewById(R.id.priceCheckBox);
        mTintColorChecked = getResources().getColor(R.color.colorAccent);
        tintColorUnchecked = getResources().getColor(R.color.gray);
        mDrawableChecked = getResources().getDrawable(R.drawable.drawable_round_rect_accent);
        mDrawableUnchecked = getResources().getDrawable(R.drawable.drawable_round_rect);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogHelper.log("afterTextChanged:" + s);
                mInputEditable = s;
                mProductSearchPresenter.tryToSearch();
            }
        });

        backLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (Button button : mButtons) {
            button.setOnClickListener(this);
        }

        mSalesCheckBox.setOnCheckedChangeListener(this);
        mPriceCheckBox.setOnCheckedChangeListener(this);

        mProductSearchPresenter.initSearchRecords();
    }

    @Override
    protected void onDestroy() {
        mProductSearchPresenter.clearReference();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        CharSequence inputSequence = button.getText();
        String inputString = inputSequence.toString();
        mEditText.setText(inputSequence);
        mProductSearchPresenter.searchProducts(inputString);
        mQuickSearchLayout.setVisibility(View.INVISIBLE);
        mListLayout.setVisibility(View.VISIBLE);
        switch (v.getId()) {
            case R.id.button:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
                mProductSearchPresenter.saveSearchRecord(inputString);
            default:
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.salesCheckBox:
                mSalesCheckBox.getButtonDrawable().setTint(mTintColorChecked);
                mSalesCheckBox.setBackground(mDrawableChecked);
                mSalesCheckBox.setTextColor(mTintColorChecked);
                mPriceCheckBox.getButtonDrawable().setTint(tintColorUnchecked);
                mPriceCheckBox.setBackground(mDrawableUnchecked);
                mPriceCheckBox.setTextColor(tintColorUnchecked);
                mProductSearchPresenter.sortWithSales(mAdapter.getProducts(), isChecked);
                break;
            case R.id.priceCheckBox:
                mPriceCheckBox.getButtonDrawable().setTint(mTintColorChecked);
                mPriceCheckBox.setBackground(mDrawableChecked);
                mPriceCheckBox.setTextColor(mTintColorChecked);
                mSalesCheckBox.getButtonDrawable().setTint(tintColorUnchecked);
                mSalesCheckBox.setBackground(mDrawableUnchecked);
                mSalesCheckBox.setTextColor(tintColorUnchecked);
                mProductSearchPresenter.sortWithPrice(mAdapter.getProducts(), isChecked);
                break;
            default:
        }
    }

    @Override
    public void onReadyForSearching() {
        String string = mInputEditable.toString();
        mProductSearchPresenter.searchProducts(string);
        mProductSearchPresenter.saveSearchRecord(string);
    }

    @Override
    public void onSearchCompleted(List<Product> products) {
        mQuickSearchLayout.setVisibility(View.INVISIBLE);
        mListLayout.setVisibility(View.VISIBLE);
        mAdapter.setProducts(products);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInitRecordsCompleted(String[] recordStrings) {
        for (int i = 0; i < recordStrings.length; i++) {
            if (recordStrings[i] == null) return;
            mButtons[i].setText(recordStrings[i]);
            mButtons[i].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSortCompleted() {
        mAdapter.notifyDataSetChanged();
    }
}