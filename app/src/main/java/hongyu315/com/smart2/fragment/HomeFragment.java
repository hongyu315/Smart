package hongyu315.com.smart2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.superrecycleview.superlibrary.recycleview.ProgressStyle;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.ShoppingDetailActivity;
import hongyu315.com.smart2.adapter.HomeFragmentHeaderAdapter;
import hongyu315.com.smart2.bean.TabEntity;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.TopTitleBarView;

public class HomeFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    protected Activity mActivity;
    private TopTitleBarView titleBarView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SuperRecyclerView superRecyclerView;
    private HomeFragmentHeaderAdapter headerAdapter;

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

        initData();
        initViews(paramView);
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList arrayList = new ArrayList();
        TabEntity entity = new TabEntity("111",2,2);
        arrayList.add(entity);

        headerAdapter = new HomeFragmentHeaderAdapter(getActivity(),arrayList);
    }

    protected void initViews(View paramView){
        this.titleBarView = ((TopTitleBarView) paramView.findViewById(R.id.topTitleBarView));
        this.titleBarView.mTvLeftImageMenu.setOnClickListener(this);
        this.drawerLayout = ((DrawerLayout) paramView.findViewById(R.id.home_fragment_drawer));
        this.navigationView = ((NavigationView) paramView.findViewById(R.id.navigation));
        this.superRecyclerView = ((SuperRecyclerView) paramView.findViewById(R.id.super_recycler_view));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.superRecyclerView.setLayoutManager(layoutManager);
        superRecyclerView.setRefreshEnabled(true);//可以定制是否开启下拉刷新
        superRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//可以自定义下拉刷新的样式
        superRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);//可以自定义上拉加载的样式
        superRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);//设置下拉箭头
        View header = getLayoutInflater().inflate(R.layout.fragment_home_header,(ViewGroup)superRecyclerView.getParent(),false);


        headerAdapter.addHeaderView(header);
        this.navigationView.setNavigationItemSelectedListener(new NavigationItemSelected());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                return;
            case R.id.top_title_bar_menu:
                onTopTitleBarMenuClick();
                return;
        }
    }

    private void onTopTitleBarMenuClick(){
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.drawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }
        this.drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onRefresh() {

    }

    public class NavigationItemSelected
            implements NavigationView.OnNavigationItemSelectedListener
    {
        private MenuItem mPreMenuItem;

        public NavigationItemSelected() {}

        public boolean onNavigationItemSelected(MenuItem paramMenuItem)
        {
            if (this.mPreMenuItem != null) {
                this.mPreMenuItem.setChecked(false);
            }
            switch (paramMenuItem.getItemId())
            {

            }

            paramMenuItem.setChecked(true);
            HomeFragment.this.drawerLayout.closeDrawers();
            this.mPreMenuItem = paramMenuItem;
            SysUtils.startActivity(mActivity,ShoppingDetailActivity.class);
            return true;
        }
    }
}
