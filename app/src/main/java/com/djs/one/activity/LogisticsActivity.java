package com.djs.one.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.djs.one.R;
import com.djs.one.adapter.LogisticsAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.LogisticsJson;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogisticsActivity extends BaseActivity {

    private TitleBar topTitleBarView;
    private RecyclerView recyclerView;
    private TextView logisticsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        logisticsText = findViewById(R.id.logistics_txt);
        topTitleBarView = findViewById(R.id.logistics_topTitleBarView);
        topTitleBarView.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                mFinish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });
        recyclerView = findViewById(R.id.logistics_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initData() {
        super.initData();

        getAddress();
    }

    private void getAddress(){
        final String tradeNo = getIntent().getStringExtra("trade_no");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<LogisticsJson> products = api.logistics(TokenManager.getInstance().getLoginToken().getData().getToken(), tradeNo);
        products.enqueue(new Callback<LogisticsJson>() {
            @Override
            public void onResponse(Call<LogisticsJson> call, Response<LogisticsJson> response) {

                try {
                    LogisticsJson productBean = response.body();
                    List<LogisticsJson.DataBean.LogisticsBean> logistics = new ArrayList<>();

                    LogisticsJson.DataBean.LogisticsBean  lB = new LogisticsJson.DataBean.LogisticsBean();
                    lB.setId(18);
                    lB.setContext("[收货地址]" + productBean.getData().getArea() + productBean.getData().getAddress() + productBean.getData().getName());
                    logistics.add(lB);
                    logistics.addAll(productBean.getData().getLogistics());
                    productBean.getData().setLogistics(logistics);

                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        logisticsText.setText(productBean.getData().getLogistic_com() + " " + productBean.getData().getWaybill_no());
                        LogisticsAdapter logisticsAdapter = new LogisticsAdapter(LogisticsActivity.this, productBean);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(LogisticsActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setAdapter(logisticsAdapter);
                    } else {
//                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<LogisticsJson> call, Throwable t) {
//                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }
}
