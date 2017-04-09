package com.example.tailin.privatepage.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * 包含fragment的activity
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private SparseArray<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initListener();
        initData();
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract int getLayoutId();//获取根视图id

    /**
     * findViewById，继承{@link BaseActivity}的Activity都调用该方法查找控件
     *
     * @param viewId 要查找控件的id
     * @param <E>
     * @return id对应的view
     */
    protected <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);
        if (view == null) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

}
