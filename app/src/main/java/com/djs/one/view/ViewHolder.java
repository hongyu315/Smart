package com.com.one.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder
{
    private View mConvertView;
    private int mPosition;
    private SparseArray<View> mViews;

    private ViewHolder(Context paramContext, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
    {
        this.mPosition = paramInt2;
        this.mViews = new SparseArray();
        this.mConvertView = LayoutInflater.from(paramContext).inflate(paramInt1, paramViewGroup, false);
        this.mConvertView.setTag(this);
    }

    public ViewHolder(View paramView)
    {
        this.mConvertView = paramView;
        this.mViews = new SparseArray();
    }

    public static <T extends View> T get(View paramView, int paramInt)
    {
        Object localObject2 = paramView.getTag();
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
            localObject1 = new SparseArray();
            paramView.setTag(localObject1);
        }
        View localView = (View)((SparseArray)localObject1).get(paramInt);
        localObject2 = localView;
        if (localView == null)
        {
            localObject2 = paramView.findViewById(paramInt);
            ((SparseArray)localObject1).put(paramInt, localObject2);
        }
        return (T)localObject2;
    }

    public static ViewHolder get(Context paramContext, View paramView, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
    {
        if (paramView == null) {
            return new ViewHolder(paramContext, paramViewGroup, paramInt1, paramInt2);
        }
        return (ViewHolder)paramView.getTag();
    }

    public View getConvertView()
    {
        return this.mConvertView;
    }

    public int getPosition()
    {
        return this.mPosition;
    }

    public View getView(int paramInt)
    {
        View localView2 = this.mViews.get(paramInt);
        View localView1 = localView2;
        if (localView2 == null)
        {
            localView1 = this.mConvertView.findViewById(paramInt);
            this.mViews.put(paramInt, localView1);
        }
        return localView1;
    }

    public ViewHolder setImageBitmap(int paramInt, Bitmap paramBitmap)
    {
        ((ImageView)getView(paramInt)).setImageBitmap(paramBitmap);
        return this;
    }

    public ViewHolder setImageByUrl(int paramInt, String paramString)
    {
        return this;
    }

    public ViewHolder setImageResource(int paramInt1, int paramInt2)
    {
        ((ImageView)getView(paramInt1)).setImageResource(paramInt2);
        return this;
    }

    public ViewHolder setText(int paramInt, String paramString)
    {
        ((TextView)getView(paramInt)).setText(paramString);
        return this;
    }
}
