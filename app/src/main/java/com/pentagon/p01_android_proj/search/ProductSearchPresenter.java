package com.pentagon.p01_android_proj.search;

import android.content.Context;
import android.content.SharedPreferences;

import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.service.ProductSearchService;
import com.pentagon.p01_android_proj.service.ServiceGenerator;
import com.pentagon.p01_android_proj.util.LogHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductSearchPresenter implements IProductSearchPresenter {
    private final static int INTERVAL_TIME_MILLIS = 500;
    private final static String SP_KEY = "search records";
    private final static String KEY_0 = "search record 0";
    private final static String KEY_1 = "search record 1";
    private final static String KEY_2 = "search record 2";
    private final static String KEY_3 = "search record 3";

    private Timer mTimer;
    private boolean mIsCanceled;
    private IProductSearchView mProductSearchView;
    private Disposable mDisposable;

    public ProductSearchPresenter(IProductSearchView IProductSearchView) {
        mProductSearchView = IProductSearchView;
        mIsCanceled = true;
    }

    @Override
    public void tryToSearch() {
        if (mTimer != null) {
            synchronized (mTimer) {
                if (!mIsCanceled) {
                    mTimer.cancel();
                }
            }
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mProductSearchView.onReadyForSearching();
                mIsCanceled = true;
            }
        }, INTERVAL_TIME_MILLIS);
        mIsCanceled = false;
    }

    @Override
    public void searchProducts(String inputString) {
        unsubscribeLast();
        ServiceGenerator.createService(ProductSearchService.class)
//                .getSearchedProducts(inputString)
                .getSearchedProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogHelper.log("onSubscribe");
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        LogHelper.log("onNext");
                        mProductSearchView.onSearchCompleted(products);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogHelper.log("onError:" + e);
                    }

                    @Override
                    public void onComplete() {
                        LogHelper.log("onComplete");
                    }
                });
    }

    @Override
    public void initSearchRecords() {
        Context context = (Context) mProductSearchView;
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);

        String[] strings = new String[4];
        strings[0] = sp.getString(KEY_0, null);
        strings[1] = sp.getString(KEY_1, null);
        strings[2] = sp.getString(KEY_2, null);
        strings[3] = sp.getString(KEY_3, null);

        mProductSearchView.onInitRecordsCompleted(strings);
    }

    @Override
    public void saveSearchRecord(String inputString) {
        Context context = (Context) mProductSearchView;
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);

        String[] strings = new String[4];
        strings[0] = sp.getString(KEY_0, null);
        strings[1] = sp.getString(KEY_1, null);
        strings[2] = sp.getString(KEY_2, null);
        strings[3] = sp.getString(KEY_3, null);

        sp.edit()
                .putString(KEY_0, inputString)
                .putString(KEY_1, strings[0])
                .putString(KEY_2, strings[1])
                .putString(KEY_3, strings[2])
                .apply();
    }

    @Override
    public void sortWithSales(List<Product> list, boolean isAscending) {
        Comparator<Product> comparator;
        if (isAscending) {
            comparator = new Comparator<Product>() {
                public int compare(Product o1, Product o2) {
                    if (o1.getMonthlySales() > o2.getMonthlySales()) {
                        return 1;
                    } else if (o1.getMonthlySales() < o2.getMonthlySales()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };
        } else {
            comparator = new Comparator<Product>() {
                public int compare(Product o1, Product o2) {
                    if (o1.getMonthlySales() > o2.getMonthlySales()) {
                        return -1;
                    } else if (o1.getMonthlySales() < o2.getMonthlySales()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            };
        }

        Collections.sort(list, comparator);
        mProductSearchView.onSortCompleted();
    }

    @Override
    public void sortWithPrice(List<Product> list, boolean isAscending) {
        Comparator<Product> comparator;
        if (isAscending) {
            comparator = new Comparator<Product>() {
                public int compare(Product o1, Product o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            };
        } else {
            comparator = new Comparator<Product>() {
                public int compare(Product o1, Product o2) {
                    return o2.getPrice().compareTo(o1.getPrice());
                }
            };
        }

        Collections.sort(list, comparator);
        mProductSearchView.onSortCompleted();
    }

    @Override
    public void onDestroy() {
        unsubscribeLast();
        mProductSearchView = null;
    }

    private void unsubscribeLast() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            LogHelper.log("unsubscribeLast");
            mDisposable.dispose();
        }
    }
}
