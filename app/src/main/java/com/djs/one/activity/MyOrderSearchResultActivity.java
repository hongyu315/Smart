package com.djs.one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djs.one.R;
import com.djs.one.adapter.OrderAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.MyOrdersBean;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.manager.UserManager;
import com.djs.one.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.djs.one.constant.Constant.From_Order;

public class MyOrderSearchResultActivity extends BaseActivity implements View.OnClickListener, OrderAdapter.onOrderItemBtnClickListener, OnRefreshListener, OnLoadMoreListener {

    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView listView;

    private List<MyOrdersBean.DataBean.ListBean> orders = new ArrayList<>();
    private OrderAdapter adapter;// = new OrderAdapter(MyOrderSearchResultActivity.this,orders);

    private int page = 1;

    EditText mEditText;

    int index;
    String keyWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        findViews();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();

        try{
            if (!UserManager.getInstance().isLogin()) return;
            if (TextUtils.isEmpty(TokenManager.getInstance().getLoginToken().getData().getToken())) return;

            index = getIntent().getIntExtra("index",From_Order);
            keyWords = getIntent().getStringExtra("key");

            adapter = new OrderAdapter(MyOrderSearchResultActivity.this,orders);
            adapter.setOnOrderItemBtnClickListener(this);
            swipeToLoadLayout.setOnRefreshListener(this);
            swipeToLoadLayout.setOnLoadMoreListener(this);
            listView.setAdapter(adapter);

            getSearchResult();

        }catch (Exception e){

        }

    }

    @Override
    protected void findViews() {
        super.findViews();
        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        listView = findViewById(R.id.search_result_list);
        findViewById(R.id.back_icon).setOnClickListener(this);
        mEditText = findViewById(R.id.search_edit_text);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    keyWords = mEditText.getText().toString().trim();
                    getSearchResult();
                }
                return false;
            }
        });
    }

    private void getSearchResult(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<MyOrdersBean> products = api.myOrders(TokenManager.getInstance().getLoginToken().getData().getToken(),
                "" + Constant.COMPLETED,
                keyWords,
                "20",
                "" + page);
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
                        }else {
                            ToastUtils.showToast(MyOrderSearchResultActivity.this,"暂无相关订单信息");
                        }
                    }else {
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onItemLayoutClick(View v) {
        try {
            int position = (Integer)v.getTag();
            String tradeNo = orders.get(position).getTrade_no();
            if (!TextUtils.isEmpty(tradeNo)){
                Intent intent = new Intent(MyOrderSearchResultActivity.this, OrderDetailActivity.class);
                intent.putExtra("trade_no",tradeNo);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        }catch (Exception e){
        }
    }

    @Override
    public void onLeftBtnClick(View v) {

    }

    @Override
    public void onRightBtnClick(View v) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getSearchResult();
            }
        }, 10);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = page + 1;
                getSearchResult();
            }
        }, 10);
    }
    }

