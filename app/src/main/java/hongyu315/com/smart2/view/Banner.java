//package hongyu315.com.smart2.view;
//
//
//import android.content.Context;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//import hongyu315.com.smart2.R;
//import hongyu315.com.smart2.listener.OnBannerClickListener;
//import hongyu315.com.smart2.listener.OnBannerListener;
//import hongyu315.com.smart2.view.loader.ImageLoaderInterface;
//
//public class Banner
//        extends FrameLayout
//        implements ViewPager.OnPageChangeListener
//{
//    private BannerPagerAdapter adapter;
//    private int bannerBackgroundImage;
//    private ImageView bannerDefaultImage;
//    private OnBannerClickListener bannerListener;
//    private int bannerStyle = 1;
//    private TextView bannerTitle;
//    private Context context;
//    private int count = 0;
//    private int currentItem;
//    private int delayTime = 2000;
//    private DisplayMetrics dm;
//    private int gravity = -1;
//    private WeakHandler handler = new WeakHandler();
//    private ImageLoaderInterface imageLoader;
//    private List imageUrls;
//    private List<View> imageViews;
//    private LinearLayout indicator;
//    private List<ImageView> indicatorImages;
//    private LinearLayout indicatorInside;
//    private int indicatorSize;
//    private boolean isAutoPlay = true;
//    private boolean isScroll = true;
//    private int lastPosition = 1;
//    private OnBannerListener listener;
//    private int mIndicatorHeight;
//    private int mIndicatorMargin = 5;
//    private int mIndicatorSelectedResId = 2130837616;
//    private int mIndicatorUnselectedResId = 2130837639;
//    private int mIndicatorWidth;
//    private int mLayoutResId = 2130968613;
//    private ViewPager.OnPageChangeListener mOnPageChangeListener;
//    private BannerScroller mScroller;
//    private TextView numIndicator;
//    private TextView numIndicatorInside;
//    private int scaleType = 1;
//    private int scrollTime = 800;
//    public String tag = "banner";
//    private final Runnable task = new Runnable()
//    {
//        public void run()
//        {
//            if ((Banner.this.count > 1) && (Banner.this.isAutoPlay))
//            {
//                Banner.access$202(Banner.this, Banner.this.currentItem % (Banner.this.count + 1) + 1);
//                if (Banner.this.currentItem == 1)
//                {
//                    Banner.this.viewPager.setCurrentItem(Banner.this.currentItem, false);
//                    Banner.this.handler.post(Banner.this.task);
//                }
//            }
//            else
//            {
//                return;
//            }
//            Banner.this.viewPager.setCurrentItem(Banner.this.currentItem);
//            Banner.this.handler.postDelayed(Banner.this.task, Banner.this.delayTime);
//        }
//    };
//    private int titleBackground;
//    private int titleHeight;
//    private int titleTextColor;
//    private int titleTextSize;
//    private LinearLayout titleView;
//    private List<String> titles;
//    private BannerViewPager viewPager;
//
//    public Banner(Context paramContext)
//    {
//        this(paramContext, null);
//    }
//
//    public Banner(Context paramContext, AttributeSet paramAttributeSet)
//    {
//        this(paramContext, paramAttributeSet, 0);
//    }
//
//    public Banner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
//    {
//        super(paramContext, paramAttributeSet, paramInt);
//        this.context = paramContext;
//        this.titles = new ArrayList();
//        this.imageUrls = new ArrayList();
//        this.imageViews = new ArrayList();
//        this.indicatorImages = new ArrayList();
//        this.dm = paramContext.getResources().getDisplayMetrics();
//        this.indicatorSize = (this.dm.widthPixels / 80);
//        initView(paramContext, paramAttributeSet);
//    }
//
//    private void createIndicator()
//    {
//        this.indicatorImages.clear();
//        this.indicator.removeAllViews();
//        this.indicatorInside.removeAllViews();
//        int i = 0;
//        if (i < this.count)
//        {
//            ImageView localImageView = new ImageView(this.context);
//            localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.mIndicatorWidth, this.mIndicatorHeight);
//            localLayoutParams.leftMargin = this.mIndicatorMargin;
//            localLayoutParams.rightMargin = this.mIndicatorMargin;
//            if (i == 0)
//            {
//                localImageView.setImageResource(this.mIndicatorSelectedResId);
//                label96:
//                this.indicatorImages.add(localImageView);
//                if ((this.bannerStyle != 1) && (this.bannerStyle != 4)) {
//                    break label150;
//                }
//                this.indicator.addView(localImageView, localLayoutParams);
//            }
//            for (;;)
//            {
//                i += 1;
//                break;
//                localImageView.setImageResource(this.mIndicatorUnselectedResId);
//                break label96;
//                label150:
//                if (this.bannerStyle == 5) {
//                    this.indicatorInside.addView(localImageView, localLayoutParams);
//                }
//            }
//        }
//    }
//
//    private void handleTypedArray(Context paramContext, AttributeSet paramAttributeSet)
//    {
//        if (paramAttributeSet == null) {
//            return;
//        }
//        paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Banner);
//        this.mIndicatorWidth = paramContext.getDimensionPixelSize(7, this.indicatorSize);
//        this.mIndicatorHeight = paramContext.getDimensionPixelSize(8, this.indicatorSize);
//        this.mIndicatorMargin = paramContext.getDimensionPixelSize(9, 5);
//        this.mIndicatorSelectedResId = paramContext.getResourceId(10, 2130837616);
//        this.mIndicatorUnselectedResId = paramContext.getResourceId(11, 2130837639);
//        this.scaleType = paramContext.getInt(14, this.scaleType);
//        this.delayTime = paramContext.getInt(0, 2000);
//        this.scrollTime = paramContext.getInt(1, 800);
//        this.isAutoPlay = paramContext.getBoolean(2, true);
//        this.titleBackground = paramContext.getColor(3, -1);
//        this.titleHeight = paramContext.getDimensionPixelSize(6, -1);
//        this.titleTextColor = paramContext.getColor(4, -1);
//        this.titleTextSize = paramContext.getDimensionPixelSize(5, -1);
//        this.mLayoutResId = paramContext.getResourceId(12, this.mLayoutResId);
//        this.bannerBackgroundImage = paramContext.getResourceId(13, 2130837628);
//        paramContext.recycle();
//    }
//
//    private void initImages()
//    {
//        this.imageViews.clear();
//        if ((this.bannerStyle == 1) || (this.bannerStyle == 4) || (this.bannerStyle == 5)) {
//            createIndicator();
//        }
//        do
//        {
//            return;
//            if (this.bannerStyle == 3)
//            {
//                this.numIndicatorInside.setText("1/" + this.count);
//                return;
//            }
//        } while (this.bannerStyle != 2);
//        this.numIndicator.setText("1/" + this.count);
//    }
//
//    private void initView(Context paramContext, AttributeSet paramAttributeSet)
//    {
//        this.imageViews.clear();
//        handleTypedArray(paramContext, paramAttributeSet);
//        paramContext = LayoutInflater.from(paramContext).inflate(this.mLayoutResId, this, true);
//        this.bannerDefaultImage = ((ImageView)paramContext.findViewById(2131558409));
//        this.viewPager = ((BannerViewPager)paramContext.findViewById(2131558411));
//        this.titleView = ((LinearLayout)paramContext.findViewById(2131558436));
//        this.indicator = ((LinearLayout)paramContext.findViewById(2131558412));
//        this.indicatorInside = ((LinearLayout)paramContext.findViewById(2131558418));
//        this.bannerTitle = ((TextView)paramContext.findViewById(2131558410));
//        this.numIndicator = ((TextView)paramContext.findViewById(2131558421));
//        this.numIndicatorInside = ((TextView)paramContext.findViewById(2131558422));
//        this.bannerDefaultImage.setImageResource(this.bannerBackgroundImage);
//        initViewPagerScroll();
//    }
//
//    private void initViewPagerScroll()
//    {
//        try
//        {
//            Field localField = ViewPager.class.getDeclaredField("mScroller");
//            localField.setAccessible(true);
//            this.mScroller = new BannerScroller(this.viewPager.getContext());
//            this.mScroller.setDuration(this.scrollTime);
//            localField.set(this.viewPager, this.mScroller);
//            return;
//        }
//        catch (Exception localException)
//        {
//            Log.e(this.tag, localException.getMessage());
//        }
//    }
//
//    private void setBannerStyleUI()
//    {
//        if (this.count > 1) {}
//        for (int i = 0;; i = 8) {
//            switch (this.bannerStyle)
//            {
//                default:
//                    return;
//            }
//        }
//        this.indicator.setVisibility(i);
//        return;
//        this.numIndicator.setVisibility(i);
//        return;
//        this.numIndicatorInside.setVisibility(i);
//        setTitleStyleUI();
//        return;
//        this.indicator.setVisibility(i);
//        setTitleStyleUI();
//        return;
//        this.indicatorInside.setVisibility(i);
//        setTitleStyleUI();
//    }
//
//    private void setData()
//    {
//        this.currentItem = 1;
//        if (this.adapter == null)
//        {
//            this.adapter = new BannerPagerAdapter();
//            this.viewPager.addOnPageChangeListener(this);
//        }
//        this.viewPager.setAdapter(this.adapter);
//        this.viewPager.setFocusable(true);
//        this.viewPager.setCurrentItem(1);
//        if (this.gravity != -1) {
//            this.indicator.setGravity(this.gravity);
//        }
//        if ((this.isScroll) && (this.count > 1)) {
//            this.viewPager.setScrollable(true);
//        }
//        for (;;)
//        {
//            if (this.isAutoPlay) {
//                startAutoPlay();
//            }
//            return;
//            this.viewPager.setScrollable(false);
//        }
//    }
//
//    private void setImageList(List<?> paramList)
//    {
//        if ((paramList == null) || (paramList.size() <= 0))
//        {
//            this.bannerDefaultImage.setVisibility(0);
//            Log.e(this.tag, "The image data set is empty.");
//            return;
//        }
//        this.bannerDefaultImage.setVisibility(8);
//        initImages();
//        int i = 0;
//        label48:
//        Object localObject1;
//        if (i <= this.count + 1)
//        {
//            localObject1 = null;
//            if (this.imageLoader != null) {
//                localObject1 = this.imageLoader.createImageView(this.context);
//            }
//            Object localObject2 = localObject1;
//            if (localObject1 == null) {
//                localObject2 = new ImageView(this.context);
//            }
//            setScaleType((View)localObject2);
//            if (i != 0) {
//                break label166;
//            }
//            localObject1 = paramList.get(this.count - 1);
//            label124:
//            this.imageViews.add(localObject2);
//            if (this.imageLoader == null) {
//                break label200;
//            }
//            this.imageLoader.displayImage(this.context, localObject1, (View)localObject2);
//        }
//        for (;;)
//        {
//            i += 1;
//            break label48;
//            break;
//            label166:
//            if (i == this.count + 1)
//            {
//                localObject1 = paramList.get(0);
//                break label124;
//            }
//            localObject1 = paramList.get(i - 1);
//            break label124;
//            label200:
//            Log.e(this.tag, "Please set images loader.");
//        }
//    }
//
//    private void setScaleType(View paramView)
//    {
//        if ((paramView instanceof ImageView)) {
//            paramView = (ImageView)paramView;
//        }
//        switch (this.scaleType)
//        {
//            default:
//                return;
//            case 0:
//                paramView.setScaleType(ImageView.ScaleType.CENTER);
//                return;
//            case 1:
//                paramView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                return;
//            case 2:
//                paramView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                return;
//            case 3:
//                paramView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                return;
//            case 4:
//                paramView.setScaleType(ImageView.ScaleType.FIT_END);
//                return;
//            case 5:
//                paramView.setScaleType(ImageView.ScaleType.FIT_START);
//                return;
//            case 6:
//                paramView.setScaleType(ImageView.ScaleType.FIT_XY);
//                return;
//        }
//        paramView.setScaleType(ImageView.ScaleType.MATRIX);
//    }
//
//    private void setTitleStyleUI()
//    {
//        if (this.titles.size() != this.imageUrls.size()) {
//            throw new RuntimeException("[Banner] --> The number of titles and images is different");
//        }
//        if (this.titleBackground != -1) {
//            this.titleView.setBackgroundColor(this.titleBackground);
//        }
//        if (this.titleHeight != -1) {
//            this.titleView.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.titleHeight));
//        }
//        if (this.titleTextColor != -1) {
//            this.bannerTitle.setTextColor(this.titleTextColor);
//        }
//        if (this.titleTextSize != -1) {
//            this.bannerTitle.setTextSize(0, this.titleTextSize);
//        }
//        if ((this.titles != null) && (this.titles.size() > 0))
//        {
//            this.bannerTitle.setText((CharSequence)this.titles.get(0));
//            this.bannerTitle.setVisibility(0);
//            this.titleView.setVisibility(0);
//        }
//    }
//
//    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
//    {
//        int i;
//        if (this.isAutoPlay)
//        {
//            i = paramMotionEvent.getAction();
//            if ((i != 1) && (i != 3) && (i != 4)) {
//                break label37;
//            }
//            startAutoPlay();
//        }
//        for (;;)
//        {
//            return super.dispatchTouchEvent(paramMotionEvent);
//            label37:
//            if (i == 0) {
//                stopAutoPlay();
//            }
//        }
//    }
//
//    public Banner isAutoPlay(boolean paramBoolean)
//    {
//        this.isAutoPlay = paramBoolean;
//        return this;
//    }
//
//    public void onPageScrollStateChanged(int paramInt)
//    {
//        if (this.mOnPageChangeListener != null) {
//            this.mOnPageChangeListener.onPageScrollStateChanged(paramInt);
//        }
//        switch (paramInt)
//        {
//        }
//        do
//        {
//            do
//            {
//                return;
//                if (this.currentItem == 0)
//                {
//                    this.viewPager.setCurrentItem(this.count, false);
//                    return;
//                }
//            } while (this.currentItem != this.count + 1);
//            this.viewPager.setCurrentItem(1, false);
//            return;
//            if (this.currentItem == this.count + 1)
//            {
//                this.viewPager.setCurrentItem(1, false);
//                return;
//            }
//        } while (this.currentItem != 0);
//        this.viewPager.setCurrentItem(this.count, false);
//    }
//
//    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
//    {
//        if (this.mOnPageChangeListener != null) {
//            this.mOnPageChangeListener.onPageScrolled(toRealPosition(paramInt1), paramFloat, paramInt2);
//        }
//    }
//
//    public void onPageSelected(int paramInt)
//    {
//        this.currentItem = paramInt;
//        if (this.mOnPageChangeListener != null) {
//            this.mOnPageChangeListener.onPageSelected(toRealPosition(paramInt));
//        }
//        if ((this.bannerStyle == 1) || (this.bannerStyle == 4) || (this.bannerStyle == 5))
//        {
//            ((ImageView)this.indicatorImages.get((this.lastPosition - 1 + this.count) % this.count)).setImageResource(this.mIndicatorUnselectedResId);
//            ((ImageView)this.indicatorImages.get((paramInt - 1 + this.count) % this.count)).setImageResource(this.mIndicatorSelectedResId);
//            this.lastPosition = paramInt;
//        }
//        int i = paramInt;
//        if (paramInt == 0) {
//            i = this.count;
//        }
//        paramInt = i;
//        if (i > this.count) {
//            paramInt = 1;
//        }
//        switch (this.bannerStyle)
//        {
//            case 1:
//            default:
//                return;
//            case 2:
//                this.numIndicator.setText(paramInt + "/" + this.count);
//                return;
//            case 3:
//                this.numIndicatorInside.setText(paramInt + "/" + this.count);
//                this.bannerTitle.setText((CharSequence)this.titles.get(paramInt - 1));
//                return;
//            case 4:
//                this.bannerTitle.setText((CharSequence)this.titles.get(paramInt - 1));
//                return;
//        }
//        this.bannerTitle.setText((CharSequence)this.titles.get(paramInt - 1));
//    }
//
//    public void releaseBanner()
//    {
//        this.handler.removeCallbacksAndMessages(null);
//    }
//
//    public Banner setBannerAnimation(Class<? extends ViewPager.PageTransformer> paramClass)
//    {
//        try
//        {
//            setPageTransformer(true, (ViewPager.PageTransformer)paramClass.newInstance());
//            return this;
//        }
//        catch (Exception paramClass)
//        {
//            Log.e(this.tag, "Please set the PageTransformer class");
//        }
//        return this;
//    }
//
//    public Banner setBannerStyle(int paramInt)
//    {
//        this.bannerStyle = paramInt;
//        return this;
//    }
//
//    public Banner setBannerTitles(List<String> paramList)
//    {
//        this.titles = paramList;
//        return this;
//    }
//
//    public Banner setDelayTime(int paramInt)
//    {
//        this.delayTime = paramInt;
//        return this;
//    }
//
//    public Banner setImageLoader(ImageLoaderInterface paramImageLoaderInterface)
//    {
//        this.imageLoader = paramImageLoaderInterface;
//        return this;
//    }
//
//    public Banner setImages(List<?> paramList)
//    {
//        this.imageUrls = paramList;
//        this.count = paramList.size();
//        return this;
//    }
//
//    public Banner setIndicatorGravity(int paramInt)
//    {
//        switch (paramInt)
//        {
//            default:
//                return this;
//            case 5:
//                this.gravity = 19;
//                return this;
//            case 6:
//                this.gravity = 17;
//                return this;
//        }
//        this.gravity = 21;
//        return this;
//    }
//
//    public Banner setOffscreenPageLimit(int paramInt)
//    {
//        if (this.viewPager != null) {
//            this.viewPager.setOffscreenPageLimit(paramInt);
//        }
//        return this;
//    }
//
//    @Deprecated
//    public Banner setOnBannerClickListener(OnBannerClickListener paramOnBannerClickListener)
//    {
//        this.bannerListener = paramOnBannerClickListener;
//        return this;
//    }
//
//    public Banner setOnBannerListener(OnBannerListener paramOnBannerListener)
//    {
//        this.listener = paramOnBannerListener;
//        return this;
//    }
//
//    public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
//    {
//        this.mOnPageChangeListener = paramOnPageChangeListener;
//    }
//
//    public Banner setPageTransformer(boolean paramBoolean, ViewPager.PageTransformer paramPageTransformer)
//    {
//        this.viewPager.setPageTransformer(paramBoolean, paramPageTransformer);
//        return this;
//    }
//
//    public Banner setViewPagerIsScroll(boolean paramBoolean)
//    {
//        this.isScroll = paramBoolean;
//        return this;
//    }
//
//    public Banner start()
//    {
//        setBannerStyleUI();
//        setImageList(this.imageUrls);
//        setData();
//        return this;
//    }
//
//    public void startAutoPlay()
//    {
//        this.handler.removeCallbacks(this.task);
//        this.handler.postDelayed(this.task, this.delayTime);
//    }
//
//    public void stopAutoPlay()
//    {
//        this.handler.removeCallbacks(this.task);
//    }
//
//    public int toRealPosition(int paramInt)
//    {
//        int i = (paramInt - 1) % this.count;
//        paramInt = i;
//        if (i < 0) {
//            paramInt = i + this.count;
//        }
//        return paramInt;
//    }
//
//    public void update(List<?> paramList)
//    {
//        this.imageUrls.clear();
//        this.imageViews.clear();
//        this.indicatorImages.clear();
//        this.imageUrls.addAll(paramList);
//        this.count = this.imageUrls.size();
//        start();
//    }
//
//    public void update(List<?> paramList, List<String> paramList1)
//    {
//        this.titles.clear();
//        this.titles.addAll(paramList1);
//        update(paramList);
//    }
//
//    public void updateBannerStyle(int paramInt)
//    {
//        this.indicator.setVisibility(8);
//        this.numIndicator.setVisibility(8);
//        this.numIndicatorInside.setVisibility(8);
//        this.indicatorInside.setVisibility(8);
//        this.bannerTitle.setVisibility(8);
//        this.titleView.setVisibility(8);
//        this.bannerStyle = paramInt;
//        start();
//    }
//
//    class BannerPagerAdapter
//            extends PagerAdapter
//    {
//        BannerPagerAdapter() {}
//
//        public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
//        {
//            paramViewGroup.removeView((View)paramObject);
//        }
//
//        public int getCount()
//        {
//            return Banner.this.imageViews.size();
//        }
//
//        public Object instantiateItem(ViewGroup paramViewGroup, final int paramInt)
//        {
//            paramViewGroup.addView((View)Banner.this.imageViews.get(paramInt));
//            paramViewGroup = (View)Banner.this.imageViews.get(paramInt);
//            if (Banner.this.bannerListener != null) {
//                paramViewGroup.setOnClickListener(new View.OnClickListener()
//                {
//                    public void onClick(View paramAnonymousView)
//                    {
//                        Log.e(Banner.this.tag, "������������������������������������������������������1���������������������������������setOnBannerListener������������0������������");
//                        Banner.this.bannerListener.OnBannerClick(paramInt);
//                    }
//                });
//            }
//            if (Banner.this.listener != null) {
//                paramViewGroup.setOnClickListener(new View.OnClickListener()
//                {
//                    public void onClick(View paramAnonymousView)
//                    {
//                        Banner.this.listener.OnBannerClick(Banner.this.toRealPosition(paramInt));
//                    }
//                });
//            }
//            return paramViewGroup;
//        }
//
//        public boolean isViewFromObject(View paramView, Object paramObject)
//        {
//            return paramView == paramObject;
//        }
//    }
//}
