package hongyu315.com.smart2.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class BannerScroller
        extends Scroller
{
    private int mDuration = 800;

    public BannerScroller(Context paramContext)
    {
        super(paramContext);
    }

    public BannerScroller(Context paramContext, Interpolator paramInterpolator)
    {
        super(paramContext, paramInterpolator);
    }

    public BannerScroller(Context paramContext, Interpolator paramInterpolator, boolean paramBoolean)
    {
        super(paramContext, paramInterpolator, paramBoolean);
    }

    public void setDuration(int paramInt)
    {
        this.mDuration = paramInt;
    }

    public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.startScroll(paramInt1, paramInt2, paramInt3, paramInt4, this.mDuration);
    }

    public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
        super.startScroll(paramInt1, paramInt2, paramInt3, paramInt4, this.mDuration);
    }
}
