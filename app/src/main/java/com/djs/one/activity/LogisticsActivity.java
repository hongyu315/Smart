package com.com.one.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import com.com.one.R;
import com.com.one.adapter.LogisticsAdapter;
import com.com.one.bean.LogisticsJson;

public class LogisticsActivity extends BaseActivity {

    private TitleBar topTitleBarView;
    private RecyclerView recyclerView;

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

    }

    @Override
    protected void initData() {
        super.initData();

        String json = "{\n" +
                "\t\"code\": 1,\n" +
                "\t\"message\": \"Success\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"id\": \"1\",\n" +
                "\t\t\"state\": \"3\",\n" +
                "\t\t\"num\": \"71265042088396\",\n" +
                "\t\t\"time\": \"2018-03-11 12:55:09\",\n" +
                "\t\t\"com_name\": \"汇通\",\n" +
                "\t\t\"list\": [{\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"time\": \"2018-03-02 08:46:40\",\n" +
                "\t\t\t\t\"context\": \"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收\"\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";
        LogisticsJson logisticsJson = new Gson().fromJson(json, LogisticsJson.class);
        LogisticsAdapter logisticsAdapter = new LogisticsAdapter(LogisticsActivity.this, logisticsJson);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(logisticsAdapter);
    }
}
