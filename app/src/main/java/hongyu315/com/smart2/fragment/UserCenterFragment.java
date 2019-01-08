package hongyu315.com.smart2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.UserCenterProductAdapter;
import hongyu315.com.smart2.bean.Product;
import hongyu315.com.smart2.view.HeaderGridView;

public class UserCenterFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    private List<Product> productList = new ArrayList();
    private UserCenterProductAdapter adapter;

    private SwipeToLoadLayout swipeToLoadLayout;


    private HeaderGridView gridView;

    private View headerLayout;
    private View footer;

    static int mLoadMoreNum = 0;
    static int mRefreshNum = 0;
    private Handler handler = new Handler()
    {
        public void handleMessage(Message paramAnonymousMessage)
        {
            switch (paramAnonymousMessage.what)
            {
                default:
//                    addHeaderView();
                    return;
            }
        }
    };

    public UserCenterFragment() {
    }

    public static UserCenterFragment getInstance() {
        return new UserCenterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_center, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);

        findViews(paramView);
        initData();
    }

    @Override
    protected void findViews(View paramView) {
        super.findViews(paramView);

        swipeToLoadLayout = (SwipeToLoadLayout) paramView.findViewById(R.id.swipeToLoadLayout);
        gridView = (HeaderGridView) paramView.findViewById(R.id.swipe_target);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerLayout = inflater.inflate(R.layout.home_fragment_header,null);
        gridView.addHeaderView(headerLayout);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
//        swipeToLoadLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeToLoadLayout.setRefreshing(true);
//            }
//        }, 100);

        adapter = new UserCenterProductAdapter(getActivity(),productList);

        gridView.setAdapter(adapter);

        footer = getLayoutInflater().inflate(R.layout.listview_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setLoadMoreFooterView(footer);
//        this.handler.sendEmptyMessageDelayed(0, 3000L);
    }

    @Override
    protected void initData() {
        super.initData();

        String url1 = "https://img.zcool.cn/community/01757d5a6a7557a8012134664d0391.jpg@2o.jpg";
        for (int i = 0; i < 4; i++) {
            Product product = new Product();
            product.setType("测试衍生商品 " + i);
            product.setUrl(url1);
            productList.add(product);
        }

        if (productList != null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        Log.e("HongYu", "onRefresh: 下拉刷新");
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
                mRefreshNum ++;
//                mAdapter.add("下拉刷新" + mLoadMoreNum);
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {
        Log.e("HongYu", "onRefresh: 上拉加载");
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
                mLoadMoreNum ++;
//                mAdapter.add("上拉加载更多" + mLoadMoreNum);
            }
        }, 3000);
    }
}
