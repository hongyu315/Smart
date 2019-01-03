package hongyu315.com.smart2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.ShoppingDetailActivity;
import hongyu315.com.smart2.adapter.MyPagerAdapter;
import hongyu315.com.smart2.adapter.SearchAdapter;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.TopTitleBarView;

public class MallFragment extends BaseFragment implements View.OnClickListener {

    private static final int TAB_0 = 0;
    private static final int TAB_1 = 1;
    private static final int TAB_2 = 2;
    private static final int TAB_COUNT = 3;
    protected String TAG = "";
    private MyPagerAdapter adapter;
    private TextView artwork;
    private LinearLayout artwork_layout;
    private int current_index = 0;
    private ImageView cursorIv;
    private TextView derivatives;
    private LinearLayout derivatives_layout;
    private DrawerLayout drawerLayout;
    private boolean isShowBoolean = false;
//    private LinearLayout layout;
    private int lineWidth;
    private View listItem;
    private View listView;
    private List<Fragment> lists;
    protected Activity mActivity;
    protected FragmentManager mFragmentManager;
    public ImageView mImRightSearch;
    private NavigationView navigationView;
    private int offset = 0;
    private RelativeLayout screening;
    private SearchAdapter screeningAdapter;
    private ImageView screeningImagView;
    private TextView screening_text;
    private TopTitleBarView titleBarView;
    private ViewPager viewPager;

    public MallFragment() {
    }

    public static MallFragment newInstance() {
        return new MallFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        findViews(view);
        initVPager();
    }

    protected void findViews(View paramView){
        this.titleBarView = ((TopTitleBarView) paramView.findViewById(R.id.topTitleBarView));
        this.titleBarView.mTvLeftImageMenu.setOnClickListener(this);
        this.drawerLayout = ((DrawerLayout) paramView.findViewById(R.id.home_fragment_drawer));
        this.navigationView = ((NavigationView) paramView.findViewById(R.id.navigation));

        this.screening = ((RelativeLayout)paramView.findViewById(R.id.screening));
        this.screeningImagView = ((ImageView)paramView.findViewById(R.id.screening_image));
        this.screeningImagView.setImageResource(R.mipmap.more_bottom);
        this.artwork_layout = ((LinearLayout)paramView.findViewById(R.id.artwork_layout));
        this.derivatives_layout = ((LinearLayout)paramView.findViewById(R.id.derivatives_layout));
        this.screening_text = ((TextView)paramView.findViewById(R.id.screening_text));
        this.artwork = ((TextView)paramView.findViewById(R.id.artwork));
        this.derivatives = ((TextView)paramView.findViewById(R.id.derivatives));

//        this.layout = ((LinearLayout)this.mActivity.getLayoutInflater().inflate(2130968674, null, false));
        this.viewPager = ((ViewPager)paramView.findViewById(R.id.vPager));
        this.cursorIv = ((ImageView)paramView.findViewById(R.id.iv_tab_bottom_img));

        this.navigationView.setNavigationItemSelectedListener(new NavigationItemSelected());
        this.screening_text.setOnClickListener(this);
        this.artwork.setOnClickListener(this);
        this.derivatives.setOnClickListener(this);

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

    private void initVPager(){
        this.lists = new ArrayList<>();
        this.lists.add(new ScreeningFragment());
        this.lists.add(new ArtWorkFragment());
        this.lists.add(new DeriveFragment());
        this.adapter = new MyPagerAdapter(getChildFragmentManager(), this.lists);
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setCurrentItem(0);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int one = MallFragment.this.offset * 2 + MallFragment.this.lineWidth;
                TranslateAnimation localTranslateAnimation = new TranslateAnimation(one * MallFragment.this.current_index, one * position, 0.0F, 0.0F);
                localTranslateAnimation.setFillAfter(true);
                localTranslateAnimation.setDuration(500L);
                MallFragment.this.cursorIv.startAnimation(localTranslateAnimation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
            MallFragment.this.drawerLayout.closeDrawers();
            this.mPreMenuItem = paramMenuItem;
            SysUtils.startActivity(mActivity,ShoppingDetailActivity.class);
            return true;
        }
    }
}
