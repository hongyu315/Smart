package hongyu315.com.smart2.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hongyu315.com.smart2.R;

public class TopTitleBarView
        extends LinearLayout implements View.OnClickListener {
    public Context mContext;
    public ImageView mTvLeftImageMenu;
    public ImageView mTvRightSearch;
    public TextView mTvTitle;
    public RelativeLayout mTopTitleBarViewBg;
    public TopTitleBarClickListener mListener;

    public TopTitleBarView(Context paramContext)
    {
        super(paramContext);
    }

    public TopTitleBarView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
        initView();
    }

    public TopTitleBarView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        initView();
    }

    private void initView()
    {
        LayoutInflater.from(this.mContext).inflate(R.layout.top_title_bar_layout, this);
        this.mTopTitleBarViewBg = findViewById(R.id.top_title_bar_bg);
        this.mTvLeftImageMenu = findViewById(R.id.top_title_bar_menu);
        this.mTvTitle = findViewById(R.id.top_title_bar_title);
        this.mTvRightSearch = findViewById(R.id.top_title_bar_search);

        mTvLeftImageMenu.setOnClickListener(this);
        mTvRightSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_bar_search:
                mListener.onTopTitleBarRightIconClickListener();
                break;
            case R.id.top_title_bar_menu:
                mListener.onTopTitleBarLeftIconClickListener();
                break;
            default:
                break;
        }
    }

    public void setTitle(CharSequence text){
        mTvTitle.setText(text);
    }

    public CharSequence getTitle(){
        return mTvTitle.getText();
    }

    public interface TopTitleBarClickListener{
        void onTopTitleBarLeftIconClickListener();
        void onTopTitleBarRightIconClickListener();
    }

    public void setTopBarClickListener(TopTitleBarClickListener listener){
        mListener = listener;
    }
}
