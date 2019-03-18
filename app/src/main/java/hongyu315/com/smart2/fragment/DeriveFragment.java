package hongyu315.com.smart2.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superrecycleview.superlibrary.recycleview.ProgressStyle;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.ScreenAdapter;
import hongyu315.com.smart2.api.API;
import hongyu315.com.smart2.api.URL;
import hongyu315.com.smart2.bean.Product;
import hongyu315.com.smart2.bean.ProductList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeriveFragment extends BaseFragment {

    private SuperRecyclerView listView;
    private List<Product> productList = new ArrayList();

    public DeriveFragment() {
    }

    public static DeriveFragment newInstance(String param1, String param2) {
        return new DeriveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_derive, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);

        findViews(paramView);
        initData();
        initListener();
    }

    @Override
    protected void findViews(View paramView) {
        super.findViews(paramView);

        listView = paramView.findViewById(R.id.derive_fragment_super_recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        listView.setLayoutManager(layoutManager);
        listView.setRefreshEnabled(true);
        listView.setLoadMoreEnabled(true);
        listView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        listView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        listView.setArrowImageView(R.mipmap.iconfont_downgrey);

    }

    @Override
    protected void initData() {
        super.initData();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ProductList> products = api.getProducts("1");
        products.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

                try {
                    String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLpqf2EGjTYjuw207W6eKF1oL-pJjxtpSm8Ii0K3ndGHF6LQfk";
                    String url1 = "https://img.zcool.cn/community/01757d5a6a7557a8012134664d0391.jpg@2o.jpg";
                    for (int i = 0; i < 4; i++) {
                        Product product = new Product();
//                        product.setType("测试衍生商品 " + i);
                        product.setThumb_url(url1);
                        productList.add(product);
                    }

//                    productList = response.body();
                    if (productList != null){
                        ScreenAdapter adapter = new ScreenAdapter(getActivity(),productList);

                        listView.setAdapter(adapter);
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Log.e(TAG, "onFailure: fail" );
            }
        });
    }

    private void initListener(){
        listView.setLoadingListener(new SuperRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh: finish");
                String url1 = "https://img.zcool.cn/community/01757d5a6a7557a8012134664d0391.jpg@2o.jpg";
                for (int i = 0; i < 4; i++) {
                    Product product = new Product();
//                    product.setType("测试衍生商品 " + i);
                    product.setThumb_url(url1);
                    productList.add(product);
                }
                listView.completeRefresh();
            }

            @Override
            public void onLoadMore() {
                Log.e(TAG, "onRefresh: load more finish");
                String url1 = "https://img.zcool.cn/community/01757d5a6a7557a8012134664d0391.jpg@2o.jpg";
                for (int i = 0; i < 4; i++) {
                    Product product = new Product();
//                    product.setType("测试衍生商品 " + i);
//                    product.setUrl(url1);
                    productList.add(product);
                }
                listView.completeLoadMore();
            }
        });
    }





}
