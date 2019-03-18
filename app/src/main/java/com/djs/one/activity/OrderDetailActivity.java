package com.com.one.activity;

import android.os.Bundle;
import android.view.View;

import com.com.one.R;
import com.com.one.view.TopTitleBarView;

public class OrderDetailActivity extends BaseActivity {

    private TopTitleBarView topTitleBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        topTitleBarView = findViewById(R.id.order_detail_activity_title_bar);
//        topTitleBarView.mTvTitle.setText(getResources().getString(R.string.order_detail));
        topTitleBarView.mTvRightSearch.setVisibility(View.GONE);
        topTitleBarView.mTvLeftImageMenu.setVisibility(View.VISIBLE);
        topTitleBarView.mTvLeftImageMenu.setImageResource(R.mipmap.back);
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
