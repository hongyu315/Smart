package hongyu315.com.smart2.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.OrderDetailActivity;
import hongyu315.com.smart2.adapter.OrderAdapter;
import hongyu315.com.smart2.bean.Order;
import hongyu315.com.smart2.constant.Constant;
import hongyu315.com.smart2.util.SysUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedOrderFragment extends BaseFragment implements OrderAdapter.onOrderItemBtnClickListener {
    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView listView;
    private List<Order> orders = new ArrayList<>();
    private OrderAdapter adapter;


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

        swipeToLoadLayout = (SwipeToLoadLayout) paramView.findViewById(R.id.swipeToLoadLayout);
        listView = (ListView) paramView.findViewById(R.id.swipe_target);

        swipeToLoadLayout.setRefreshEnabled(false);
        swipeToLoadLayout.setLoadMoreEnabled(false);

        adapter = new OrderAdapter(getActivity(),orders);
        adapter.setOnOrderItemBtnClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.order_kind = Constant.COMPLETED;
            order.setOrder_time("2019-01-11 20:10:10");
            order.setOrder_status("已完成");

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
    }

    @Override
    public void onRightBtnClick(View v) {
        int position = (Integer)v.getTag();
        Log.e(TAG, "onLeftBtnClick: right" + position );
    }
}
