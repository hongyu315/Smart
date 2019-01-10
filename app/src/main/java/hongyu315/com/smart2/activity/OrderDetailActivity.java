package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.view.TopTitleBarView;

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

        topTitleBarView = (TopTitleBarView) findViewById(R.id.order_detail_activity_title_bar);
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
