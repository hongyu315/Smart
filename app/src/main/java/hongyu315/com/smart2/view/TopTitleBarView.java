package hongyu315.com.smart2.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hongyu315.com.smart2.R;

public class TopTitleBarView
        extends LinearLayout
{
    public Context mContext;
    public ImageView mTvLeftImageMenu;
    public ImageView mTvRightSearch;
    public TextView mTvTitle;

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
        this.mTvLeftImageMenu = ((ImageView)findViewById(R.id.top_title_bar_menu));
        this.mTvTitle = ((TextView)findViewById(R.id.top_title_bar_title));
        this.mTvRightSearch = ((ImageView)findViewById(R.id.top_title_bar_search));
    }
}
