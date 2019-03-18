package com.com.one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import com.com.one.R;
import com.com.one.api.API;
import com.com.one.api.URL;
import com.com.one.bean.Address;
import com.com.one.bean.SuccessfulMode;
import com.com.one.constant.Constant;
import com.com.one.manager.TokenManager;
import com.com.one.util.SysUtils;
import com.com.one.util.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAddressActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private TitleBar titleBar;
    private EditText userName;
    private EditText userPhone;
    private EditText locate;
    private EditText detailAddress;
    private CheckBox checkbox;
    private Address.DataBean.AddressBean address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        titleBar = findViewById(R.id.add_address_title_bar);
        userName = findViewById(R.id.address_name_edit_text);
        userPhone = findViewById(R.id.add_address_user_phone_edit_text);
        locate = findViewById(R.id.add_address_user_address_edit_text);
        detailAddress = findViewById(R.id.add_address_user_address_detail_edit_text);
        checkbox = findViewById(R.id.add_address_user_default_checkbox);

        checkbox.setOnCheckedChangeListener(this);
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

        Intent intent = getIntent();
        address = (Address.DataBean.AddressBean) intent.getSerializableExtra("address");
        if (address != null && !TextUtils.isEmpty(address.getName())){//点击编辑进来的
            userName.setText(address.getName());
            userPhone.setText(address.getMobile());
            locate.setText(address.getArea());
            detailAddress.setText(address.getAddress());
            checkbox.setChecked(address.getDefaultX() == 1 ? true : false);
        }else {//点击新增收货地址进来的

        }
    }

    public void onSetUserDefaultAddressLayoutClick(View view){

    }

    public void onAddAddressConfirmButtonClick(View view){
        onConfirmBtnClick();
    }

    public void onConfirmBtnClick(){
        //检查填写的值

        try{
            String name = userName.getText().toString();
            String phone = userPhone.getText().toString();
            String locateStr = locate.getText().toString();
            String detailAddressStr = detailAddress.getText().toString();

            if (TextUtils.isEmpty(name)){
                ToastUtils.showToast(this,"请输入收货人");
                return;
            }

            if (TextUtils.isDigitsOnly(name)){
                ToastUtils.showToast(this,"请输入正确的收货人");
                return;
            }

            if (TextUtils.isEmpty(phone)){
                ToastUtils.showToast(this,"请输入手机号码");
                return;
            }

            if (!SysUtils.isPhoneNumber(phone)){
                ToastUtils.showToast(this,"请输入正确的手机号码");
                return;
            }

            if (TextUtils.isEmpty(locateStr)){
                ToastUtils.showToast(this,"请输入所在地区");
                return;
            }

            if (TextUtils.isEmpty(detailAddressStr)){
                ToastUtils.showToast(this,"请输入详细地址");
                return;
            }

            updateAddress(name, phone, locateStr, detailAddressStr,checkbox.isChecked() ? "1" : "2");

        }catch (Exception e){
        }
    }

    private void updateAddress(String name, String phone, String locate, String detail, String isDefaultAddress){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.createAddress(TokenManager.getInstance().getLoginToken().getData().getToken(),
                name,phone,locate,detail,isDefaultAddress);
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode userProfile = response.body();

                    if (Constant.SUCCESSFUL == userProfile.getCode()){
                        //if update successful
                        mFinish();
                    }else {
                        ToastUtils.showToast(AddAddressActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(AddAddressActivity.this, t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
