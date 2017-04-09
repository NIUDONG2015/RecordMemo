package com.example.tailin.privatepage.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

/**
 * 所有的Activity继承自此BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private SparseArray<View> mViews; //查找控件的缓存，达到优化


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        //直接返回布局View
        setContentView(getLayoutId());
        initView();
        initListener();
        initData();
    }

    protected abstract int getLayoutId(); //获取根视图id

    protected abstract void initView();//初始view

    protected abstract void initData();//初始数据

    protected abstract void initListener();//初始监听

    protected abstract void processClick(View view);//onClick事件处理

    public void onClick(View view) {
        processClick(view);
    }


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


    /**
     * 设置onClick事件监听,继承{@link BaseActivity}的Activity都调用该方法设置onClick事件
     *
     * @param view 需要设置监听的view
     * @param <E>
     */
    protected <E extends View> void setClick(E view) {
        if (view == null)
            return;
        view.setOnClickListener(this);
    }

}
