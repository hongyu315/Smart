package com.com.one.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import com.com.one.ForOneApplication;
import com.com.one.R;
import com.com.one.adapter.DialogSizeItemAdapter;
import com.com.one.api.API;
import com.com.one.api.URL;
import com.com.one.bean.ProductDetailBannerBean;
import com.com.one.bean.ProductDetailBean;
import com.com.one.bean.SuccessfulModeBean;
import com.com.one.constant.Constant;
import com.com.one.manager.TokenManager;
import com.com.one.util.DensityUtil;
import com.com.one.util.SysUtils;
import com.com.one.util.ToastUtils;
import com.com.one.view.AmountView;
import com.com.one.view.Banner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    private Banner banner;
    private List<String> bannerUrlList = new ArrayList<>();
    private LinearLayout wechatLayout,friendGroupLayout;

    //商品id
    private String itemId;

    private ProductDetailBean.ProductDetail mProductDetail;

    /**
     * 详情页子view
     */
    TextView titleTV, priceTV,brandStoryTV,detailCompositionTV;
    WebView contentWV;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        StatusBarUtil.setTranslucent(ProductDetailActivity.this,100);

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

    }

    @Override
    protected void initData() {
        super.initData();

        try {
            Intent intent = getIntent();
            itemId = intent.getStringExtra("itemId");

            if (!TextUtils.isEmpty(itemId)){
                getProductDetailBanner();
                getProductDetail();
            }
        }catch (Exception e){
        }
    }

    private void initBanner(List<ProductDetailBannerBean.ProductBanner> bannerList){
        try{
            HttpProxyCacheServer proxy = ForOneApplication.getProxy(getApplicationContext());
            for (ProductDetailBannerBean.ProductBanner banner : bannerList){
                if (banner.getMedia_type() == 1){
                    bannerUrlList.add(banner.getMedia_url());
                }else if (banner.getMedia_type() == 2){
                    bannerUrlList.add(proxy.getProxyUrl(banner.getMedia_url(),true));
                }
            }

            banner.setDataList(ProductDetailActivity.this, bannerUrlList);
//            banner.startBanner();
        }catch (Exception e){
        }
    }

    private void getProductDetailBanner(){
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
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        initBanner(productBean.getData());
                    }else {
                        ToastUtils.showToast(ProductDetailActivity.this,response.body().getMessage());
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

    private void getProductDetail(){
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
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        mProductDetail = productBean.getData();
                        setData();
                    }else {
                        ToastUtils.showToast(ProductDetailActivity.this,response.body().getMessage());
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

    private void setData(){
        try{
            if (mProductDetail != null){
                titleTV.setText(mProductDetail.getTitle());
                priceTV.setText("￥" + mProductDetail.getMarket_price());
                detailCompositionTV.setText(mProductDetail.getComposition());
                brandStoryTV.setText(mProductDetail.getStory());
                String html = "<html>\n" +
                        "\n" +
                        "    <head>\n" +
                        "        <meta charset=\"UTF-8\"/>\n" +
                        "    </head>\n" +
                        "\n" +
                        "    <body>\n" +
                        "\n" +
                        "    <p>这是一个段落噻！</p>\n" +
                        "\n" +
                        "    </body>\n" +
                        "\n" +
                        "    <script>\n" +
                        "\n" +
                        "        function callJS(){\n" +
                        "            alert(\"Android call js method!\");\n" +
                        "        }\n" +
                        "\n" +
                        "    </script>\n" +
                        "\n" +
                        "</html>\n";
                contentWV.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
//                contentWV.loadUrl("https://www.m.iqiyi.com");
            }
        }catch (Exception e){
        }
    }

    public void onShareBtnClick(View view){

        Dialog dialog = new Dialog(ProductDetailActivity.this,R.style.MyDialog);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (SysUtils.getScreenWidth(this) * 0.7);
        lp.height = (int) (SysUtils.getScreenHeight(this) * 0.63);
        dialogWindow.setAttributes(lp);
        dialog.setContentView(R.layout.product_detail_share);
        dialogWindow.findViewById(R.id.wechat_layout).setOnClickListener(this);
        dialogWindow.findViewById(R.id.friend_group_layout).setOnClickListener(this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    private void onFavoriteBtnClick(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<SuccessfulModeBean> products = api.collect(TokenManager.getInstance().getLoginToken().getData().getToken(),itemId);
        products.enqueue(new Callback<SuccessfulModeBean>() {
            @Override
            public void onResponse(Call<SuccessfulModeBean> call, Response<SuccessfulModeBean> response) {

                try {
                    SuccessfulModeBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        ToastUtils.showToast(ProductDetailActivity.this,productBean.getData());
                    }else {
                        ToastUtils.showToast(ProductDetailActivity.this,response.body().getMessage());
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
     * @param v
     */
    int sizeListSelectedPosition = 0;
    int colorListSelectedPosition = 0;
    int amountInDialog = 1;
    public void onAddToShoppingCarBtnClick(View v){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(ProductDetailActivity.this,R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(ProductDetailActivity.this,R.layout.dialog_bottom_layout,null);

        ListView sizeListView = view.findViewById(R.id.dialog_size_list);
        List<String> items = new ArrayList<>();
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        final DialogSizeItemAdapter adapter = new DialogSizeItemAdapter(ProductDetailActivity.this,items);
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

        final List<TextView> colorTextViews = new ArrayList<>(colors.size());

        for (int i = 0; i < colors.size(); i++){
            final TextView colorView = new TextView(ProductDetailActivity.this);
            colorView.setTextSize(12);
            colorView.setTextColor(getResources().getColor(R.color.home_glod_text_unselect));
            colorView.setBackground(getResources().getDrawable(R.drawable.bg_amount_layout));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,DensityUtil.dp2px(ProductDetailActivity.this,10),0);
            colorView.setPadding(DensityUtil.dp2px(ProductDetailActivity.this,9),
                    DensityUtil.dp2px(ProductDetailActivity.this,6),
                    DensityUtil.dp2px(ProductDetailActivity.this,9),
                    DensityUtil.dp2px(ProductDetailActivity.this,6));
            colorView.setLayoutParams(lp);
            colorView.setText(colors.get(i));
            colorView.setTag(i);
            colorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change other text status
                    for(TextView textView : colorTextViews){
//                        textView.setBackgroundColor(ProductDetailActivity.this.getResources().getColor(R.color.white));
                        textView.setTextColor(ProductDetailActivity.this.getResources().getColor(R.color.home_glod_text_unselect));
                        textView.setBackground(getResources().getDrawable(R.drawable.bg_amount_layout));
                        textView.setPadding(DensityUtil.dp2px(ProductDetailActivity.this,9),
                                DensityUtil.dp2px(ProductDetailActivity.this,6),
                                DensityUtil.dp2px(ProductDetailActivity.this,9),
                                DensityUtil.dp2px(ProductDetailActivity.this,6));
                    }
                    colorListSelectedPosition = (int) v.getTag();
                    colorView.setBackgroundColor(ProductDetailActivity.this.getResources().getColor(R.color.home_glod_text_unselect));
                    colorView.setTextColor(ProductDetailActivity.this.getResources().getColor(R.color.white));

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

    public void onWechatBtnClick(){
        Log.e("xxxxxxx","onWechatBtnClick click");
    }

    public void onFriendGroupBtnClick(){
        Log.e("xxxxxxx","onFriendGroupBtnClick click");
    }

    @Override
    protected void onDestroy() {
        banner.destroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
        }
    }
}
