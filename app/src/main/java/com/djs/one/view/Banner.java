package com.com.one.view;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import com.com.one.adapter.BannerViewAdapter;
import com.com.one.manager.BannerViewManager;

/**
 * Created by hongyu on 2019/1/14.
 */

public class Banner extends RelativeLayout {
    private ViewPager viewPager;
    //默认显示位置
    private int autoCurrIndex = 0;
    //是否自动播放
    private List<View> views;
    private BannerViewAdapter mAdapter;

    private LinearLayout pointContainer;
    private Activity mActivity;

    public Banner(Context context) {
        super(context);
        init();
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Banner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        viewPager = new ViewPager(getContext());
        LinearLayout.LayoutParams vp_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(vp_param);
        this.addView(viewPager);

        pointContainer = new LinearLayout(getContext());
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.bottomMargin = 20;
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        pointContainer.setGravity(CENTER_HORIZONTAL);
        pointContainer.setLayoutParams(lp);

        this.addView(pointContainer);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //当前位置
                onCancelSelect(autoCurrIndex);
                onSelect(position);
                autoCurrIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                pauseAllVideo();

                //ViewPager跳转
                int pageIndex = autoCurrIndex;
                View view1 = views.get(pageIndex);
                if (view1 instanceof VideoView) {
                    final VideoView videoView = (VideoView) view1;
                    videoView.seekTo(1);
                }
            }
        });
    }

    /**
     * 指示点被选中
     *
     * @param position
     */
    protected void onSelect(int position) {
        if (pointContainer == null) return;
        View view = pointContainer.getChildAt(position);
        if (view != null) {
            view.setEnabled(false);
        }
    }

    protected void onCancelSelect(int position) {
        if (pointContainer == null) return;

        View childAt = pointContainer.getChildAt(position);
        if (childAt == null) {
            Log.e("xxx", "mPointContainer position is empty at position:" + position);
            return;
        }
        childAt.setEnabled(true);
    }

    public void setDataList(Activity activity, List<String> dataList) {

        mActivity = activity;

        if (dataList == null) {
            dataList = new ArrayList<>();
        }

        //用于显示的数组
        if (views == null) {
            views = new ArrayList<>();
        } else {
            views.clear();
        }

        mAdapter = new BannerViewAdapter(views);

        for (int i = 0 ; i < dataList.size(); i++) {
            pointContainer.addView(BannerViewManager.getInstance().createPoint(getContext()));
        }


        new BannerItemCreateAsyncTask().execute(dataList);
    }

    class BannerItemCreateAsyncTask extends AsyncTask<List<String>,Void,View>{
        @Override
        protected View doInBackground(List<String>... lists) {
            Looper.prepare();

            List<String> dataList = lists[0];

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            if (dataList.size() > 1) {
                for (int i = 0; i < dataList.size(); i++) {
                    createBannerItem(dataList.get(i),lp,i);
                }
            } else if (dataList.size() == 1) {
                autoCurrIndex = 0;
                createBannerItem(dataList.get(0),lp,0);
            }

            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            dataChanged();
        }
    }

    private void createBannerItem(String url, LinearLayout.LayoutParams lp, int position){
        try {
            if (MimeTypeMap.getFileExtensionFromUrl(url).equals("mp4")) {
                views.add(createVideoContainerLayout(getContext(),lp,url,position));
            } else {
                views.add(BannerViewManager.getInstance().createImageViewBanner(mActivity,lp,url));
            }
        }catch (Exception e){
        }
    }

    private View createVideoContainerLayout(Context context, LinearLayout.LayoutParams lp, String url, int position){
        RelativeLayout videoContainerLayout = new RelativeLayout(context);
        videoContainerLayout.setTag(position);
        videoContainerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSelectedVideo((Integer) v.getTag());
            }
        });
        videoContainerLayout.setLayoutParams(lp);

        videoContainerLayout.addView(BannerViewManager.getInstance().createVideoViewBanner(context,lp,url));
        videoContainerLayout.addView(BannerViewManager.getInstance().createVideLayout(context,url));
        return videoContainerLayout;
    }

    public void startBanner() {
        try {
            if (views != null && views.size() > 0){
//                mAdapter = new BannerViewAdapter(views);
                viewPager.setAdapter(mAdapter);
                viewPager.setOffscreenPageLimit(views.size());
                viewPager.setCurrentItem(autoCurrIndex);
                onSelect(autoCurrIndex);
            }
        }catch (Exception e){
        }
    }

    public void playSelectedVideo(int position) {
        RelativeLayout layout = (RelativeLayout) views.get(position);
        View view1 = layout.getChildAt(0);
        if (view1 instanceof VideoView) {
            VideoView videoView = (VideoView) view1;
            videoView.start();
        }
    }

    public void pauseAllVideo() {
        for (View videoView : views) {

            if (videoView instanceof RelativeLayout){
                RelativeLayout layout = (RelativeLayout) videoView;
                View view1 = layout.getChildAt(0);
                if (view1 instanceof VideoView) {
                    ((VideoView) view1).pause();
                }
            }
        }
    }

    public void dataChanged() {
        try{
            if (mActivity != null && mAdapter != null && views != null){
//                mAdapter.setDataList(views);
//                mAdapter.notifyDataSetChanged();
                startBanner();
            }
        }catch (Exception e){
        }
    }

    public void destroy() {
        try{
            if (views != null && views.size() > 0){
                views.clear();
            }
            views = null;
            viewPager = null;
            mAdapter = null;
        }catch (Exception e){
        }

    }
}
