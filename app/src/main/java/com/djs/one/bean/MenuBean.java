package com.djs.one.bean;

import java.io.Serializable;

public class MenuBean
        implements Serializable
{
    public int id;
    public String menuId;
    public String menuName;

    public int getId()
    {
        return this.id;
    }

    public String getMenuId()
    {
        return this.menuId;
    }

    public String getMenuName()
    {
        return this.menuName;
    }

    public void setId(int paramInt)
    {
        this.id = paramInt;
    }

    public void setMenuId(String paramString)
    {
        this.menuId = paramString;
    }

    public void setMenuName(String paramString)
    {
        this.menuName = paramString;
    }
}
