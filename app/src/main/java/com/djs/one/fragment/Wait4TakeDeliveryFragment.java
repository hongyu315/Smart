package com.djs.one.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djs.one.R;
import com.djs.one.activity.LogisticsActivity;
import com.djs.one.activity.OrderDetailActivity;
import com.djs.one.adapter.OrderAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.MyOrdersBean;
import com.djs.one.bean.SuccessfulMode;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.util.SysUtils;

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
public class Wait4TakeDeliveryFragment extends BaseFragment implements OrderAdapter.onOrderItemBtnClickListener, AdapterView.OnItemClickListener, OnRefreshListener, OnLoadMoreListener {
    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView listView;
    private List<MyOrdersBean.DataBean.ListBean> orders = new ArrayList<>();
    private OrderAdapter adapter = new OrderAdapter(getActivity(),orders);
    private int page = 1;


    public Wait4TakeDeliveryFragment() {
        // Required empty public constructor
    }

    public static Wait4TakeDeliveryFragment getInstance() {
        return new Wait4TakeDeliveryFragment();
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

        adapter = new OrderAdapter(getActivity(), orders);
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

    private void getOrders() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<MyOrdersBean> products = api.myOrders(TokenManager.getInstance().getLoginToken().getData().getToken(),
                "" + Constant.WAIT4TAKEDELIVER,
                "",
                "20",
                "" + page);
        products.enqueue(new Callback<MyOrdersBean>() {
            @Override
            public void onResponse(Call<MyOrdersBean> call, Response<MyOrdersBean> response) {

                try {
                    if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                    if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);

                    MyOrdersBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        if (productBean.getData() == null) return;
                        if (productBean.getData().getList() == null) return;
                        if (productBean.getData().getList().size() > 0) {
                            orders.clear();
                            orders.addAll(productBean.getData().getList());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
//                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<MyOrdersBean> call, Throwable t) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);
//                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onItemLayoutClick(View v) {
        try {
            int position = (Integer) v.getTag();
            Log.e(TAG, "xxxxxxxx onItemClick: " + position);
            String tradeNo = orders.get(position).getTrade_no();
            if (!TextUtils.isEmpty(tradeNo)) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                intent.putExtra("trade_no", tradeNo);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onLeftBtnClick(View v) {
        int position = (Integer) v.getTag();
        try {
            Log.e(TAG, "onLeftBtnClick: left" + position);
            String tradeNo = orders.get(position).getTrade_no();
            if (!TextUtils.isEmpty(tradeNo)) {
                Intent intent = new Intent(getActivity(), LogisticsActivity.class);
                intent.putExtra("trade_no", tradeNo);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onRightBtnClick(View v) {
        final int position = (Integer) v.getTag();
        String tradeNo = orders.get(position).getTrade_no();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.receive(TokenManager.getInstance().getLoginToken().getData().getToken(), tradeNo);
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        orders.remove(position);
                        adapter.notifyDataSetChanged();
                    } else {
//                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick: " + position);
        SysUtils.startActivity(getActivity(), OrderDetailActivity.class);
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
