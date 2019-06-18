package com.djs.one.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.djs.one.bean.ProductCategoryBean;
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

    //上架时间
    private TextView onStoreTextView;
    private ImageView onStoreArrowUp, onStoreArrowDown;

    //产品价格
    private TextView onStorePriceText;
    private ImageView onStorePriceArrowUp, onStorePriceArrowDown;

    private SwipeToLoadLayout swipeToLoadLayout;

    private GridView gridView;
    private ProductAdapter adapter;

    private int page;

    //按上架时间排序，1 倒序 2 升序，默认 1
    private String saleTimeSortType = Constant.SORT_TYPE_INVERTED;

    //按商品价格排序，1 倒序 2 升序，默认 2
    private String priceSortType = Constant.SORT_TYPE_ASCENDING;

    private LinearLayout sprintLayout;
    private ListView mListView;
    private CatagoryAdapater catagoryAdapater;
    private int selectedCategoryId;

    private EditText searchEditText;
    String keywords;
    int categoryId;
    boolean isApendData;

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
        if (this.mActivity == null) {
            return;
        }

        initViews(paramView);
        initData();
    }

    protected void initViews(View paramView) {

        paramView.findViewById(R.id.home_search_layout).setOnClickListener(this);
        //商品分类
        mListView = paramView.findViewById(R.id.categroy_listview);

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
        sprintLayout = paramView.findViewById(R.id.spring_layout);
        swipeToLoadLayout = paramView.findViewById(R.id.swipeToLoadLayout);
        gridView = paramView.findViewById(R.id.swipe_target);
        adapter = new ProductAdapter(getActivity());
        gridView.setAdapter(adapter);
        catagoryAdapater = new CatagoryAdapater();
        mListView.setAdapter(catagoryAdapater);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        gridView.setOnItemClickListener(this);

        searchEditText = paramView.findViewById(R.id.home_fragment_search_edit_text);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_NEXT
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    keywords = searchEditText.getText().toString().trim();
                    getProductList(categoryId,isApendData);
                }

                return false;
            }
        });
    }


    /**
     * 搜索按钮点击
     */
    public void onSearchLayoutClick() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra("index", From_Product);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 上架时间点击排序
     */
    public void onStoreTextViewClick() {
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
    }

    /**
     * 上架时间点击排序
     */
    public void onStorePriceViewClick() {
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
    }

    private void getCataGoryList() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductCategoryBean> catagorys = api.getCategory();
        catagorys.enqueue(new Callback<ProductCategoryBean>() {
            @Override
            public void onResponse(Call<ProductCategoryBean> call, Response<ProductCategoryBean> response) {
                ProductCategoryBean productCategoryBean = response.body();
                if (productCategoryBean != null) {
                    selectedCategoryId = productCategoryBean.getData().get(0).getId();
                    catagoryAdapater.setData(productCategoryBean);
                    categoryId = selectedCategoryId;
                    isApendData = false;
                    getProductList(selectedCategoryId, false);
                }
            }

            @Override
            public void onFailure(Call<ProductCategoryBean> call, Throwable t) {

            }
        });
    }

    private void getProductList(int categoryId, final boolean isApendData) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductBean> products = api.getProductList(Constant.PRODUCT_SIZE,
                page + "",
                categoryId,
                saleTimeSortType,
                priceSortType,
                keywords);
        products.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {
                if (swipeToLoadLayout.isRefreshing()) swipeToLoadLayout.setRefreshing(false);
                if (swipeToLoadLayout.isLoadingMore()) swipeToLoadLayout.setLoadingMore(false);

                try {
                    ProductBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()) {
                        if (productBean.getData() != null && productBean.getData().getList() != null && productBean.getData().getList().size() > 0) {
                            if (productList != null && !isApendData) {
                                productList.clear();
                            }
                            productList.addAll(productBean.getData().getList());
                            adapter.setData(productList);
                            adapter.notifyDataSetChanged();
                            catagoryAdapater.notifyDataSetChanged();
                            if (!isApendData) {
                                gridView.scrollTo(0, 0);
                            }
                        }
                    } else {
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
        getCataGoryList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_search_layout:
                onSearchLayoutClick();
                break;
            case R.id.home_on_store_text_layout:
                onStoreTextViewClick();
                break;
            case R.id.home_on_store_price_layout:
                onStorePriceViewClick();
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
                getProductList(selectedCategoryId, false);
            }
        }, 10);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = page + 1;
                getProductList(selectedCategoryId, true);
            }
        }, 10);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = productList.get(position);
        String itemId = product.getId() + "";

        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra("itemId", itemId);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }

    private class CatagoryAdapater extends BaseAdapter {
        private List<ProductCategoryBean.DataBean> data;

        public void setData(ProductCategoryBean productCategoryBean) {
            if (productCategoryBean != null) {
                data = productCategoryBean.getData();
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public ProductCategoryBean.DataBean getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                TextView name = (TextView) mActivity.getLayoutInflater().inflate(R.layout.category_item_layout, null);
                convertView = name;
            }
            int id = getItem(i).getId();
            if (selectedCategoryId == id) {
                ((TextView) convertView).setText("[" + getItem(i).getName() + "]");

                ((TextView) convertView).setTextColor(mActivity.getResources().getColor(R.color.home_glod_text_select));
            } else {
                ((TextView) convertView).setText(getItem(i).getName());
                ((TextView) convertView).setTextColor(Color.parseColor("#000000"));
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCategoryId = getItem(i).getId();
                    getProductList(selectedCategoryId, false);
                }
            });
            return convertView;
        }
    }
}
