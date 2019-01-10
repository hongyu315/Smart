package hongyu315.com.smart2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.MyPagerAdapter;
import hongyu315.com.smart2.fragment.CompletedOrderFragment;
import hongyu315.com.smart2.fragment.Wait4DeliverFragment;
import hongyu315.com.smart2.fragment.Wait4PayFragment;
import hongyu315.com.smart2.fragment.Wait4TakeDeliveryFragment;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.TopTitleBarView;

public class MyOrderActivity extends BaseActivity implements TopTitleBarView.TopTitleBarClickListener {

    private TopTitleBarView topTitleBarView;
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
        topTitleBarView = (TopTitleBarView)findViewById(R.id.topTitleBarView);
        topTitleBarView.mTvLeftImageMenu.setVisibility(View.VISIBLE);
        topTitleBarView.mTvLeftImageMenu.setImageResource(R.mipmap.back);
        topTitleBarView.mTvTitle.setText(R.string.my_order);
        topTitleBarView.setTopBarClickListener(this);

        //tab滑动栏
        commonTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabLayout);
        viewPager = (ViewPager) findViewById(R.id.my_order_view_pager);

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

    @Override
    public void onTopTitleBarLeftIconClickListener() {
        mFinish();
    }

    @Override
    public void onTopTitleBarRightIconClickListener() {
        SysUtils.startActivity(this,SearchActivity.class);
    }
}
