package com.djs.one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.djs.one.R;
import com.djs.one.adapter.MyPagerAdapter;
import com.djs.one.fragment.CompletedOrderFragment;
import com.djs.one.fragment.Wait4DeliverFragment;
import com.djs.one.fragment.Wait4PayFragment;
import com.djs.one.fragment.Wait4TakeDeliveryFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;

import static com.djs.one.constant.Constant.From_Order;

public class MyOrderActivity extends BaseActivity {

    private TitleBar topTitleBarView;
    private SlidingTabLayout commonTabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    private String[] mTitles = { "已完成", "待付款", "待发货","待收货" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        //标题栏
        topTitleBarView = findViewById(R.id.my_order_title_bar);
        topTitleBarView.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                mFinish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this,SearchActivity.class);
                intent.putExtra("index",From_Order);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });

        //tab滑动栏
        commonTabLayout = findViewById(R.id.slidingTabLayout);
        viewPager = findViewById(R.id.my_order_view_pager);

        mFragments.add(CompletedOrderFragment.getInstance());
        mFragments.add(Wait4PayFragment.getInstance());
        mFragments.add(Wait4DeliverFragment.getInstance());
        mFragments.add(Wait4TakeDeliveryFragment.getInstance());

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments);
        viewPager.setAdapter(mAdapter);
        commonTabLayout.setViewPager(viewPager,mTitles);
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        commonTabLayout.setCurrentTab(index);
    }

}
