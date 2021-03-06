package com.djs.one.manager;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.djs.one.R;
import com.djs.one.view.MVideoView;

import java.io.IOException;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/24<p>
 * <p>更改时间：2019/1/24<p>
 * <p>版本号：1<p>
 */
public class BannerViewManager {

    private static class InnerInstance{
        private static BannerViewManager instance = new BannerViewManager();
    }

    private BannerViewManager(){
    }

    public static BannerViewManager getInstance(){
        return InnerInstance.instance;
    }

    protected int getDotDrawableRescource() {
        return R.drawable.dot;
    }

    public View createPoint(Context context){
        View viewPoint = new View(context);
        viewPoint.setBackgroundResource(getDotDrawableRescource());
        // value.complexToDimensionPixelOffset(data, metrics)
        // 把像素转换为点
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 10);
        params.leftMargin = 10;
        viewPoint.setLayoutParams(params);
        return viewPoint;
    }


    public VideoView createVideoViewBanner(Context context,LinearLayout.LayoutParams lp, String url){
        MVideoView videoView = new MVideoView(context);
        videoView.setLayoutParams(lp);
        videoView.setVideoURI(Uri.parse(url));
        videoView.setTag(url);
        videoView.seekTo(1);
        return videoView;
    }

    public ImageView createImageViewBanner(final Activity context, LinearLayout.LayoutParams lp, final String imageUrl){
        final ImageView imageView = new ImageView(context);
        try{
            imageView.setLayoutParams(lp);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (context !=null && !context.isDestroyed()){
                        Glide.with(context).load(imageUrl).into(imageView);
                    }
                }
            });
        }catch (Exception e){
        }
        return imageView;
    }

    public View createPlayIcon(Context context){
        ImageView icon = new ImageView(context);
        icon.setImageResource(R.mipmap.play_icon);
//        icon.setText("");
        return icon;
    }

    public View createDurationText(Context context, String videoUrl){
        TextView duration = new TextView(context);
        duration.setTextSize(7);
        duration.setTextColor(context.getResources().getColor(R.color.white));
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int md = mediaPlayer.getDuration() / 1000;
        String mediaD = String.format("%d'%02d''",md/60, md % 60);
        duration.setText(mediaD);
//        duration.setCompoundDrawablesWithIntrinsicBounds(null,context.getDrawable(R.mipmap.play_icon),null,null);
//        duration.setCompoundDrawablePadding(30);
        return duration;
    }

    public View createVideLayout(Context context, String videoUrl){
        RelativeLayout videoLayout = new RelativeLayout(context);
//        videoLayout.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams videoParams = new RelativeLayout.LayoutParams(120, 120);
        videoParams.rightMargin = 100;
        videoParams.bottomMargin = 50;
        videoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        videoLayout.setLayoutParams(videoParams);

        videoLayout.addView(createPlayIcon(context));

        View durView = createDurationText(context,videoUrl);

        RelativeLayout.LayoutParams videoParams1 = new RelativeLayout.LayoutParams(80, 48);
        videoParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        videoParams1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        durView.setLayoutParams(videoParams1);

        videoLayout.addView(durView);
        return videoLayout;
    }
}
