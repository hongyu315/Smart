package com.djs.one.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.djs.one.adapter.HorizontalListViewAdapter;
import com.djs.one.adapter.ProductDetailDialogAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.AddToShoppingCarBean;
import com.djs.one.bean.ProductDetailBannerBean;
import com.djs.one.bean.ProductDetailBean;
import com.djs.one.bean.SuccessfulMode;
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
import com.djs.one.view.HorizontalListView;
import com.jaeger.library.StatusBarUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.djs.one.api.URL;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    private Banner banner;
    private List<String> bannerUrlList = new ArrayList<>();
    private LinearLayout wechatLayout, friendGroupLayout;

    //商品id
    private String itemId;

    public static ProductDetailBean.DataBean mProductDetail;

    /**
     * 详情页子view
     */
    TextView titleTV, priceTV, brandStoryTV, detailCompositionTV;
    WebView contentWV;

    private IWXAPI api;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

    String[] titles;
    private HorizontalListView hListView;
    private HorizontalListViewAdapter hListViewAdapter ;

    //弹框内容listview
    private ListView mDialogListView;
    private ProductDetailDialogAdapter mDialogAdapter;
    TextView mSize,mPrice;


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

        mReceiver receiver = new mReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.dsj.one.product_dialog");
        registerReceiver(receiver,intentFilter);

    }

    public class mReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            updatePrice();
        }
    }

    public void updatePrice(){
        int size = 0;
        int price = 0;
        for (int i = 0; i < mProductDetail.getSku().size();i++){
            for (int j = 0; j < mProductDetail.getSku().get(i).getSku().size() ; j ++){
                size = size + mProductDetail.getSku().get(i).getSku().get(j).getAmount();
                price = price + mProductDetail.getSku().get(i).getSku().get(j).getAmount() * mProductDetail.getSku().get(i).getSku().get(j).getMarket_price();
            }
        }
        mSize.setText(size + "");
        mPrice.setText("￥" + price);
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
                        titles = new String[mProductDetail.getSku().size()];
                        for (int i = 0; i < titles.length; i++){
                            titles[i] = mProductDetail.getSku().get(i).getValue();
                        }
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
        try{
            Dialog dialog = new Dialog(ProductDetailActivity.this, R.style.MyDialog);
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = (int) (SysUtils.getScreenWidth(this) * 0.7);
            lp.height = (int) (SysUtils.getScreenHeight(this) * 0.55);//0.63);
            dialogWindow.setAttributes(lp);
            dialog.setContentView(R.layout.product_detail_share);
            dialogWindow.findViewById(R.id.wechat_layout).setOnClickListener(this);
            dialogWindow.findViewById(R.id.friend_group_layout).setOnClickListener(this);
            if (!TextUtils.isEmpty(mProductDetail.getThumb_url())){
                Glide.with(ProductDetailActivity.this).load(mProductDetail.getThumb_url()).into((ImageView) dialogWindow.findViewById(R.id.share_img));
            }
            ((TextView) dialogWindow.findViewById(R.id.share_name)).setText(mProductDetail.getTitle() + "");
            ((TextView) dialogWindow.findViewById(R.id.share_time)).setText("上架时间" + SysUtils.stampToDate(mProductDetail.getOn_sale_time() + ""));
            ((TextView) dialogWindow.findViewById(R.id.share_price)).setText("￥" + mProductDetail.getMarket_price());
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }catch (Exception e){
        }
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
    List<ProductDetailBean.DataBean.AttributesBean.ValuesBean> colorValues = new ArrayList<>();
    List<ProductDetailBean.DataBean.AttributesBean.ValuesBean> sizeValues = new ArrayList<>();

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

        mSize = view.findViewById(R.id.bottom_size);
        mPrice = view.findViewById(R.id.bottom_price);

        ImageView thumbnail = view.findViewById(R.id.product_prompt_dialog_thumbnail);

        hListView = view.findViewById(R.id.horizon_listview);
        mDialogListView = view.findViewById(R.id.lv_base_call_activity);
        mDialogAdapter = new ProductDetailDialogAdapter(ProductDetailActivity.this);
        mDialogListView.setAdapter(mDialogAdapter);
        mDialogAdapter.setData(mProductDetail.getSku().get(0).getSku());

        hListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(),titles);
        hListView.setAdapter(hListViewAdapter);
        hListViewAdapter.setSelectIndex(0);
        hListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hListViewAdapter.setSelectIndex(i);
                hListViewAdapter.notifyDataSetChanged();

                mDialogAdapter.setData(mProductDetail.getSku().get(i).getSku());
            }
        });

        titleTV.setText(mProductDetail.getTitle() + "");
        priceTV.setText("￥" + mProductDetail.getMarket_price() + "");

        mSize.setText("" + Constant.size);
        mPrice.setText("￥" + Constant.price);
        Glide.with(ProductDetailActivity.this).load(mProductDetail.getThumb_url()).into(thumbnail);


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
                        (float) (SysUtils.getScreenHeight(ProductDetailActivity.this) * 0.25)));
        dialog.show();
    }

    /**
     * 加入购物车按钮点击
     */
    public void onAddToShoppingCarBtnPressed() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulMode> products = api.addItems(TokenManager.getInstance().getLoginToken().getData().getToken(), getSkus());
        products.enqueue(new Callback<SuccessfulMode>() {
            @Override
            public void onResponse(Call<SuccessfulMode> call, Response<SuccessfulMode> response) {

                try {
                    SuccessfulMode productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        ToastUtils.showToast(ProductDetailActivity.this, "加入购物车成功");
                    } else {
//                        ToastUtils.showToast(ProductDetailActivity.this,response.body().getMessage());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<SuccessfulMode> call, Throwable t) {
                ToastUtils.showToast(ProductDetailActivity.this, "加入购物车失败");
            }
        });
    }

    public void onWechatBtnClick() {
        wxShare(false);
    }

    private void wxShare(final boolean isTimeline){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = mProductDetail.getThumb_url();
                WXImageObject imgObj = new WXImageObject();
                imgObj.setImagePath(url);

//                WXTextObject textObj = new WXTextObject();
//                textObj.text = mProductDetail.getTitle();w

                //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                Bitmap bitmap = null;
                try {
                    java.net.URL url1 = new java.net.URL(url);
                    bitmap = BitmapFactory.decodeStream(url1.openStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap thumBitmap = bitmap.createScaledBitmap(bitmap, 120, 150, true);
//                    释放资源
                bitmap.recycle();
                msg.thumbData = bitmapToByteArray(thumBitmap, true);

                msg.description = mProductDetail.getTitle();

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("img");
                req.message = msg;
                req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                //调用api接口，发送数据到微信
                api.sendReq(req);
            }
        });

        thread.start();
    }

    private byte[] bitmapToByteArray(Bitmap bitmap, boolean recycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (recycle) {
            bitmap.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void onFriendGroupBtnClick() {
        wxShare(true);
//        UMConfigure.init(ProductDetailActivity.this, "5c8ef97261f564f490000a1c"
//                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "70d6885b210ad186c508eee7fa687019");
//        PlatformConfig.setWeixin(Constant.APP_ID, "3baf1193c85774b3fd9d18447d76cab0");
//        new ShareAction(ProductDetailActivity.this)
//                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
//                .withText(mProductDetail.getTitle())//分享内容
//                .setCallback(shareListener)//回调监听器
//                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
//            Toast.makeText(ProductDetailActivity.this, "onStart", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ProductDetailActivity.this, "分享成功", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ProductDetailActivity.this, "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
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
//        String colorId = colorValues.get(colorListSelectedPosition).getId() + "";
//        String sizeId = sizeValues.get(sizeListSelectedPosition).getId() + "";
//        List skuList = mProductDetail.getSku();
//        String skuId = "";//skuList.get(sizeId + "-" + colorId).getId() + "";
//        String quantity = amountInDialog + "";

        String skus = "";//	商品SKU,格式：itemId:skuId:quantity[;itemId:skuId:quantity]

        for (int i = 0; i < mProductDetail.getSku().size();i++){
            for (int j = 0; j < mProductDetail.getSku().get(i).getSku().size() ; j ++){
                if (mProductDetail.getSku().get(i).getSku().get(j).getAmount() >= 1){
                    skus = skus + itemId + ":" + mProductDetail.getSku().get(i).getSku().get(j).getId() +":" + mProductDetail.getSku().get(i).getSku().get(j).getAmount() +";";
                }
            }
        }

        return skus;//ShoppingUtils.getSkus(itemId, skuId, quantity);
    }
}
