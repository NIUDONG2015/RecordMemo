package com.example.tailin.privatepage.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 所有的Fragment继承自此BaseFragment
 * <p>
 * 如果采用ViewPager+Fragment，需要让Fragment进行缓存，那么必须对ViewPager进行缓存设置
 * viewPager.setOffscreenPageLimit(5);
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private boolean isVisible = false; //
    private boolean isInitView = false; //是否完成初始view标记
    private boolean isFirstLoad = true;
    private View rootView; //根视图
    private SparseArray<View> mViews; //查找控件的缓存，达到优化

    //这个方法当切换Fragment时会调用，会返回当前Fragment是否用户可见
    //setUserVisibleHint是在onCreateView之前调用的，
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViews = new SparseArray<>();
        rootView = inflater.inflate(getLayoutId(), container, false);//得到根视图
        initView(); //初始化控件
        isInitView = true;//view初始化完成设置为true
        lazyLoad();//懒加载
        return rootView;

    }

    //懒加载数据（不会缓存相邻的页面）
    private void lazyLoad() {
        //如果不是第一次加载，不是初始化view，不是不可见，则不加载数据
        if (!isFirstLoad || !isInitView || !isVisible)
            return;
        //否则，加载数据
        initListener();
        initData();
        //已经不是第一次加载了
        isFirstLoad = false;
    }


    /**
     * 设置根部局
     *
     * @return 根部局id
     */
    protected abstract int getLayoutId();

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
            view = (E) rootView.findViewById(viewId);
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
