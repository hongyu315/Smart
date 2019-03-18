package com.com.one.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity
{
    public int selectedIcon;
    public String title;
    public int unSelectedIcon;

    public TabEntity(String paramString, int paramInt1, int paramInt2)
    {
        this.title = paramString;
        this.selectedIcon = paramInt1;
        this.unSelectedIcon = paramInt2;
    }

    public int getTabSelectedIcon()
    {
        return this.selectedIcon;
    }

    public String getTabTitle()
    {
        return this.title;
    }

    public int getTabUnselectedIcon()
    {
        return this.unSelectedIcon;
    }
}
