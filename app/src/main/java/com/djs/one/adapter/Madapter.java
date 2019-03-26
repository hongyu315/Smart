package com.djs.one.adapter;

import android.widget.BaseAdapter;

import java.util.List;

public abstract class Madapter extends BaseAdapter {
    public abstract List<?> getItems();

    public abstract String getShowKey(int paramInt, String paramString);

    public abstract void setSelectColor(int paramInt);

    public abstract void setSelectedPosition(int paramInt);
}
