package com.djs.one.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djs.one.R;
import com.djs.one.activity.ProductDetailActivity;
import com.djs.one.adapter.MainBannerAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.HomeBanners;
import com.djs.one.bean.Product;
import com.djs.one.bean.ProductBean;
import com.djs.one.constant.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, AdapterView.OnItemClickListener {


    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView mListView;
    private MainBannerAdapter adapter;
    private HomeBanners banners = new HomeBanners();


    public MainFragment() {
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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
        mListView = paramView.findViewById(R.id.swipe_target);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

//        adapter = new MainBannerAdapter(getActivity(),banners);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(this);
    }


    @Override
    protected void initData() {
        super.initData();

        getHomeBanners();
    }

    private void getHomeBanners(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<HomeBanners> products = api.getHomeBanners();
        products.enqueue(new Callback<HomeBanners>() {
            @Override
            public void onResponse(Call<HomeBanners> call, Response<HomeBanners> response) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);

                try {
                    banners = response.body();

                    if (Constant.SUCCESSFUL == banners.getCode()){
                        if (banners.getData() != null && banners.getData().size() >0){
                            adapter = new MainBannerAdapter(getActivity(),banners.getData());
                            mListView.setAdapter(adapter);

                            mListView.setOnItemClickListener(MainFragment.this);
                        }

                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<HomeBanners> call, Throwable t) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getHomeBanners();
            }
        }, 10);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getHomeBanners();
            }
        }, 10);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HomeBanners.Data product = banners.getData().get(position);
        String itemId = product.getId() + "";

        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra("itemId",itemId);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }
}
