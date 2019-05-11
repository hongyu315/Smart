package com.djs.one.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.danikula.videocache.HttpProxyCacheServer;
import com.djs.one.ForOneApplication;
import com.djs.one.R;
import com.djs.one.adapter.DialogSizeItemAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.AddToShoppingCarBean;
import com.djs.one.bean.ProductDetailBannerBean;
import com.djs.one.bean.ProductDetailBean;
import com.djs.one.bean.SuccessfulModeBean;
import com.djs.one.constant.Constant;
import com.djs.one.manager.TokenManager;
import com.djs.one.manager.UserManager;
import com.djs.one.util.DensityUtil;
import com.djs.one.util.ShoppingUtils;
import com.djs.one.util.SysUtils;
import com.djs.one.util.ToastUtils;
import com.djs.one.view.AmountView;
import com.djs.one.view.Banner;
import com.jaeger.library.StatusBarUtil;
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
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    private Banner banner;
    private List<String> bannerUrlList = new ArrayList<>();
    private LinearLayout wechatLayout, friendGroupLayout;

    //商品id
    private String itemId;

    private ProductDetailBean.ProductDetail mProductDetail;

    /**
     * 详情页子view
     */
    TextView titleTV, priceTV, brandStoryTV, detailCompositionTV;
    WebView contentWV;

    private IWXAPI api;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        StatusBarUtil.setTranslucent(ProductDetailActivity.this, 100);

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);//

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        findViewById(R.id.product_detail_back_layout).setOnClickListener(this);

        banner = findViewById(R.id.product_detail_banner);

        titleTV = findViewById(R.id.detail_title);
        priceTV = findViewById(R.id.detail_price);
        detailCompositionTV = findViewById(R.id.detail_composition);
        brandStoryTV = findViewById(R.id.brand_story);
        contentWV = findViewById(R.id.detail_content);
        findViewById(R.id.detail_favorite).setOnClickListener(this);
        findViewById(R.id.buy_now_btn).setOnClickListener(this);

    }

    @Override
    protected void initData() {
        super.initData();

        try {
            Intent intent = getIntent();
            itemId = intent.getStringExtra("itemId");

            if (!TextUtils.isEmpty(itemId)) {
                getProductDetailBanner();
                getProductDetail();
            }
        } catch (Exception e) {
        }
    }

    private void initBanner(List<ProductDetailBannerBean.ProductBanner> bannerList) {
        try {
            HttpProxyCacheServer proxy = ForOneApplication.getProxy(getApplicationContext());
            for (ProductDetailBannerBean.ProductBanner banner : bannerList) {
                if (banner.getMedia_type() == 1) {
                    bannerUrlList.add(banner.getMedia_url());
                } else if (banner.getMedia_type() == 2) {
                    bannerUrlList.add(proxy.getProxyUrl(banner.getMedia_url(), true));
                }
            }

            banner.setDataList(ProductDetailActivity.this, bannerUrlList);
//            banner.startBanner();
        } catch (Exception e) {
        }
    }

    private void getProductDetailBanner() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductDetailBannerBean> products = api.getProductDetailBanner(itemId);
        products.enqueue(new Callback<ProductDetailBannerBean>() {
            @Override
            public void onResponse(Call<ProductDetailBannerBean> call, Response<ProductDetailBannerBean> response) {

                try {
                    ProductDetailBannerBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        initBanner(productBean.getData());
                    } else {
                        ToastUtils.showToast(ProductDetailActivity.this, response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ProductDetailBannerBean> call, Throwable t) {
                ToastUtils.showToast(ProductDetailActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void getProductDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductDetailBean> products = api.getProductDetail(itemId);
        products.enqueue(new Callback<ProductDetailBean>() {
            @Override
            public void onResponse(Call<ProductDetailBean> call, Response<ProductDetailBean> response) {

                try {
                    ProductDetailBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        mProductDetail = productBean.getData();
                        setData();
                    } else {
                        ToastUtils.showToast(ProductDetailActivity.this, response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ProductDetailBean> call, Throwable t) {
                ToastUtils.showToast(ProductDetailActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void setData() {
        try {
            if (mProductDetail != null) {
                titleTV.setText(mProductDetail.getTitle());
                priceTV.setText("￥" + mProductDetail.getMarket_price());
                detailCompositionTV.setText(mProductDetail.getComposition());
                brandStoryTV.setText(mProductDetail.getStory());
                String html = mProductDetail.getContent();
//                String html = "<html>\n" +
//                        "\n" +
//                        "    <head>\n" +
//                        "        <meta charset=\"UTF-8\"/>\n" +
//                        "    </head>\n" +
//                        "\n" +
//                        "    <body>\n" +
//                        "\n" +
//                        "    <p>这是一个段落噻！</p>\n" +
//                        "\n" +
//                        "    </body>\n" +
//                        "\n" +
//                        "    <script>\n" +
//                        "\n" +
//                        "        function callJS(){\n" +
//                        "            alert(\"Android call js method!\");\n" +
//                        "        }\n" +
//                        "\n" +
//                        "    </script>\n" +
//                        "\n" +
//                        "</html>\n";
                contentWV.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
//                contentWV.loadUrl("https://www.m.iqiyi.com");
            }
        } catch (Exception e) {
        }
    }

    /**
     * 分享按钮点击
     *
     * @param view
     */
    public void onShareBtnClick(View view) {

        Dialog dialog = new Dialog(ProductDetailActivity.this, R.style.MyDialog);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (SysUtils.getScreenWidth(this) * 0.7);
        lp.height = (int) (SysUtils.getScreenHeight(this) * 0.55);//0.63);
        dialogWindow.setAttributes(lp);
        dialog.setContentView(R.layout.product_detail_share);
        dialogWindow.findViewById(R.id.wechat_layout).setOnClickListener(this);
        dialogWindow.findViewById(R.id.friend_group_layout).setOnClickListener(this);
        Glide.with(ProductDetailActivity.this).load(mProductDetail.getThumb_url()).into((ImageView) dialogWindow.findViewById(R.id.share_img));
        ((TextView) dialogWindow.findViewById(R.id.share_name)).setText(mProductDetail.getTitle() + "");
        ((TextView) dialogWindow.findViewById(R.id.share_time)).setText("上架时间" + SysUtils.stampToDate(mProductDetail.getOn_sale_time() + ""));
        ((TextView) dialogWindow.findViewById(R.id.share_price)).setText("￥" + mProductDetail.getMarket_price());
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    private void onFavoriteBtnClick() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulModeBean> products = api.collect(TokenManager.getInstance().getLoginToken().getData().getToken(), itemId);
        products.enqueue(new Callback<SuccessfulModeBean>() {
            @Override
            public void onResponse(Call<SuccessfulModeBean> call, Response<SuccessfulModeBean> response) {

                try {
                    SuccessfulModeBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        ToastUtils.showToast(ProductDetailActivity.this, productBean.getData());
                    } else {
                        ToastUtils.showToast(ProductDetailActivity.this, response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SuccessfulModeBean> call, Throwable t) {
                ToastUtils.showToast(ProductDetailActivity.this, t.getLocalizedMessage());
            }
        });
    }

    /**
     * 加入购物车 按钮点击
     *
     * @param v
     */
    int sizeListSelectedPosition = 0;
    int colorListSelectedPosition = 0;
    int amountInDialog = 1;
    List<ProductDetailBean.ProductDetail.AttributesBean.ValuesBean> colorValues = new ArrayList<>();
    List<ProductDetailBean.ProductDetail.AttributesBean.ValuesBean> sizeValues = new ArrayList<>();

    public void onAddToShoppingCarBtnClick(View v) {
        if (UserManager.getInstance().isLogin()) {
            showShoppingCarDialog(2);
        } else {
            Intent intent3 = new Intent(ProductDetailActivity.this, LoginActivity.class);
            intent3.putExtra(Constant.PAGE, ProductDetailActivity.class.getName());
//            startActivity(intent3);
            startActivityForResult(intent3, 1000);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == 1000) {
                showShoppingCarDialog(2);
            } else if (requestCode == 2000) {
                showShoppingCarDialog(1);
            }
        }
    }

    private void showShoppingCarDialog(final int type) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(ProductDetailActivity.this, R.style.DialogTheme);

        //2、设置布局
        View view = View.inflate(ProductDetailActivity.this, R.layout.dialog_bottom_layout, null);
        TextView titleTV = view.findViewById(R.id.product_prompt_dialog_title);
        TextView priceTV = view.findViewById(R.id.product_prompt_dialog_price);
        ImageView thumbnail = view.findViewById(R.id.product_prompt_dialog_thumbnail);

        titleTV.setText(mProductDetail.getTitle() + "");
        priceTV.setText(mProductDetail.getMarket_price() + "");
        Glide.with(ProductDetailActivity.this).load(mProductDetail.getThumb_url()).into(thumbnail);
        ListView sizeListView = view.findViewById(R.id.dialog_size_list);
        List<ProductDetailBean.ProductDetail.AttributesBean> attributesBeanList = mProductDetail.getAttributes();

        for (ProductDetailBean.ProductDetail.AttributesBean attributesBean : attributesBeanList) {
            if (attributesBean.getAttr_name().equalsIgnoreCase("颜色")) {
                colorValues = attributesBean.getValues();
            } else if (attributesBean.getAttr_name().equalsIgnoreCase("尺寸")) {
                sizeValues = attributesBean.getValues();
            }
        }

        List<String> items = new ArrayList<>();
        for (ProductDetailBean.ProductDetail.AttributesBean.ValuesBean valuesBean : sizeValues) {
            items.add(valuesBean.getValue());
        }
        final DialogSizeItemAdapter adapter = new DialogSizeItemAdapter(ProductDetailActivity.this, items);
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
        for (ProductDetailBean.ProductDetail.AttributesBean.ValuesBean valuesBean : colorValues) {
            colors.add(valuesBean.getValue());
        }

        final List<TextView> colorTextViews = new ArrayList<>(colors.size());

        for (int i = 0; i < colors.size(); i++) {
            final TextView colorView = new TextView(ProductDetailActivity.this);
            colorView.setTextSize(12);
            colorView.setTextColor(getResources().getColor(R.color.home_glod_text_unselect));
            colorView.setBackground(getResources().getDrawable(R.drawable.bg_amount_layout));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, DensityUtil.dp2px(ProductDetailActivity.this, 10), 0);
            colorView.setPadding(DensityUtil.dp2px(ProductDetailActivity.this, 9),
                    DensityUtil.dp2px(ProductDetailActivity.this, 6),
                    DensityUtil.dp2px(ProductDetailActivity.this, 9),
                    DensityUtil.dp2px(ProductDetailActivity.this, 6));
            colorView.setLayoutParams(lp);
            colorView.setText(colors.get(i));
            colorView.setTag(i);
            colorView.setTextIsSelectable(true);
            colorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change other text status
                    for (TextView textView : colorTextViews) {
//                        textView.setBackgroundColor(ProductDetailActivity.this.getResources().getColor(R.color.white));
                        textView.setTextColor(ProductDetailActivity.this.getResources().getColor(R.color.home_glod_text_unselect));
                        textView.setBackground(getResources().getDrawable(R.drawable.bg_amount_layout));
                        textView.setPadding(DensityUtil.dp2px(ProductDetailActivity.this, 9),
                                DensityUtil.dp2px(ProductDetailActivity.this, 6),
                                DensityUtil.dp2px(ProductDetailActivity.this, 9),
                                DensityUtil.dp2px(ProductDetailActivity.this, 6));
                    }
                    colorListSelectedPosition = (int) v.getTag();
                    colorView.setBackgroundColor(ProductDetailActivity.this.getResources().getColor(R.color.home_glod_text_unselect));
                    colorView.setTextColor(ProductDetailActivity.this.getResources().getColor(R.color.white));

                }
            });

            if (i == 0) {//默认第一个选中
                colorView.performClick();
            }
            colorTextViews.add(colorView);
            colorGridLayout.addView(colorView);
        }


        AmountView amountView = view.findViewById(R.id.dialog_color_item_amount);
        amountView.setGoods_storage(Integer.MAX_VALUE);
        amountView.etAmount.setText("1");
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Log.e("Smart", "onAmountChange: mount = " + amount);
                amountInDialog = amount;
            }
        });

        Button confirmBtn = view.findViewById(R.id.shopping_dialog_comfirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == 1) {
                    ShoppingUtils.createOrder(getSkus(), ProductDetailActivity.this);
                } else if (type == 2) {
                    onAddToShoppingCarBtnPressed();
                }
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
                DensityUtil.dp2px(ProductDetailActivity.this,
                        (float) (SysUtils.getScreenHeight(ProductDetailActivity.this) * 0.22)));
        dialog.show();
    }

    /**
     * 加入购物车按钮点击
     */
    public void onAddToShoppingCarBtnPressed() {
        String colorId = colorValues.get(colorListSelectedPosition).getId() + "";
        String sizeId = sizeValues.get(sizeListSelectedPosition).getId() + "";

        Map<String, ProductDetailBean.ProductDetail.SkusBean> skuList = mProductDetail.getSku();

        String skuId = skuList.get(sizeId + "-" + colorId).getId() + "";
        String quantity = amountInDialog + "";

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<AddToShoppingCarBean> products = api.addItem(TokenManager.getInstance().getLoginToken().getData().getToken(), itemId, skuId, quantity);
        products.enqueue(new Callback<AddToShoppingCarBean>() {
            @Override
            public void onResponse(Call<AddToShoppingCarBean> call, Response<AddToShoppingCarBean> response) {

                try {
                    AddToShoppingCarBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        ToastUtils.showToast(ProductDetailActivity.this, "加入购物车成功");
                    } else {
//                        ToastUtils.showToast(ProductDetailActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<AddToShoppingCarBean> call, Throwable t) {
//                ToastUtils.showToast(ProductDetailActivity.this, "加入购物车失败");
            }
        });
    }

    public void onWechatBtnClick() {

        Log.e("xxxxxxx", "onWechatBtnClick click");
        WXTextObject textObj = new WXTextObject();
        textObj.text = "text";

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = "text";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = mTargetScene;
//调用api接口，发送数据到微信
        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void onFriendGroupBtnClick() {
        Log.e("xxxxxxx", "onFriendGroupBtnClick click");
        UMConfigure.init(ProductDetailActivity.this, "5c8ef97261f564f490000a1c"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "70d6885b210ad186c508eee7fa687019");
        PlatformConfig.setWeixin(Constant.APP_ID, "3baf1193c85774b3fd9d18447d76cab0");
        new ShareAction(ProductDetailActivity.this)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
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
            Toast.makeText(ProductDetailActivity.this, "onStart", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ProductDetailActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ProductDetailActivity.this, "失 败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ProductDetailActivity.this, "取消 了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onDestroy() {
        banner.destroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_detail_back_layout:
                mFinish();
                break;
            case R.id.wechat_layout:
                onWechatBtnClick();
                break;
            case R.id.friend_group_layout:
                onFriendGroupBtnClick();
                break;
            case R.id.detail_favorite:
                onFavoriteBtnClick();
                break;
            case R.id.buy_now_btn:
                buyNow();
                break;
        }
    }

    private void buyNow() {
        if (UserManager.getInstance().isLogin()) {
            showShoppingCarDialog(1);
        } else {
            Intent intent3 = new Intent(ProductDetailActivity.this, LoginActivity.class);
            intent3.putExtra(Constant.PAGE, ProductDetailActivity.class.getName());
//            startActivity(intent3);
            startActivityForResult(intent3, 2000);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    private String getSkus() {
        String colorId = colorValues.get(colorListSelectedPosition).getId() + "";
        String sizeId = sizeValues.get(sizeListSelectedPosition).getId() + "";
        Map<String, ProductDetailBean.ProductDetail.SkusBean> skuList = mProductDetail.getSku();
        String skuId = skuList.get(sizeId + "-" + colorId).getId() + "";
        String quantity = amountInDialog + "";
        return ShoppingUtils.getSkus(itemId, skuId, quantity);
    }
}
