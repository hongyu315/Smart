package com.djs.one.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.djs.one.R;
import com.djs.one.adapter.FavoriteAdapter;
import com.djs.one.adapter.MessageAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.Product;
import com.djs.one.bean.ProductBean;
import com.djs.one.bean.SuccessfulModeBean;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.util.DensityUtil;
import com.djs.one.util.ToastUtils;
import com.djs.one.view.TopTitleBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteActivity extends BaseActivity implements View.OnClickListener, MessageAdapter.InnerItemOnclickListener {

    private TopTitleBarView topTitleBarView;
    private SwipeToLoadLayout swipeToLoadLayout;

    private SwipeMenuListView favoriteList;
    private FavoriteAdapter adapter;
    private List<Product> productList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        topTitleBarView = findViewById(R.id.topTitleBarView);
        topTitleBarView.mTvRightSearch.setVisibility(View.GONE);
        topTitleBarView.mTvLeftImageMenu.setVisibility(View.VISIBLE);
        topTitleBarView.mTvLeftImageMenu.setImageResource(R.mipmap.back);
        topTitleBarView.mTvLeftImageMenu.setOnClickListener(this);
        topTitleBarView.mTvTitle.setText(R.string.favorite);

        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setLoadMoreEnabled(false);
        swipeToLoadLayout.setRefreshEnabled(false);
        favoriteList = findViewById(R.id.swipe_target);

        adapter = new FavoriteAdapter(this,productList);
//        adapter.setOnInnerItemOnClickListener(this);
        favoriteList.setAdapter(adapter);
        favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = productList.get(position);
                String itemId = product.getItem_id() + "";

                Intent intent = new Intent(FavoriteActivity.this,ProductDetailActivity.class);
                intent.putExtra("itemId",itemId);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(DensityUtil.dp2px(FavoriteActivity.this,80));
                // set a icon
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
//                deleteItem.setIcon(R.mipmap.right_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        favoriteList.setMenuCreator(creator);

        favoriteList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                deleteFavorite(position);
                return false;
            }
        });
    }

    private void deleteFavorite(final int position){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulModeBean> products = api.collect(TokenManager.getInstance().getLoginToken().getData().getToken(),productList.get(position).getItem_id() + "");
        products.enqueue(new Callback<SuccessfulModeBean>() {
            @Override
            public void onResponse(Call<SuccessfulModeBean> call, Response<SuccessfulModeBean> response) {

                try {
                    SuccessfulModeBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        productList.remove(position);
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(FavoriteActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SuccessfulModeBean> call, Throwable t) {
                ToastUtils.showToast(FavoriteActivity.this, t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getFavoriteList();
    }

    private void getFavoriteList(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductBean> products = api.getMyCollects(TokenManager.getInstance().getLoginToken().getData().getToken(),"100","1");
        products.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {

                try {
                    ProductBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        productList.addAll(productBean.getData().getList());
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(FavoriteActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {
                ToastUtils.showToast(FavoriteActivity.this, t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.top_title_bar_menu:
                mFinish();
                break;
            default:
                break;
        }
    }

    @Override
    public void itemClick(View v) {
        int position = (Integer) v.getTag();
        switch(v.getId()){
            case R.id.delete_product:
//                messages.remove(position);
//                adapter.notifyDataSetChanged();
                break;
            case R.id.add_to_shopping_car:

                break;
        }
    }
}
