package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.MessageAdapter;
import hongyu315.com.smart2.bean.Message;
import hongyu315.com.smart2.constant.Constant;
import hongyu315.com.smart2.view.TopTitleBarView;

public class FavoriteActivity extends BaseActivity implements View.OnClickListener, MessageAdapter.InnerItemOnclickListener {

    private TopTitleBarView topTitleBarView;
    private SwipeToLoadLayout swipeToLoadLayout;

    private ListView favoriteList;
    private MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        topTitleBarView = (TopTitleBarView) findViewById(R.id.topTitleBarView);
        topTitleBarView.mTvRightSearch.setVisibility(View.GONE);
        topTitleBarView.mTvLeftImageMenu.setVisibility(View.VISIBLE);
        topTitleBarView.mTvLeftImageMenu.setImageResource(R.mipmap.back);
        topTitleBarView.mTvLeftImageMenu.setOnClickListener(this);
        topTitleBarView.mTvTitle.setText(R.string.favorite);

        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setLoadMoreEnabled(false);
        swipeToLoadLayout.setRefreshEnabled(false);
        favoriteList = (ListView) findViewById(R.id.swipe_target);

        adapter = new MessageAdapter(this,messages);
        adapter.setOnInnerItemOnClickListener(this);
        favoriteList.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < 5; i++) {
            String url = "http://pic.downcc.com/upload/2018-3/2018316144426875970.jpg";
            String status = "男士印花手上的的方法库";
            String name = "小王子" + i;
            String time = "￥960.00";
            String num = "L 睡衣180";
            Message msg = new Message();
            msg.iconUrl = url;
            msg.setType(Constant.FAVORITE);
            msg.orderStatus = status;
            msg.orderName = name;
            msg.orderTime = time;
            msg.orderNum = num;
            messages.add(msg);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.top_title_bar_menu:
                mFinish();
                break;
            default:
                break;
        }
    }

    @Override
    public void itemClick(View v) {
        int position = (Integer) v.getTag();
        switch(v.getId()){
            case R.id.delete_product:
                messages.remove(position);
                adapter.notifyDataSetChanged();
                break;
            case R.id.add_to_shopping_car:

                break;
        }
    }
}
