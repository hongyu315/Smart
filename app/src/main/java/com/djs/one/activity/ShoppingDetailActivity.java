package com.djs.one.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import com.djs.one.R;
import com.djs.one.adapter.ShoppingTitleBarAdapter;
import com.djs.one.bean.MenuBean;
import com.djs.one.bean.Product;
import com.djs.one.fragment.ChildFragment;
import com.djs.one.view.ViewHolder;

public class ShoppingDetailActivity extends FragmentActivity implements View.OnClickListener {

    public static int amount = 1;
    public static Activity mActivity;
    public static TextView mBtnQuantityMinus;
    public static TextView mBtnQuantityPlus;
    public static Button mBtnSubmit;
    public static EditText mEtQuantityInput;
    public static ImageButton mIbClose;
    public static ImageView mIvLogo;
    public static LinearLayout mLlPrice;
    public static TextView mTvInfo;
    public static TextView mTvQuantity;
    public static TextView mTvQuantityLabel;
    public static TextView mTvSellingPrice;
    public static TextView mTvSellingPriceUnit;
    public static Product product = null;
    public static ViewPager viewPager;
    private ShoppingTitleBarAdapter mAdapter;
    public TextView mAddShoppingCart;
    private ArrayList<Fragment> mFragments = new ArrayList();
    public TextView mGoPay;
    public ImageView mIvLeftImageMenu;
    public ImageView mIvRightImageMenu;
    public SlidingTabLayout mTl3;
    private ArrayList<MenuBean> titles;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);

        mActivity = this;
        initView();
    }

    private void initVPager()
    {
        int i = 0;
        while (i < this.titles.size())
        {
            this.mFragments.add(ChildFragment.getInstance(this.titles.get(i).getMenuName()));
            i += 1;
        }
        View localView = getWindow().getDecorView();
        viewPager = ViewHolder.get(localView,R.id.vPager);
        viewPager.setOffscreenPageLimit(this.titles.size());
        this.mAdapter = new ShoppingTitleBarAdapter(getSupportFragmentManager(), this.mFragments, this.titles);
        viewPager.setAdapter(this.mAdapter);
        ((SlidingTabLayout)ViewHolder.get(localView, R.id.tl_3)).setViewPager(viewPager);
    }

    public static void onGoodsCheckDialog(boolean paramBoolean)
    {
        final Dialog localDialog = new Dialog(mActivity, R.style.BottomDialog);
        View localView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_product, null);
        mIbClose = localView.findViewById(R.id.ib_close);
        mTvInfo = localView.findViewById(R.id.tv_info);
        mTvSellingPrice = localView.findViewById(R.id.tv_selling_price);
        mTvSellingPriceUnit = localView.findViewById(R.id.tv_selling_price_unit);
        mLlPrice = localView.findViewById(R.id.ll_price);
        mTvQuantityLabel = localView.findViewById(R.id.tv_quantity_label);
        mTvQuantity = localView.findViewById(R.id.tv_quantity);
        mBtnQuantityMinus = localView.findViewById(R.id.btn_quantity_minus);
        mEtQuantityInput = localView.findViewById(R.id.et_quantity_input);
        mBtnQuantityPlus = localView.findViewById(R.id.btn_quantity_plus);
        mBtnSubmit = localView.findViewById(R.id.btn_submit);
        mIvLogo = localView.findViewById(R.id.iv_logo);
        mIbClose.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                localDialog.dismiss();
            }
        });
        Glide.with(mActivity).load(product.getThumb_url()).into(mIvLogo);
        mTvSellingPrice.setText("3");
        mTvSellingPriceUnit.setText("2");
        mEtQuantityInput.setText("1");
        mBtnQuantityMinus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (ShoppingDetailActivity.amount > 1)
                {
                    ShoppingDetailActivity.amount -= 1;
                    ShoppingDetailActivity.mEtQuantityInput.setText(String.valueOf(ShoppingDetailActivity.amount));
                }
            }
        });
        mBtnQuantityPlus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (ShoppingDetailActivity.amount > 0)
                {
                    ShoppingDetailActivity.amount += 1;
                    ShoppingDetailActivity.mEtQuantityInput.setText(String.valueOf(ShoppingDetailActivity.amount));
                }
            }
        });
        mBtnSubmit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
//                ShoppingDetailActivity.product.setCurrencyUnit(ShoppingDetailActivity.mEtQuantityInput.getText().toString());
//                if (!this.val$external)
//                {
//                    paramAnonymousView = new Bundle();
//                    paramAnonymousView.putSerializable("product", ShoppingDetailActivity.product);
//                    SysUtils.startActivity(ShoppingDetailActivity.mActivity, MainActivity.class, paramAnonymousView);
//                    ShoppingDetailActivity.mActivity.finish();
//                    return;
//                }
//                localDialog.dismiss();
            }
        });
        localDialog.setContentView(localView);
        ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
        localLayoutParams.width = mActivity.getResources().getDisplayMetrics().widthPixels;
        localView.setLayoutParams(localLayoutParams);
        localDialog.getWindow().setGravity(80);
//        localDialog.getWindow().setWindowAnimations(R.anim.BottomDialog.Animation);
        localDialog.show();
    }

    public static void setCurrentItem()
    {
        viewPager.setCurrentItem(2);
    }

    public void initView()
    {
        this.mIvLeftImageMenu = findViewById(R.id.iv_left_image_menu);
        this.mTl3 = findViewById(R.id.tl_3);
        this.mIvRightImageMenu = findViewById(R.id.iv_right_image_menu);
        this.mAddShoppingCart = findViewById(R.id.add_shopping_cart);
        this.mGoPay = findViewById(R.id.go_pay);
        this.mIvLeftImageMenu.setImageResource(R.mipmap.back);
        this.mIvRightImageMenu.setImageResource(R.mipmap.share);
        this.mIvLeftImageMenu.setOnClickListener(this);
        this.mIvRightImageMenu.setOnClickListener(this);
        this.mAddShoppingCart.setOnClickListener(this);
        this.mGoPay.setOnClickListener(this);
        onGetData();
        initVPager();
    }

    public void onGetData()
    {
        this.titles = new ArrayList();
        int i = 0;
        while (i < 3)
        {
            MenuBean localMenuBean = new MenuBean();
            localMenuBean.setId(i);
            localMenuBean.setMenuId(i + "");
            if (i == 0) {
                localMenuBean.setMenuName("商品");
            }
            if (i == 1) {
                localMenuBean.setMenuName("详情");
            }
            if (i == 2) {
                localMenuBean.setMenuName("评价");
            }
            this.titles.add(localMenuBean);
            i += 1;
        }
        product = new Product();
//        product.setType("tpe");
//        product.setUrl("https://img.zcool.cn/community/01757d5a6a7557a8012134664d0391.jpg@2o.jpg");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bottomMenu:
            default:
                return;
            case R.id.iv_left_image_menu:
                finish();
                return;
            case R.id.iv_right_image_menu:
//                SysUtils.startActivity(this, SocicalActivity.class);
                return;
            case R.id.add_shopping_cart:
                onGoodsCheckDialog(false);
                return;
        }
//        SysUtils.startActivity(this, SocicalActivity.class);
    }
}
