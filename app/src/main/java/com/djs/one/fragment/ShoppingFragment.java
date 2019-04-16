package com.djs.one.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djs.one.R;
import com.djs.one.activity.LoginActivity;
import com.djs.one.activity.OrderDetailActivity;
import com.djs.one.adapter.DialogSizeItemAdapter;
import com.djs.one.adapter.ShoppingCartAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.CreateOrderBean;
import com.djs.one.bean.ShoppingCarItems;
import com.djs.one.bean.SuccessfulMode;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.manager.UserManager;
import com.djs.one.util.DensityUtil;
import com.djs.one.util.SysUtils;
import com.djs.one.util.ToastUtils;
import com.djs.one.util.WXPayUtils;
import com.djs.one.view.AmountView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.djs.one.util.WXPayUtils;
//import com.tencent.mm.opensdk.constants.ConstantsAPI;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.opensdk.modelmsg.WXTextObject;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class ShoppingFragment extends BaseFragment implements View.OnClickListener,
        ShoppingCartAdapter.onCheckboxClickListener,
        ShoppingCartAdapter.onAmountValueChangeListener {//,IWXAPIEventHandler


    public LinearLayout check_LL;
    private int count = 0;
    private List<ShoppingCarItems.DataBean.ListBean> datas = new ArrayList();
    public View layout_empty_shopcart;
    protected Activity mActivity;
    public CheckBox mAllCheckBox;
    public CheckBox mAllCheckBoxBottom;
    public TextView mClearShoppingCart;
    public TextView mGoPay;
    public RelativeLayout mLlCart;
    public LinearLayout mOrderInfo;
    private ShoppingCartAdapter mShoppingCartAdapter;
    public TextView mTotalPrice;
    private double price = 0.0D;

    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView recyclerView;

    //合计 ： 960元
    private LinearLayout moneyLayout;

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016092500589630";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     * 获取权限使用的 RequestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1002;

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDvFtLcbemEvETuaPL/58u4wJXBXXPzm7msVhmSrOj0KKVD8x+1ADRwVAxYwcC/wI3/W13/IDEHsU3IvD9JGtlhF1TYKwxEzzv8GE5FpiMa0UyXtdPfZV1iK6tWqLFW3IpOiqIl5geEYa7jqhaSVSJyrBcmohjR0iNiOfXocLbljXwAszwru+SZAKobTo4o6R/XqARy+cu4PQuGJ5xVLeIoExZw+zDcFtR9ax3mwIxDgbXdBbinmcW8gtISes2o6ZUVmP+nuJQCltirvYX7Q3CCg6V+Muwb+ABmYGqmlGsqw5OOywWOFzVKMbgoECdEMCZMgoqQOieXdlRhEG+BY+VdAgMBAAECggEAQ0HkZ2Xz/wcHTRnw4dRPFtX2SsMDT1BLVxORdhV2ItkcRJUsIjrAhajfIEjA9DAywdbuBksD/+n8u69ZDjOjWxC5KWZSuTii4zPjMOyfi3e9WCAqTfmx31/xfxtNZ+X6ckXFf2vKSY9BJ4I9f0S7wA5xJMkMulmm4obzikPK75/mPqpA8PVzOHqL4JSnuLuULagYAjTUQK51M2/0wlTr0+IFboNlQtadwYranbvQS+88xiwWQQBoAj0axIrTYvg0aIGh10Y7NhAvf4jf0tMJFuObp02tdB1c4B5gA3TRSE+mKF5RvVpjJNnaspqBuNxX1PU9xH2nprP2EIlppGoqAQKBgQD8EO9VrJjV85IaY2iCnJ/pBYonLLFMhGCACkO8/1Qp746cWriabGTsIKWP0srQzT74uStIgFjz6mlsLf1coccl5K1789ixT+2KinYbuaZ6iSjMg5uN1u0N9e/czgXzKJxyWLhcAxfF+QHxteFdvKBXKgoQmKfKnQO4j+BM/yZJfQKBgQDy0grj89suciQEXzAxBUT67X7uZ9HgmttoaODQlaWIxoQ8iY6TBGL420qUWKIqqd35G4KBvtmbEtj0HdglIWmJ5cv9G68x5hpevcueFSN9B3QHbaiv8wV9cawKEkEykDheligSosTGJGeaSFd+/7qt2JU1yoiyV/0DLTsaJt/RYQKBgQDiW77h/CYf9CSfJ+hBaq+7zaq79Umewj36nb0o603EfwldZabjnjWfSs4C8zQJAFftejPhXskC+d4ENOSSnAFACTS13EwbJwDVafQYf8Z9wdBuoD0/yeOFpvbpHeZ/71dW5E9i6bAeq7fQPRoOWIbQ38K8FqqPrCMcSbAO1Q6n7QKBgQDA169ueiyIJn4UOsS7KcQuxI8aJ7m290VpFmH1RePBTGeY7GYevE0d9oq0Ze/kkiOHwyFSiY+oaL+EoG8YjgCTU709ts7cgjJPK8yaL4+PrGupup1Nn2OszKolFXpR/dfGxtnscvhzpFXjYbCbW92WU3uszLzjZp25+CIIEveOAQKBgAjNqJYl4dqCpq4oK2RIAdU/vN8SOtOQ155VeulZQPMOLm2LpbNZtuUNwmqRn4zIDFSIzFiZb2JFrG0ozTyJiLjTxmYbVbLk4rAHXSiW/HFE/Bq+vhh33hVvw3QfqLZK065StMQlcqgv/l/SOlYA2ac08r5+IyyBtpjIX/vh+5wE";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;

    private int page = 1;
    private int pageSize = 20;


    public ShoppingFragment() {
    }

    public static ShoppingFragment getInstance() {
        return new ShoppingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
        requestPermission();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        if (this.mActivity == null) {
            return;
        }
        findViews(paramView);
        initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserManager.getInstance().isLogin()){
            mClearShoppingCart.setVisibility(View.VISIBLE);
            getShoppingCarItems();
        }else {
            mClearShoppingCart.setVisibility(View.GONE);
        }
    }

    @Override
    protected void findViews(View paramView) {
        super.findViews(paramView);

        this.mClearShoppingCart = paramView.findViewById(R.id.clear_shopping_cart);
        this.mAllCheckBox = paramView.findViewById(R.id.all_checkBox);
        mAllCheckBoxBottom = paramView.findViewById(R.id.all_checkBox_bottom);
        this.mClearShoppingCart.setOnClickListener(this);

        this.mAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                mAllCheckBoxBottom.setChecked(paramAnonymousBoolean);
                ShoppingFragment.this.checkAllGoodsInfo(paramAnonymousBoolean);
            }
        });

        mAllCheckBoxBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                mAllCheckBox.setChecked(paramAnonymousBoolean);
                ShoppingFragment.this.checkAllGoodsInfo(paramAnonymousBoolean);
            }
        });

        swipeToLoadLayout = paramView.findViewById(R.id.swipeToLoadLayout);
        this.recyclerView = paramView.findViewById(R.id.swipe_target);
        this.mTotalPrice = paramView.findViewById(R.id.total_price);
        this.mGoPay = paramView.findViewById(R.id.go_pay);
        this.mGoPay.setOnClickListener(this);
        this.mOrderInfo = paramView.findViewById(R.id.order_info);
        this.mLlCart = paramView.findViewById(R.id.ll_cart);
        this.check_LL = paramView.findViewById(R.id.check_LL);
        this.layout_empty_shopcart = paramView.findViewById(R.id.layout_empty_shopcart);
        moneyLayout = paramView.findViewById(R.id.money_layout);

        layout_empty_shopcart.setOnClickListener(this);
        this.mShoppingCartAdapter = new ShoppingCartAdapter(this.mActivity, datas, this, this);
        recyclerView.setAdapter(mShoppingCartAdapter);

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = page + 1;
                        getShoppingCarItems();
                    }
                }, 30);
            }
        });

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        getShoppingCarItems();
                    }
                }, 30);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
