package com.djs.one.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djs.one.R;
import com.djs.one.activity.OrderDetailActivity;
import com.djs.one.adapter.OrderAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.MyOrdersBean;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.util.SysUtils;
import com.djs.one.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedOrderFragment extends BaseFragment implements OrderAdapter.onOrderItemBtnClickListener, OnRefreshListener, OnLoadMoreListener {
    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView listView;
    private List<MyOrdersBean.DataBean.ListBean> orders = new ArrayList<>();
    private OrderAdapter adapter;
    private int page = 1;


    public CompletedOrderFragment() {
        // Required empty public constructor
    }

    public static CompletedOrderFragment getInstance(){
        return new CompletedOrderFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_completed_order, container, false);
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

        swipeToLoadLayout = paramView.findViewById(R.id.swipeToLoadLayout);
        listView = paramView.findViewById(R.id.swipe_target);

        swipeToLoadLayout.setRefreshEnabled(false);
        swipeToLoadLayout.setLoadMoreEnabled(false);

        adapter = new OrderAdapter(getActivity(),orders);
        adapter.setOnOrderItemBtnClickListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        getOrders();
    }

    private void getOrders(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<MyOrdersBean> products = api.myOrders(TokenManager.getInstance().getLoginToken().getData().getToken(),"" + Constant.COMPLETED,"20","" + page);
        products.enqueue(new Callback<MyOrdersBean>() {
            @Override
            public void onResponse(Call<MyOrdersBean> call, Response<MyOrdersBean> response) {

                try {
                    if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                    if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);

                    MyOrdersBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        if (productBean.getData() == null) return;
                        if (productBean.getData().getList() == null) return;
                        if (productBean.getData().getList().size() > 0){
                            orders.clear();
                            orders.addAll(productBean.getData().getList());
                            adapter.notifyDataSetChanged();
                        }
                    }else {
                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<MyOrdersBean> call, Throwable t) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);
                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onItemLayoutClick(View v) {
        int position = (Integer)v.getTag();
        Log.e(TAG, "xxxxxxxx onItemClick: " + position );
        SysUtils.startActivity(getActivity(),OrderDetailActivity.class);
    }

    @Override
    public void onLeftBtnClick(View v) {
        int position = (Integer)v.getTag();
        Log.e(TAG, "onLeftBtnClick: left" + position );
    }

    @Override
    public void onRightBtnClick(View v) {
        int position = (Integer)v.getTag();
        Log.e(TAG, "onLeftBtnClick: right" + position );
    }

    @Override
    public void onRefresh() {
        page = 1;
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getOrders();
            }
        }, 10);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = page + 1;
                getOrders();
            }
        }, 10);
    }
}
