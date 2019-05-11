package com.djs.one.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.djs.one.R;
import com.djs.one.activity.OrderDetailActivity;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.CreateOrderBean;
import com.djs.one.constant.Constant;
import com.djs.one.fragment.ShoppingFragment;
import com.djs.one.manager.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingUtils{

    public static void createOrder(String skus, final Activity context){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<CreateOrderBean> products = api.createOrder(TokenManager.getInstance().getLoginToken().getData().getToken(),skus);
        products.enqueue(new Callback<CreateOrderBean>() {
            @Override
            public void onResponse(Call<CreateOrderBean> call, Response<CreateOrderBean> response) {

                try {
                    CreateOrderBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        if (productBean.getData() != null){
                            if (!TextUtils.isEmpty(productBean.getData().getTrade_no())){
                                Intent intent = new Intent(context, OrderDetailActivity.class);
                                intent.putExtra("trade_no",productBean.getData().getTrade_no());
                                context.startActivity(intent);
                                context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                                return;
                            }
                        }
                        ToastUtils.showToast(context,"创单失败");
                    }else {
                        ToastUtils.showToast(context,response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<CreateOrderBean> call, Throwable t) {
                ToastUtils.showToast(context, t.getLocalizedMessage());
            }
        });
    }

    public static String getSkus(String itemId, String skuId, String quantity) {
        return itemId + ":" + skuId +":" + quantity +";";
    }
}