//        getShoppingCarItems();
    }

    private void getShoppingCarItems()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ShoppingCarItems> products = api.shoppingCarItems(TokenManager.getInstance().getLoginToken().getData().getToken(),pageSize + "",page + "");
        products.enqueue(new Callback<ShoppingCarItems>() {
            @Override
            public void onResponse(Call<ShoppingCarItems> call, Response<ShoppingCarItems> response) {

                try {
                    if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                    if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);

                    ShoppingCarItems productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        datas.clear();
                        if (productBean.getData().getList().size() > 0){
                            datas.addAll(productBean.getData().getList());
                            layout_empty_shopcart.setVisibility(View.GONE);
                            mLlCart.setVisibility(View.VISIBLE);
                        }
                        mShoppingCartAdapter.notifyDataSetChanged();

                    }else {
                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<ShoppingCarItems> call, Throwable t) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);
                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

    private void test(){
        UMConfigure.init(getActivity(),"5c8ef97261f564f490000a1c"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"70d6885b210ad186c508eee7fa687019");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        new ShareAction(getActivity())
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .withText("hello")//分享内容
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"onStart",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失 败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消 了",Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            default:
                return;
            case R.id.layout_empty_shopcart:
                if (!UserManager.getInstance().isLogin()){
                    SysUtils.startActivity(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.clear_shopping_cart:
                onEditClick();
                return;
            case R.id.go_pay:
//                defaultView();
//                test();
//                sendToWeixin();
                onGoPlayClick();
//                showBottomDialog();
                return;
        }
    }

    private void defaultView(){
        if (UserManager.getInstance().isLogin()){
//            getShoppingCarItems();
            onGoPlayClick();
        }else {
            SysUtils.startActivity(getActivity(), LoginActivity.class);
        }
    }

    int sizeListSelectedPosition = 0;
    int colorListSelectedPosition = 0;
    int amountInDialog = 1;
    private void showBottomDialog(){

        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(getActivity(),R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(getActivity(),R.layout.dialog_custom_layout,null);

        ListView sizeListView = view.findViewById(R.id.dialog_size_list);
        List<String> items = new ArrayList<>();
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        final DialogSizeItemAdapter adapter = new DialogSizeItemAdapter(getActivity(),items);
        sizeListView.setAdapter(adapter);

        sizeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);
                sizeListSelectedPosition = position;
            }
        });
        sizeListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        LinearLayout colorGridLayout = view.findViewById(R.id.dialog_color_list);
        List<String> colors = new ArrayList<>();
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");

        final List<TextView> colorTextViews = new ArrayList<>(colors.size());

        for (int i = 0; i < colors.size(); i++){
            final TextView colorView = new TextView(getActivity());
            colorView.setBackground(getResources().getDrawable(R.drawable.bg_amount_layout));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,30,0);
            colorView.setPadding(30,4,30,4);
            colorView.setLayoutParams(lp);
            colorView.setText(colors.get(i));
            colorView.setTag(i);
            colorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change other text status
                    for(TextView textView : colorTextViews){
                        textView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                        textView.setTextColor(getActivity().getResources().getColor(R.color.black));
                    }
                    colorListSelectedPosition = (int) v.getTag();
                    colorView.setBackgroundColor(getActivity().getResources().getColor(R.color.black));
                    colorView.setTextColor(getActivity().getResources().getColor(R.color.white));
                }
            });

            colorTextViews.add(colorView);
            colorGridLayout.addView(colorView);
        }


        AmountView amountView = view.findViewById(R.id.dialog_color_item_amount);
        amountView.setGoods_storage(Integer.MAX_VALUE);
        amountView.etAmount.setText("1");
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Log.e("Smart", "onAmountChange: mount = "  + amount );
                amountInDialog = amount;
            }
        });

        Button confirmBtn = view.findViewById(R.id.shopping_dialog_comfirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("xxxx","size = " + sizeListSelectedPosition + "color" + colorListSelectedPosition + "amount" + amountInDialog  + "");
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
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtil.dp2px(getActivity(), (float) (SysUtils.getScreenHeight(getActivity()) * 0.25)));
        dialog.show();

    }

    /**
     * 全选按钮点击
     * @param paramBoolean
     */
    private void checkAllGoodsInfo(boolean paramBoolean)
    {

//        price = 0.0;
        int i = 0;
        while (i < datas.size())
        {
            ShoppingCarItems.DataBean.ListBean goodsInfo = datas.get(i);
//            price += goodsInfo.getPrice();
            goodsInfo.setChoosed(paramBoolean);
            i += 1;
        }

        if (paramBoolean){//全选

        }else {//全部取消
            price = 0;
        }

        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("编辑")){
            mTotalPrice.setText("￥" + price);
        }
        this.mShoppingCartAdapter.notifyDataSetChanged();
    }

    private void clearCart()
    {
        this.mClearShoppingCart.setVisibility(View.GONE);
        this.mLlCart.setVisibility(View.GONE);
    }

    private void delGoods()
    {
        removeShoppingCarRequest(getSelectedList());
    }

    private ArrayList getSelectedList(){
        ArrayList<ShoppingCarItems.DataBean.ListBean> localArrayList = new ArrayList();
        int i = 0;
        while (i < this.datas.size())
        {
            if (datas.get(i).isChoosed()){
                localArrayList.add(this.datas.get(i));
            }
            i += 1;
        }

        if (localArrayList.size() == 0) return null;

        return localArrayList;
    }

    /**
     * 删除购物车
     * 购物车ID,$cartId等于all时表示删除全部,多个时使用,号隔开
     */
    private void removeShoppingCarRequest(final ArrayList<ShoppingCarItems.DataBean.ListBean> localArrayList){
        String cartId = "";
        for (ShoppingCarItems.DataBean.ListBean goods : localArrayList) {
            cartId = cartId + goods.getId() + ",";
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.removeShoppingCar(TokenManager.getInstance().getLoginToken().getData().getToken(),cartId);
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        ToastUtils.showToast(getActivity(),"删除成功");

                        datas.removeAll(localArrayList);
                        mShoppingCartAdapter.notifyDataSetChanged();

                        if (datas.size() == 0){
                            clearCart();
                        }
                    }else {
                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

    public void onEditClick(){
        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("编辑")){
            mClearShoppingCart.setText("完成");
            mGoPay.setText("删除");
            moneyLayout.setVisibility(View.GONE);
        }else {
            mClearShoppingCart.setText("编辑");
            mGoPay.setText("去结算");
            moneyLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * “itemID:skuId:quantity"
     * @return
     */
    private String getSelectedSKUs(){
        String skus = "";
        ArrayList<ShoppingCarItems.DataBean.ListBean> localArrayList = getSelectedList();

        if (localArrayList == null) return skus;
        if (localArrayList.size() == 0 ) return skus;

        for (ShoppingCarItems.DataBean.ListBean bean : localArrayList) {
            skus += bean.getItem_id() + ":" + bean.getSku_id() + ":" + bean.getQuantity() + ";";
        }

        return skus;
    }

    /**
     * 去结算、删除按钮点击
     */
    public void onGoPlayClick(){
        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("完成")){//删除购物车
            delGoods();
        }else {//创单 ；商品SKU,格式：itemId:skuId:quantity[;itemId:skuId:quantity]
            if (TextUtils.isEmpty(getSelectedSKUs())) return;
            createOrder(getSelectedSKUs());
        }
    }

    private void createOrder(String skus){
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
                                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                                intent.putExtra("trade_no",productBean.getData().getTrade_no());
                                getActivity().startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                                return;
                            }
                        }
                        ToastUtils.showToast(getActivity(),"创单失败");
                    }else {
                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<CreateOrderBean> call, Throwable t) {
                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

    /**
     * 检查支付宝 SDK 所需的权限，并在必要的时候动态获取。
     * 在 targetSDK = 23 以上，READ_PHONE_STATE 和 WRITE_EXTERNAL_STORAGE 权限需要应用在运行时获取。
     * 如果接入支付宝 SDK 的应用 targetSdk 在 23 以下，可以省略这个步骤。
     */
    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSIONS_REQUEST_CODE);

        } else {
//            showToast(this, getString(R.string.permission_already_granted));
        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {

                // 用户取消了权限弹窗
                if (grantResults.length == 0) {
//                    showToast(this, getString(R.string.permission_rejected));
                    return;
                }

                // 用户拒绝了某些权限
                for (int x : grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
//                        showToast(this, getString(R.string.permission_rejected));
                        return;
                    }
                }

                // 所需的权限均正常获取
//                showToast(this, getString(R.string.permission_granted));
            }
        }
    }

    private void onWeixinPaySignOnNet(){
        //假装请求了服务器 获取到了所有的数据,注意参数不能少
        WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
        builder.setAppId("123")
                .setPartnerId("56465")
                .setPrepayId("41515")
                .setPackageValue("5153")
                .setNonceStr("5645")
                .setTimeStamp("56512")
                .setSign("54615")
                .build().toWXPayNotSign(getActivity());
    }

    private void onWeixinPaySignOnLocal(){
        //假装请求了服务器 获取到了所有的数据,注意参数不能少
        //假装请求了服务端信息，并获取了appid、partnerId、prepayId，注意参数不能少
        WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
        builder.setAppId("123")
                .setPartnerId("213")
                .setPrepayId("3213")
                .setPackageValue("Sign=WXPay")
                .build()
                .toWXPayAndSign(getActivity(),"123","key");
    }

    private void sendToWeixin(){
        IWXAPI api = WXAPIFactory.createWXAPI(getActivity(),Constant.APP_ID);
        api.registerApp(Constant.APP_ID);

        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = "Text";

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        // msg.title = "Will be ignored";
        msg.description = "Des";

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis()); // transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = true ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        req.openId = Constant.APP_ID;//getOpenId();
        // 调用api接口发送数据到微信
        api.sendReq(req);
    }

    @Override
    public void onCheckboxClick(View view, boolean isChecked) {
        int position = (int) view.getTag();
        datas.get(position).setChoosed(isChecked);

        ShoppingCarItems.DataBean.ListBean goodsInfo = datas.get(position);
        if (isChecked){
            price += goodsInfo.getMarket_price() * goodsInfo.getQuantity();
        }else {
            if (price > 0){
                price -= goodsInfo.getMarket_price() * goodsInfo.getQuantity();//Integer.valueOf(goodsInfo.getSize());
            }
        }

        int checkNum = 0;
        for (ShoppingCarItems.DataBean.ListBean goods : datas) {
            if (goods.isChoosed()) {
                checkNum ++;
            }
        }

        if (checkNum == datas.size()){//所有的都被选中
            mAllCheckBox.setChecked(true);
            mAllCheckBoxBottom.setChecked(true);
        }

        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("编辑")){
            mTotalPrice.setText("共计：￥" + price);
        }
    }

    /**
     * 加 1 减 1 按钮监听
     * @param view
     * @param amount
     */
    @Override
    public void onAmountValueChangeListener(View view, int amount) {
        String status = mClearShoppingCart.getText().toString();

        if (status.equalsIgnoreCase("编辑")){
            int position = (int) view.getTag();
            ShoppingCarItems.DataBean.ListBean good = datas.get(position);

            String type = "1";
            if (amount > good.getQuantity()){//加一
                type = "1";
            }else{//减一
                type = "2";
            }

            changeQuantity(good.getId() + "",type);

            good.setQuantity(amount);

            price = 0.0;

            int i = 0;
            while (i < datas.size())
            {
                ShoppingCarItems.DataBean.ListBean goods = datas.get(i);
                if (goods.isChoosed()){
                    price += goods.getMarket_price()  * goods.getQuantity();//Integer.valueOf(goods.getSize());
                }
                i += 1;
            }


            mTotalPrice.setText("￥" + price);
        }
    }

    /**
     *
     * @param cardId 购物车ID
     * @param type 	操作类型：1表示+1 2表示-1
     */
    private void changeQuantity(String cardId, String type){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.changeQuantity(TokenManager.getInstance().getLoginToken().getData().getToken(),cardId,type);
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                    }else {
                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

//    @Override
//    public void onReq(BaseReq baseReq) {
//        Log.e("onReq", "onReq, errCode = " + baseReq.toString());
//    }
//
//    @Override
//    public void onResp(BaseResp baseResp) {
//        Log.e("onReq", "onPayFinish, errCode = " + baseResp.errCode);
//
//        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            int errCord = baseResp.errCode;
//            if (errCord == 0) {
//                Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
//            }
//            //这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
//            //获取到你刚刚存到本地的状态码进行相应的操作就可以了
//        }
//    }
}
