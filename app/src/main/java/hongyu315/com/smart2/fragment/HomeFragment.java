package hongyu315.com.smart2.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.ProductDetailActivity;
import hongyu315.com.smart2.activity.SearchActivity;
import hongyu315.com.smart2.adapter.ProductAdapter;
import hongyu315.com.smart2.bean.Product;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.TopTitleBarView;

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener, AdapterView.OnItemClickListener {

    protected Activity mActivity;
    private TopTitleBarView titleBarView;

    private List<Product> productList = new ArrayList();

    private SwipeToLoadLayout swipeToLoadLayout;

    private GridView gridView;
    private LinearLayout headerLayout;
    private ProductAdapter adapter;

    public HomeFragment() {
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        if (this.mActivity == null){
            return;
        }

        initViews(paramView);
        initData();
    }

    protected void initViews(View paramView){
        this.titleBarView = ((TopTitleBarView) paramView.findViewById(R.id.topTitleBarView));
        this.titleBarView.mTvLeftImageMenu.setOnClickListener(this);
        this.titleBarView.mTvRightSearch.setOnClickListener(this);

        swipeToLoadLayout = (SwipeToLoadLayout) paramView.findViewById(R.id.swipeToLoadLayout);
        gridView = (GridView) paramView.findViewById(R.id.swipe_target);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerLayout = (LinearLayout) inflater.inflate(R.layout.home_header_layout,null);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        gridView.setOnItemClickListener(this);

        adapter = new ProductAdapter(getActivity(),productList);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();

        for (int i = 0 ; i < 10 ; i++){
            Product product = new Product();
            product.setUrl("http://img3.imgtn.bdimg.com/it/u=2949159174,2649619291&fm=11&gp=0.jpg");
            productList.add(product);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                return;
            case R.id.top_title_bar_search:
                Log.e(TAG, "onClick: search" );
                SysUtils.startActivity(getActivity(),SearchActivity.class);
                return;
        }
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }


    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 3000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SysUtils.startActivity(getActivity(),ProductDetailActivity.class);
    }
}
