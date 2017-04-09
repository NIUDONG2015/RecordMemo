package com.example.tailin.privatepage.demo.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tailin.privatepage.R;
import com.example.tailin.privatepage.widget.flowlayout.FlowLayout;
import com.example.tailin.privatepage.widget.flowlayout.TagAdapter;
import com.example.tailin.privatepage.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 流式布局
 * 与{@link FlowLayoutActivity}相关的布局
 * R.layout.activity_flow_layout（根部局），
 * R.layout.item_text_flowlayout（子布局的view），
 * drawable/selector_flowlayout（子布局view选择器
 * @drawable/rectangle_yuanjiao_white_orange
 * @drawable/rectangle_yuanjiao_white
 * <p/>
 * 准备工作：
 * 1.流式布局数据源，可以是任意类型
 * 2.流式布局自定义布局样式，比如R.layout.item_text_flowlayout
 * 3.设置适配器
 */
public class FlowLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        initTagFlowLayout();
    }

    //初始化流式布局
    private void initTagFlowLayout() {
        TagFlowLayout tagFlowLayout = (TagFlowLayout) findViewById(R.id.view_flowlayout);
        //数据源
        List<String> tagList = new ArrayList<>();
        for (int i = 0; i < 44; i++) {
            tagList.add("流式布局" + i);
        }
        //适配器
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        TagAdapter<String> tagAdapter = new TagAdapter<String>(tagList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {//每个流式view的样式
                //给每个流式布局子view设置内容
                TextView textView = (TextView) layoutInflater.inflate(R.layout.item_text_flowlayout, parent, false);
                textView.setText(s);//设置内容
                return textView;
            }
        };
        //设置适配器
        tagFlowLayout.setAdapter(tagAdapter);
    }
}
