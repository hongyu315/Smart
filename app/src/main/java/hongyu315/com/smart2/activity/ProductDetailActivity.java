package hongyu315.com.smart2.activity;

import android.os.Bundle;

import com.danikula.videocache.HttpProxyCacheServer;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.ForOneApplication;
import hongyu315.com.smart2.R;
import hongyu315.com.smart2.view.Banner;

public class ProductDetailActivity extends BaseActivity {

    private Banner banner;
    private List<String> bannerUrlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        banner = findViewById(R.id.product_detail_banner);

    }

    @Override
    protected void initData() {
        super.initData();

        HttpProxyCacheServer proxy = ForOneApplication.getProxy(getApplicationContext());
        String proxyUrl = proxy.getProxyUrl("http://yyt.lexin580.com:8080/app_config/ztj08.mp4");
        String proxyUrl2 = proxy.getProxyUrl("http://yyt.lexin580.com:8080/app_config/ztj08.mp4");

        bannerUrlList = new ArrayList<>();
        bannerUrlList.add(proxyUrl);
//        bannerUrlList.add(proxyUrl2);
        bannerUrlList.add("http://img2.imgtn.bdimg.com/it/u=3817131034,1038857558&fm=27&gp=0.jpg");
        bannerUrlList.add("http://img1.imgtn.bdimg.com/it/u=4194723123,4160931506&fm=200&gp=0.jpg");
        bannerUrlList.add("http://img5.imgtn.bdimg.com/it/u=1812408136,1922560783&fm=27&gp=0.jpg");

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                banner.setDataList(ProductDetailActivity.this,bannerUrlList);
                banner.startBanner();
//            }
//        },500);

    }


    @Override
    protected void onDestroy() {
        banner.destroy();
        super.onDestroy();
    }
}
