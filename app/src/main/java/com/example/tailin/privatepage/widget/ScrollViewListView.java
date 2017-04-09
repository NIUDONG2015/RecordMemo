package com.example.tailin.privatepage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 重写ListView{@link ScrollViewListView}
 * scrollview嵌套ListView
 */
public class ScrollViewListView extends ListView {

    public ScrollViewListView(Context context) {
        super(context);
    }

    public ScrollViewListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
