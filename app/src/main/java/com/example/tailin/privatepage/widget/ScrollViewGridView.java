package com.example.tailin.privatepage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 重写GridView{@link ScrollViewGridView}
 * 适用于scrollview嵌套GridView的
 */
public class ScrollViewGridView extends GridView {
    public ScrollViewGridView(Context context) {
        super(context);
    }

    public ScrollViewGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
