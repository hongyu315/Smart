package hongyu315.com.smart2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.api.API;
import hongyu315.com.smart2.api.URL;
import hongyu315.com.smart2.bean.ImageVerifyCode;
import hongyu315.com.smart2.bean.LoginToken;
import hongyu315.com.smart2.bean.PhoneCheckCode;
import hongyu315.com.smart2.bean.UserProfile;
import hongyu315.com.smart2.constant.Constant;
import hongyu315.com.smart2.manager.TokenManager;
import hongyu315.com.smart2.manager.UserManager;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.util.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity {

    private EditText phoneEditText;
    private EditText imageCodeEditText;
    private ImageView imageVerifyCodeImg;
    private EditText msgCodeEditText;
    private Button getMsgCodeButton;
    private CheckBox licenceCheckBox;

    //图形验证码
    private ImageVerifyCode mImageVerifyCode;

    //手机短信验证码
    private PhoneCheckCode mPhoneCheckCode;
    private String pageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        try {
            Intent intent = getIntent();
            pageName = intent.getStringExtra(Constant.PAGE);
        }catch (Exception e){
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getImageVerifyCode();
    }

    @Override
    protected void findViews() {
        super.findViews();
        phoneEditText = findViewById(R.id.phone_edit_text);
        imageCodeEditText = findViewById(R.id.image_verifier_edit_text);
        msgCodeEditText = findViewById(R.id.msg_verifier_edit_text);
        getMsgCodeButton = findViewById(R.id.get_verifier_code_btn);
        imageVerifyCodeImg = findViewById(R.id.image_verifier);
        licenceCheckBox = findViewById(R.id.licence_checkbox);

        imageVerifyCodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageVerifyCode();
            }
        });
    }

    //获取图形验证码
    private void getImageVerifyCode(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ImageVerifyCode> products = api.getImageVerifyCode();
        products.enqueue(new Callback<ImageVerifyCode>() {
            @Override
            public void onResponse(Call<ImageVerifyCode> call, Response<ImageVerifyCode> response) {

                try {
                    mImageVerifyCode = response.body();
                    String encodedString = response.body().getImg();
                    String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",") + 1);
                    byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                    Glide.with(LoginActivity.this).load(decodedBytes).crossFade().fitCenter().into(imageVerifyCodeImg);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ImageVerifyCode> call, Throwable t) {
            }
        });
    }

    //获取短信验证码
    private void getPhoneCheckCode(String phone, String imageCode){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
//        Call<PhoneCheckCode> products = api.sendPhoneCheckCode(phone,imageCode,mImageVerifyCode.getKey());
        Call<PhoneCheckCode> products = api.sendPhoneCheckCode("13721042453","hppc","$2y$10$4kNHwZViLp5cl3pl2UT5O.eDmeueGSvS9aKhi7ApvtBenSWdBJvmm");
        products.enqueue(new Callback<PhoneCheckCode>() {
            @Override
            public void onResponse(Call<PhoneCheckCode> call, Response<PhoneCheckCode> response) {

                try {
                    mPhoneCheckCode = response.body();
                    Log.e("xxx", "onResponse: " + mPhoneCheckCode.getData().getCode() );
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<PhoneCheckCode> call, Throwable t) {
            }
        });
    }

    public void onSendPhoneCheckCodeClick(View view){
        String phoneStr = phoneEditText.getText().toString();
        String imageCodeStr = imageCodeEditText.getText().toString();

        if (TextUtils.isEmpty(phoneStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入手机号");
            return;
        }

        if (!SysUtils.isPhoneNumber(phoneStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(imageCodeStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入图形验证码");
            return;
        }

        getPhoneCheckCode(phoneStr,imageCodeStr);

    }

    public void onLoginBtnClick(View view) {
        String phoneStr = phoneEditText.getText().toString();
        String imageCodeStr = imageCodeEditText.getText().toString();
        String msgCodeStr = msgCodeEditText.getText().toString();

        if (TextUtils.isEmpty(phoneStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入手机号");
            return;
        }

        if (!SysUtils.isPhoneNumber(phoneStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(imageCodeStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入图形验证码");
            return;
        }

        if (TextUtils.isEmpty(msgCodeStr)) {
            ToastUtils.showToast(LoginActivity.this, "请输入短信验证码");
            return;
        }

        requestLogin(phoneStr,msgCodeStr);

    }

    private void requestLogin(String mobile, String checkCode){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<LoginToken> products = api.login(mobile,checkCode,"1");
        products.enqueue(new Callback<LoginToken>() {
            @Override
            public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {

                try {
                    LoginToken loginToken = response.body();
                    if (Constant.SUCCESSFUL == loginToken.getCode()){
                        TokenManager.getInstance().setLoginToken(loginToken);
                        getUser();
                    }else {
                        ToastUtils.showToast(LoginActivity.this,loginToken.getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<LoginToken> call, Throwable t) {
                ToastUtils.showToast(LoginActivity.this, t.getLocalizedMessage());
            }
        });
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
                        jumpToWhereShouldGo();
//                        SysUtils.startActivity(LoginActivity.this, AccountManagerActivity.class);
                        finish();
                    }else {
                        ToastUtils.showToast(LoginActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                ToastUtils.showToast(LoginActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void jumpToWhereShouldGo(){
        if (TextUtils.isEmpty(pageName)) return;

        Intent intent3 = null;
        try {
            intent3 = new Intent();
            intent3.setClass(getApplicationContext(),Class.forName(pageName));
            startActivity(intent3);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onLoginCloseClick(View view) {
        mFinish();
    }
}
