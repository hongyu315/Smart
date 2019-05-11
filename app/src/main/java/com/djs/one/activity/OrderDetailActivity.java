package com.djs.one.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.djs.one.R;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.Address;
import com.djs.one.bean.LogisticsJson;
import com.djs.one.bean.OrderDetailBean;
import com.djs.one.bean.PayCallBackBean;
import com.djs.one.bean.PayResult;
import com.djs.one.bean.SuccessfulMode;
import com.djs.one.bean.WXCallback;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.util.DensityUtil;
import com.djs.one.util.SPUtils;
import com.djs.one.util.ToastUtils;
import com.djs.one.util.WXPayUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OrderDetailActivity extends BaseActivity implements View.OnClickListener, IWXAPIEventHandler {

    private TitleBar topTitleBarView;
    TextView payMethodText;

    //剩余时间
    private TextView leftTime;

    //金额
    private TextView money;

    //订单编号
    private TextView orderNum;

    //物流用户姓名
    private TextView userName;

    //用户手机
    private TextView phone;

    //用户地址
    private  TextView address;

    String payArray[] = {"微信","支付宝"};
    int selectedPosition = 0;
    String trade_no = "";//订单号
    String addressId = "";

    OrderDetailBean productBean;
    private static final int SDK_PAY_FLAG = 1;

    //等待付款layout
    RelativeLayout wait4PayLayout;

    //取消订单、去支付layout
    LinearLayout payCancelPayLayout;
    //物流信息layout
    View deliverLayout;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showToast(OrderDetailActivity.this,"支付成功" );// + payResult);
                        onBackPressed();
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showToast(OrderDetailActivity.this,"支付失败" );//+ payResult);
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        wait4PayLayout = findViewById(R.id.wait_for_pay_layout);
        payCancelPayLayout = findViewById(R.id.pay_cancel_pay_layout);
        leftTime = findViewById(R.id.left_time);
        money = findViewById(R.id.order_detail_money);
        orderNum = findViewById(R.id.order_detail_num);
        userName = findViewById(R.id.customer_name);
        phone = findViewById(R.id.order_detail_phone);
        address = findViewById(R.id.order_detail_address);
        deliverLayout = findViewById(R.id.deliver_layout);
        findViewById(R.id.on_pay_method).setOnClickListener(this);
        findViewById(R.id.cancel_order).setOnClickListener(this);
        findViewById(R.id.more_address).setOnClickListener(this);

        payMethodText = (TextView) findViewById(R.id.order_detail_pay_method_txt);
        findViewById(R.id.order_detail_pay_method).setOnClickListener(this);
        topTitleBarView = findViewById(R.id.order_detail_activity_title_bar);
        topTitleBarView.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    private void initAddresseeInfo(Address.DataBean.AddressBean bean) {
        if (bean != null) {
            userName.setText(bean.getName());
            phone.setText(bean.getMobile());
            address.setText(bean.getAddress());
        }
    }

    private void initDeliverInfo() {
        deliverLayout.setVisibility(View.VISIBLE);
        final TextView deliver_name = findViewById(R.id.deliver_name);
        final TextView deliver_arrive_time = findViewById(R.id.deliver_arrive_time);
        final  TextView deliver_last_info = findViewById(R.id.deliver_last_info);
        final TextView deliver_last_update_time = findViewById(R.id.deliver_last_update_time);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<LogisticsJson> deliverInfo =  api.logistics(TokenManager.getInstance().getLoginToken().getData().getToken(), "1000");
        deliverInfo.enqueue(new Callback<LogisticsJson>() {
            @Override
            public void onResponse(Call<LogisticsJson> call, Response<LogisticsJson> response) {
                try{
                    LogisticsJson logisticsJson = response.body();
                    if (Constant.SUCCESSFUL == logisticsJson.getCode()){
                        deliver_name.setText(logisticsJson.getData().getLogistic_com());
                        if (logisticsJson.getData().getLogistics() != null && logisticsJson.getData().getLogistics().size() > 0) {
//                            deliver_arrive_time.setText(logisticsJson.getData().get);
                            deliver_last_info.setText(logisticsJson.getData().getLogistics().get(0).getContext());
                            deliver_last_update_time.setText(logisticsJson.getData().getLogistics().get(0).getTime());
                        }
                    }else {
                        ToastUtils.showToast(OrderDetailActivity.this,response.body().getMessage());
                    }
                }catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<LogisticsJson> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        trade_no = getIntent().getStringExtra("trade_no");

        requestOrderDetailFromTradeNo();
    }

    private void getAddressList(){
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
                        List<Address.DataBean.AddressBean> addressBeans = userProfile.getData().getList();
                        for (Address.DataBean.AddressBean bean : addressBeans){
                            if (bean.getDefaultX() == 1){
                                addressId = bean.getId() + "";
                            }
                        }
                    }else {
                        ToastUtils.showToast(OrderDetailActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                ToastUtils.showToast(OrderDetailActivity.this, t.getLocalizedMessage());
            }
        });

    }

    private void requestOrderDetailFromTradeNo(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<OrderDetailBean> products = api.orderInfo(TokenManager.getInstance().getLoginToken().getData().getToken(),trade_no);
        products.enqueue(new Callback<OrderDetailBean>() {
            @Override
            public void onResponse(Call<OrderDetailBean> call, Response<OrderDetailBean> response) {

                try {
                    productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        handleOrderRequestResult();
                    }else {
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<OrderDetailBean> call, Throwable t) {
                ToastUtils.showToast(OrderDetailActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void handleOrderRequestResult(){
        //订单状态（-1 无效  0 待确认, 1已确认/待支付, 2已支付/待发货, 3已发货/待收货, 4已完成, 5已取消, 6已关闭）
        //隐藏物流信息模块
        deliverLayout.setVisibility(View.GONE);
        int status = productBean.getData().getStatus();
        if (status == 1 || status == 0){
            getAddressList();//如果没有支付，需要去拉取收货地址列表
        } else if (status == 3 || status == 4) {
            initDeliverInfo();
        } else if (status == 5) {

        } else if (status == 6) {

        }
        setDetailData();
    }

    private void setDetailData(){
        OrderDetailBean.DataBean data = productBean.getData();
        //订单状态（-1 无效  0 待确认, 1已确认/待支付, 2已支付/待发货, 3已发货/待收货, 4已完成, 5已取消, 6已关闭）
        switch (data.getStatus()){
            case -1:
                break;
            case 0:
            case 1:
                leftTime.setText("剩余时间:" + data.getExpire_time());
                money.setText("需付款:" + data.getPay_amount());
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                wait4PayLayout.setVisibility(View.GONE);
                payCancelPayLayout.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        orderNum.setText("订单编号:" + data.getTrade_no());

    }

    private void onPayMethodClick(){
        showDialog(payArray);
    }

    public void showDialog(String[] arr){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(OrderDetailActivity.this,R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(OrderDetailActivity.this,R.layout.dialog_custom_layout,null);

//        Integer position  = (Integer) SPUtils.get(OrderDetailActivity.this, Constant.PAY_METHOD,Integer.valueOf(2));

        final RelativeLayout topLayout = view.findViewById(R.id.top_layout);
        final RelativeLayout bottomLayout = view.findViewById(R.id.bottom_layout);
        final TextView topTextView = view.findViewById(R.id.dialog_custom_top_tv);
        final TextView bottomTV = view.findViewById(R.id.dialog_custom_bottom_tv);
        topTextView.setText(arr[0]);
        bottomTV.setText(arr[1]);
        final String tag = (String) topLayout.getTag();
        final String bottomTag = (String) bottomLayout.getTag();

//        if (position == 0){
            bottomLayout.setBackground(getResources().getDrawable(R.color.white));
            bottomTV.setTextColor(getResources().getColor(R.color.gray));

            topLayout.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
            topTextView.setTextColor(getResources().getColor(R.color.white));
//        }else if (position == 1){
//            bottomTV.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
//            bottomTV.setTextColor(getResources().getColor(R.color.white));
//
//            topLayout.setBackground(getResources().getDrawable(R.color.white));
//            topTextView.setTextColor(getResources().getColor(R.color.gray));
//        }

        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!TextUtils.isEmpty(tag) && tag.equalsIgnoreCase(getString(R.string.false_tag))){
                    topLayout.setTag("true");
                    bottomLayout.setTag("false");

                    topLayout.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
                    topTextView.setTextColor(getResources().getColor(R.color.white));

                    bottomLayout.setBackground(getResources().getDrawable(R.color.white));
                    bottomTV.setTextColor(getResources().getColor(R.color.gray));
//                }else{
//                    v.setTag("false");
//                    bottomLayout.setTag("true");
//
//                    v.setBackground(getResources().getDrawable(R.color.white));
//                    topTextView.setTextColor(getResources().getColor(R.color.gray));
//
//                    bottomLayout.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
//                    bottomTV.setTextColor(getResources().getColor(R.color.white));
//                }
            }
        });

        bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!TextUtils.isEmpty(bottomTag) && bottomTag.equalsIgnoreCase(getString(R.string.false_tag))){
                topLayout.setTag("false");
                bottomLayout.setTag("true");

                topLayout.setBackground(getResources().getDrawable(R.color.white));
                topTextView.setTextColor(getResources().getColor(R.color.gray));

                bottomLayout.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
                bottomTV.setTextColor(getResources().getColor(R.color.white));
//                }else{
//                    topLayout.setTag("false");
//                    v.setTag("true");
//                    v.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
//                    bottomTV.setTextColor(getResources().getColor(R.color.white));
//
//                    topLayout.setBackground(getResources().getDrawable(R.color.white));
//                    topTextView.setTextColor(getResources().getColor(R.color.gray));
//                }
            }
        });

        Button confirmBtn = view.findViewById(R.id.bottom_dialog_comfirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topTag = (String) topLayout.getTag();
                String bottomTag = (String) bottomLayout.getTag();

                if (!TextUtils.isEmpty(topTag) && !topTag.equalsIgnoreCase(getString(R.string.false_tag))){
                    selectedPosition = 0;
                }else if (!TextUtils.isEmpty(bottomTag) && !bottomTag.equalsIgnoreCase(getString(R.string.false_tag)) ){
                    selectedPosition = 1;
                }else {
                    selectedPosition = 1;
                }

                handleResult();
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dp2px(OrderDetailActivity.this, 180));
        dialog.show();
    }

    private void handleResult(){
        if (selectedPosition == 0){
            payMethodText.setText("微信");
            onWeixinPayClick();
        }else {
            payMethodText.setText("支付宝");
            onAliPayClick();
        }
        SPUtils.put(OrderDetailActivity.this, Constant.PAY_METHOD,Integer.valueOf(selectedPosition));
    }

    private void onWeixinPayClick(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<WXCallback> products = api.wxpay(TokenManager.getInstance().getLoginToken().getData().getToken(),addressId,trade_no,"2");
        products.enqueue(new Callback<WXCallback>() {
            @Override
            public void onResponse(Call<WXCallback> call, Response<WXCallback> response) {

                try {
                    WXCallback productBean = response.body();
                    WXCallback.DataBean.PayBean payBean = productBean.getData().getPay();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                        builder.setAppId(payBean.getAppid() + "")
                                .setPartnerId(payBean.getPartnerid() + "")
                                .setPrepayId(payBean.getPrepayid() + "")
                                .setPackageValue(payBean.getPackageX() + "")
                                .setNonceStr(payBean.getNoncestr() + "")
                                .setTimeStamp(payBean.getTimestamp() + "")
                                .setSign(payBean.getSign() + "")
                                .build().toWXPayNotSign(OrderDetailActivity.this);
                    }else {
                        ToastUtils.showToast(OrderDetailActivity.this, "支付失败");
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<WXCallback> call, Throwable t) {
                ToastUtils.showToast(OrderDetailActivity.this, "支付失败");
            }
        });
    }

    private void onAliPayClick(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<PayCallBackBean> products = api.pay(TokenManager.getInstance().getLoginToken().getData().getToken(),addressId,trade_no,"1");
        products.enqueue(new Callback<PayCallBackBean>() {
            @Override
            public void onResponse(Call<PayCallBackBean> call, Response<PayCallBackBean> response) {

                try {
                    PayCallBackBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        payV2(productBean.getData().getPay());
                    }else {
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<PayCallBackBean> call, Throwable t) {
                ToastUtils.showToast(OrderDetailActivity.this, "支付失败");
            }
        });
    }

    /**
     * 支付宝支付业务示例
     */
    public void payV2(final String orderInfo) {
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, true);
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

//        String sign = OrderInfoUtil2_0.getSign(params, Constant.AliPayPrivate, true);
//        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

        @Override
    public void onReq(BaseReq baseReq) {
        Log.e("onReq", "onReq, errCode = " + baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("onReq", "onPayFinish, errCode = " + baseResp.errCode);

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int errCord = baseResp.errCode;
            if (errCord == 0) {
                Toast.makeText(OrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(OrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
            //这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
            //获取到你刚刚存到本地的状态码进行相应的操作就可以了
        }
    }

    private void onCancelOrderClick(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.cancelOrder(TokenManager.getInstance().getLoginToken().getData().getToken(),trade_no);
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        ToastUtils.showToast(OrderDetailActivity.this, productBean.getMessage());
                    }else {
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(OrderDetailActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void onMoreAddressClick(){
        Intent intent = new Intent(OrderDetailActivity.this, AddressManagerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.on_pay_method:
                onPayMethodClick();
                break;
            case R.id.cancel_order:
                onCancelOrderClick();
                break;
            case R.id.more_address:
                onMoreAddressClick();
                break;
            default:
                break;
        }
    }
}
