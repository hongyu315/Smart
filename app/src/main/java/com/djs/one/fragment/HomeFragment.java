package com.djs.one.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djs.one.R;
import com.djs.one.activity.ProductDetailActivity;
import com.djs.one.activity.SearchActivity;
import com.djs.one.adapter.ProductAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.Product;
import com.djs.one.bean.ProductBean;
import com.djs.one.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.djs.one.constant.Constant.From_Product;

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener, AdapterView.OnItemClickListener {

    protected Activity mActivity;

    private List<Product> productList = new ArrayList();
    private List<Product> summerPL = new ArrayList();
    private List<Product> autumnPl = new ArrayList();
    private List<Product> winterPl = new ArrayList();

    //上架时间
    private TextView onStoreTextView;
    private ImageView onStoreArrowUp,onStoreArrowDown;

    //产品价格
    private TextView onStorePriceText;
    private ImageView onStorePriceArrowUp,onStorePriceArrowDown;

    private SwipeToLoadLayout swipeToLoadLayout,summerLoadLayout,autumnLoadLayout,winterLoadLayout;

    private GridView gridView,summerGridView,autumnGridView,winterGridView;
    private ProductAdapter adapter,summerAdapter,autumnAdapter,winterAdapter;

    private int page,summerPage,autumnPage,winterPage = 1;

    //按上架时间排序，1 倒序 2 升序，默认 1
    private String saleTimeSortType = Constant.SORT_TYPE_INVERTED;

    //按商品价格排序，1 倒序 2 升序，默认 2
    private String priceSortType = Constant.SORT_TYPE_ASCENDING;

    private Button sprintBtn,SummerBtn,AutumnBtn,WinterBtn;
    private LinearLayout sprintLayout,summerLayout,automnLayout,winterLayout;

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<LinearLayout> layouts = new ArrayList<>();
    private ArrayList<SwipeToLoadLayout> swipeToLoadLayouts = new ArrayList<>();
    private ArrayList<GridView> gridViews = new ArrayList<>();
    private ArrayList<List<Product> > productListMap = new ArrayList<>();
    private ArrayList<ProductAdapter> productAdapters = new ArrayList<>();


    public HomeFragment() {
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        if (this.mActivity == null){
            return;
        }

        initViews(paramView);
        initData();
    }

    protected void initViews(View paramView){

        paramView.findViewById(R.id.home_search_layout).setOnClickListener(this);

        //上架时间
        paramView.findViewById(R.id.home_on_store_text_layout).setOnClickListener(this);
        onStoreTextView = paramView.findViewById(R.id.home_on_store_time_text);
        onStoreArrowUp = paramView.findViewById(R.id.home_on_store_time_arrow_up);
        onStoreArrowDown = paramView.findViewById(R.id.home_on_store_time_arrow_down);

        //产品价格
        paramView.findViewById(R.id.home_on_store_price_layout).setOnClickListener(this);
        onStorePriceText = paramView.findViewById(R.id.home_on_store_price_text);
        onStorePriceArrowUp = paramView.findViewById(R.id.home_on_store_price_arrow_up);
        onStorePriceArrowDown = paramView.findViewById(R.id.home_on_store_price_arrow_down);

        sprintBtn = paramView.findViewById(R.id.spring_btn);
        SummerBtn = paramView.findViewById(R.id.summer_btn);
        AutumnBtn = paramView.findViewById(R.id.autumn_btn);
        WinterBtn = paramView.findViewById(R.id.winter_btn);

        buttons.add(sprintBtn);
        buttons.add(SummerBtn);
        buttons.add(AutumnBtn);
        buttons.add(WinterBtn);

        sprintLayout = paramView.findViewById(R.id.spring_layout);
        summerLayout = paramView.findViewById(R.id.summer_layout);
        automnLayout = paramView.findViewById(R.id.autumn_layout);
        winterLayout = paramView.findViewById(R.id.winter_layout);

        layouts.add(sprintLayout);
        layouts.add(summerLayout);
        layouts.add(automnLayout);
        layouts.add(winterLayout);

        sprintBtn.setPressed(true);

        swipeToLoadLayout = paramView.findViewById(R.id.swipeToLoadLayout);
        summerLoadLayout = paramView.findViewById(R.id.summer_swipeToLoadLayout);
        autumnLoadLayout = paramView.findViewById(R.id.autumn_swipeToLoadLayout);
        winterLoadLayout = paramView.findViewById(R.id.winter_swipeToLoadLayout);

        swipeToLoadLayouts.add(swipeToLoadLayout);
        swipeToLoadLayouts.add(summerLoadLayout);
        swipeToLoadLayouts.add(autumnLoadLayout);
        swipeToLoadLayouts.add(winterLoadLayout);

        summerLoadLayout.setVisibility(View.GONE);
        automnLayout.setVisibility(View.GONE);
        winterLoadLayout.setVisibility(View.GONE);

        gridView = paramView.findViewById(R.id.swipe_target);
        summerGridView = summerLoadLayout.findViewById(R.id.swipe_target);
        autumnGridView = autumnLoadLayout.findViewById(R.id.swipe_target);
        winterGridView = winterLoadLayout.findViewById(R.id.swipe_target);

        gridViews.add(gridView);
        gridViews.add(summerGridView);
        gridViews.add(autumnGridView);
        gridViews.add(winterGridView);

        adapter = new ProductAdapter(getActivity(),productList);
        summerAdapter = new ProductAdapter(getActivity(),summerPL);
        autumnAdapter = new ProductAdapter(getActivity(),autumnPl);
        winterAdapter = new ProductAdapter(getActivity(),winterPl);

        productAdapters.add(adapter);
        productAdapters.add(summerAdapter);
        productAdapters.add(autumnAdapter);
        productAdapters.add(winterAdapter);

        for (int i = 0; i < 4; i++){
            buttons.get(i).setOnClickListener(this);
            gridViews.get(i).setSelector(new ColorDrawable(Color.TRANSPARENT));
            gridViews.get(i).setOnItemClickListener(this);
            swipeToLoadLayouts.get(i).setOnRefreshListener(this);
            swipeToLoadLayouts.get(i).setOnLoadMoreListener(this);

            gridViews.get(i).setAdapter(productAdapters.get(i));
        }

    }

    private void onSeasonBtnClick(int index){
        for (int i = 1; i <= 4; i++){
            if (i-1 == index){
                buttons.get(i-1).setFocusableInTouchMode(true);
                buttons.get(i-1).setPressed(true);
                buttons.get(i-1).setFocusable(true);
                buttons.get(i-1).setSelected(true);
                layouts.get(i-1).setVisibility(View.VISIBLE);
            }else {
                buttons.get(i-1).setFocusableInTouchMode(false);
                buttons.get(i-1).setPressed(false);
                buttons.get(i-1).setFocusable(false);
                buttons.get(i-1).setSelected(false);
                layouts.get(i-1).setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 搜索按钮点击
     */
    public void onSearchLayoutClick(){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra("index",From_Product);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 上架时间点击排序
     */
    public void onStoreTextViewClick(){
        onStoreTextView.setTextColor(getResources().getColor(R.color.home_glod_text_select));
        onStorePriceText.setTextColor(getResources().getColor(R.color.home_glod_text_unselect));
        onStorePriceArrowUp.setImageDrawable(getResources().getDrawable(R.mipmap.home_arrow_unselect_up));
        onStorePriceArrowDown.setImageDrawable(getResources().getDrawable(R.mipmap.home_arrow_unselect_down));

        boolean selected = Boolean.valueOf(onStoreTextView.getTag() + "");

       //为被选中，把状态改为选中，同时设置箭头
        onStoreArrowUp.setImageDrawable(selected ? getResources().getDrawable(R.mipmap.home_arrow_selected_up) : getResources().getDrawable(R.mipmap.home_arrow_unselect_up));
        onStoreArrowDown.setImageDrawable(selected ? getResources().getDrawable(R.mipmap.home_arrow_unselect_down) : getResources().getDrawable(R.mipmap.home_arrow_selected_down));

        onStoreTextView.setTag(String.valueOf(!selected));

        saleTimeSortType = selected ? Constant.SORT_TYPE_INVERTED : Constant.SORT_TYPE_ASCENDING;
        getProductList();
    }

    /**
     * 上架时间点击排序
     */
    public void onStorePriceViewClick(){
        onStorePriceText.setTextColor(getResources().getColor(R.color.home_glod_text_select));
        onStoreTextView.setTextColor(getResources().getColor(R.color.home_glod_text_unselect));
        onStoreArrowUp.setImageDrawable(getResources().getDrawable(R.mipmap.home_arrow_unselect_up));
        onStoreArrowDown.setImageDrawable(getResources().getDrawable(R.mipmap.home_arrow_unselect_down));

        boolean selected = Boolean.valueOf(onStorePriceText.getTag() + "");

        //为被选中，把状态改为选中，同时设置箭头
        onStorePriceArrowUp.setImageDrawable(selected ? getResources().getDrawable(R.mipmap.home_arrow_selected_up) : getResources().getDrawable(R.mipmap.home_arrow_unselect_up));
        onStorePriceArrowDown.setImageDrawable(selected ? getResources().getDrawable(R.mipmap.home_arrow_unselect_down) : getResources().getDrawable(R.mipmap.home_arrow_selected_down));

        onStorePriceText.setTag(String.valueOf(!selected));

        priceSortType = selected ? Constant.SORT_TYPE_ASCENDING : Constant.SORT_TYPE_INVERTED;
        getProductList();
    }

    private void getProductList(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductBean> products = api.getProductList(Constant.PRODUCT_SIZE,
                page + "",
                saleTimeSortType,
                priceSortType,
                "");
        products.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);

                try {
                    ProductBean productBean = response.body();

                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        if (productBean.getData() != null && productBean.getData().getList() != null && productBean.getData().getList().size() > 0){
                            if (productList != null && productList.size() > 0){
                                productList.clear();
                            }

                            productList.addAll(productBean.getData().getList());
                            summerPL.addAll(productList);
                            autumnPl.addAll(productList);
                            winterPl.addAll(productList);

                            adapter.notifyDataSetChanged();
                            summerAdapter.notifyDataSetChanged();
                            autumnAdapter.notifyDataSetChanged();
                            winterAdapter.notifyDataSetChanged();
                        }
                    }else {
//                        ToastUtils.showToast(getActivity(),response.body().getMessage());
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);
//                ToastUtils.showToast(getActivity(), t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        getProductList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_search_layout:
                onSearchLayoutClick();
                break;
            case R.id.home_on_store_text_layout:
                onStoreTextViewClick();
                break;
            case R.id.home_on_store_price_layout:
                onStorePriceViewClick();
                break;
            case R.id.spring_btn:
                onSeasonBtnClick(0);
                break;
            case R.id.summer_btn:
                onSeasonBtnClick(1);
                break;
            case R.id.autumn_btn:
                onSeasonBtnClick(2);
                break;
            case R.id.winter_btn:
                onSeasonBtnClick(3);
                break;
            default:
                return;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getProductList();
            }
        }, 10);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = page + 1;
                getProductList();
            }
        }, 10);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = productList.get(position);
        String itemId = product.getId() + "";

        Intent intent = new Intent(getActivity(),ProductDetailActivity.class);
        intent.putExtra("itemId",itemId);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }
}
