package hongyu315.com.smart2.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.SearchActivity;
import hongyu315.com.smart2.adapter.UserCenterProductAdapter;
import hongyu315.com.smart2.bean.Product;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.GlideImageLoader;
import hongyu315.com.smart2.view.TopTitleBarView;

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener {

    protected Activity mActivity;
    private TopTitleBarView titleBarView;

    private List<Product> productList = new ArrayList();
    private UserCenterProductAdapter adapter;

    private SwipeToLoadLayout swipeToLoadLayout;

    private ScrollView containerLayout;
    private LinearLayout mainLinearLayout;

    private View headerLayout;
    private View bodyLayout;
    private View footerLayout;

    private Banner banner;
    private List imageUrls = new ArrayList();

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

    @Override
    protected void initData() {
        super.initData();
    }

    protected void initViews(View paramView){
        this.titleBarView = ((TopTitleBarView) paramView.findViewById(R.id.topTitleBarView));
        this.titleBarView.mTvLeftImageMenu.setOnClickListener(this);
        this.titleBarView.mTvRightSearch.setOnClickListener(this);

        swipeToLoadLayout = (SwipeToLoadLayout) paramView.findViewById(R.id.swipeToLoadLayout);
        containerLayout = (ScrollView) paramView.findViewById(R.id.swipe_target);
        mainLinearLayout = new LinearLayout(getActivity());
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mainLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        containerLayout.addView(mainLinearLayout);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerLayout = inflater.inflate(R.layout.home_banner_layout,null);
        bodyLayout = inflater.inflate(R.layout.home_body_layout,null);
        footerLayout = inflater.inflate(R.layout.home_footer_layout,null);

        //banner
        banner = (Banner)headerLayout.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());

        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546775631910&di=de0bb4d72a02b67c39286db327f89aa3&imgtype=0&src=http%3A%2F%2F06.imgmini.eastday.com%2Fmobile%2F20180713%2F20180713_7a55642717ce630abd940582bf4c27ed_wmk.jpeg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546775806871&di=b3ccbebcfef0dcf09a09ab7e02740bf4&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20171_31_13%2Fa21fjy4497275431542.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546775837016&di=9197f647063f794db6467d218a900cb2&imgtype=0&src=http%3A%2F%2Fpic.makepolo.net%2Fnews%2Fallimg%2F20161225%2F1482605461532415.jpg");
        banner.setImages(imageUrls);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(banner.getLayoutParams());
        layoutParams.width = SysUtils.getScreenWidth(getActivity());
        layoutParams.height = (int) (SysUtils.getScreenHeight(getActivity()) * 0.8);
        banner.setLayoutParams(layoutParams);
        banner.start();

        mainLinearLayout.addView(headerLayout);
        mainLinearLayout.addView(bodyLayout);
        mainLinearLayout.addView(footerLayout);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setLoadMoreEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                return;
            case R.id.top_title_bar_search:
                Log.e(TAG, "onClick: search" );
                SysUtils.startActivity(getActivity(),SearchActivity.class);
                return;
        }
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }

}
