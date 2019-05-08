package com.djs.one.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djs.one.R;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.SuccessfulMode;
import com.djs.one.bean.UserProfile;
import com.djs.one.constant.Constant;
import com.djs.one.manager.CacheManager;
import com.djs.one.manager.TokenManager;
import com.djs.one.manager.UserManager;
import com.djs.one.util.SysUtils;
import com.djs.one.util.ToastUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountManagerActivity extends BaseActivity implements OnTitleBarListener {

    private TitleBar mTitleBar;
    private ImageView userIcon;
    private TextView userName;
    private TextView version;
    private TextView cacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        mTitleBar = findViewById(R.id.account_manager_title_bar);

        userIcon = findViewById(R.id.user_icon);
        userName = findViewById(R.id.user_name);
        version = findViewById(R.id.version);
        cacheSize = findViewById(R.id.cache_size);

        mTitleBar.setOnTitleBarListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    private void getUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<UserProfile> products = api.getUser(TokenManager.getInstance().getLoginToken().getData().getToken());
        products.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

                try {
                    UserProfile userProfile = response.body();

                    if (Constant.SUCCESSFUL == userProfile.getCode()){
                        UserManager.getInstance().setUser(userProfile);
                        String nickName = (String) userProfile.getData().getNickname();
                        String phone = (String) userProfile.getData().getMobile();
                        userName.setText(TextUtils.isEmpty(nickName) ? phone : nickName);
                        String userIconUrl = (String) userProfile.getData().getAvatar();
                        if (!TextUtils.isEmpty(userIconUrl)){
//                            Glide.with(AccountManagerActivity.this).load("https://www.baidu.com/img/bdlogo.png").transform(new GlideRoundTransform(AccountManagerActivity.this)).into(userIcon);
                            Glide.with(AccountManagerActivity.this).load(userIconUrl).into(userIcon);
                        }
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        try {
            String nickName = (String) UserManager.getInstance().getUser().getData().getNickname();
            String phone = (String) UserManager.getInstance().getUser().getData().getMobile();
            userName.setText(TextUtils.isEmpty(nickName) ? phone : nickName);
            String userIconUrl = (String) UserManager.getInstance().getUser().getData().getAvatar();
            if (!TextUtils.isEmpty(userIconUrl)){
                Glide.with(this).load(userIconUrl).into(userIcon);
            }
            version.setText(getResources().getString(R.string.version));
            cacheSize.setText(CacheManager.getInstance().getTotalCacheSize(AccountManagerActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUserInfoLayoutClick(View view){
        SysUtils.startActivity(this,UserInfoActivity.class);
    }

    public void onAddressManagerLayoutClick(View view){
        SysUtils.startActivity(this,AddressManagerActivity.class);
    }

    public void onAboutLayoutClick(View view){
        SysUtils.startActivity(this,AboutActivity.class);
    }

    public void onClearCacheLayoutClick(View view){
        try {
            CacheManager.getInstance().clearAllCache(this);
            cacheSize.setText(CacheManager.getInstance().getTotalCacheSize(AccountManagerActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLogoutButtonClick(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.logout(TokenManager.getInstance().getLoginToken().getData().getToken());
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode logoutSuccessful = response.body();

                    if (Constant.SUCCESSFUL == logoutSuccessful.getCode()){
                        UserManager.getInstance().logout();
                        finish();
                    }else {
                        ToastUtils.showToast(AccountManagerActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(AccountManagerActivity.this, t.getLocalizedMessage());
            }
        });
    }

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


}
