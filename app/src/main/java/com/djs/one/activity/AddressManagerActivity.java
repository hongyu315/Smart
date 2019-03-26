package com.djs.one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import com.djs.one.R;
import com.djs.one.adapter.AddressAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.Address;
import com.djs.one.bean.SuccessfulMode;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.util.SysUtils;
import com.djs.one.util.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressManagerActivity extends BaseActivity implements AddressAdapter.OnAddressItemClickListener {

    private TitleBar titleBar;
    private ListView listView;
    private AddressAdapter adapter;
    private boolean getAddressSuccessful = false;
    private List<Address.DataBean.AddressBean> addressList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);

        findViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try{
            if (!getAddressSuccessful){
                initData();
            }
        }catch (Exception e){
        }

    }

    @Override
    protected void findViews() {
        super.findViews();

        titleBar = findViewById(R.id.address_activity_title_bar);
        listView = findViewById(R.id.address_list);
        adapter = new AddressAdapter(this,addressList, this);
        listView.setAdapter(adapter);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
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

    }

    @Override
    protected void initData() {
        super.initData();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<Address> products = api.getMyAddresses(TokenManager.getInstance().getLoginToken().getData().getToken(),"1000");
        products.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {

                try {
                    Address userProfile = response.body();

                    if (Constant.SUCCESSFUL == userProfile.getCode()){
                        getAddressSuccessful = true;
                        addressList.addAll(userProfile.getData().getList());
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(AddressManagerActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                ToastUtils.showToast(AddressManagerActivity.this, t.getLocalizedMessage());
            }
        });

    }

    public void onAddNewAddressLayoutClick(View view){
        SysUtils.startActivity(this,AddAddressActivity.class);
    }

    @Override
    public void onDefaultAddressClick(View view, boolean isChecked) {
        int position = (int) view.getTag();

        Address.DataBean.AddressBean addressBean = addressList.get(position);
        //如果已经是默认地址的，则不能再次点击本身进行取消，只能通过设置其他地址为默认地址后生效；
        if (addressBean.getDefaultX() == 1){
            return;
        }

        updateAddress(addressBean);
    }

    private void updateAddress(final Address.DataBean.AddressBean addressBean){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.updateAddress(TokenManager.getInstance().getLoginToken().getData().getToken(),
                addressBean.getId() + "",
                addressBean.getName(),
                addressBean.getMobile(),
                addressBean.getArea(),
                addressBean.getAddress(),
                "1");
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode userProfile = response.body();

                    if (Constant.SUCCESSFUL == userProfile.getCode()){

                        for (Address.DataBean.AddressBean address:addressList) {
                            address.setDefaultX(2);
                        }

                        addressBean.setDefaultX(1);
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(AddressManagerActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(AddressManagerActivity.this, t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void onEditAddressClick(View view) {
        int position = (int) view.getTag();
        Intent intent = new Intent(AddressManagerActivity.this,AddAddressActivity.class);
        intent.putExtra("address",addressList.get(position));
        startActivity(intent);
    }

    @Override
    public void onDeleteAddressClick(View view) {
        int position = (int) view.getTag();
        deleteAddress(position,addressList.get(position).getId() + "");
    }

    private void deleteAddress(final int position, String addressID){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.deleteAddress(TokenManager.getInstance().getLoginToken().getData().getToken(),addressID);
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode userProfile = response.body();

                    if (Constant.SUCCESSFUL == userProfile.getCode()){
                        addressList.remove(position);
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(AddressManagerActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(AddressManagerActivity.this, t.getLocalizedMessage());
            }
        });
    }
}
