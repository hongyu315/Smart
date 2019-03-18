package com.com.one.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import com.com.one.R;
import com.com.one.activity.LogisticsActivity;
import com.com.one.activity.OrderDetailActivity;
import com.com.one.adapter.OrderAdapter;
import com.com.one.bean.Order;
import com.com.one.constant.Constant;
import com.com.one.util.SysUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Wait4TakeDeliveryFragment extends BaseFragment implements OrderAdapter.onOrderItemBtnClickListener, AdapterView.OnItemClickListener {
    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView listView;
    private List<Order> orders = new ArrayList<>();
    private OrderAdapter adapter;


    public Wait4TakeDeliveryFragment() {
        // Required empty public constructor
    }

    public static Wait4TakeDeliveryFragment getInstance(){
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

        swipeToLoadLayout.setRefreshEnabled(false);
        swipeToLoadLayout.setLoadMoreEnabled(false);

        adapter = new OrderAdapter(getActivity(),orders);
        adapter.setOnOrderItemBtnClickListener(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.order_kind = Constant.WAIT4TAKEDELIVER;

            order.setOrder_time("2019-01-11 20:10:10");
            order.setOrder_status("待收货");

            order.setProduct_icon("http://img3.imgtn.bdimg.com/it/u=1023765318,2535906339&fm=26&gp=0.jpg");
            order.setProduct_name("樱花小睡袍");
            order.setProduct_type("蓝色小丸子");
            order.setProduct_size("尺寸：L 睡衣180");
            order.setProduct_price("960");
            order.setProduct_num("1");

            orders.add(order);
        }

        adapter.notifyDataSetChanged();
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

        Intent intent = new Intent(getActivity(),LogisticsActivity.class);
//        intent.putExtra()
        getActivity().startActivity(intent);
    }

    @Override
    public void onRightBtnClick(View v) {
        int position = (Integer)v.getTag();
        SysUtils.startActivity(getActivity(),OrderDetailActivity.class);
        Log.e(TAG, "onLeftBtnClick: right" + position );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick: " + position );
        SysUtils.startActivity(getActivity(),OrderDetailActivity.class);
    }
}
