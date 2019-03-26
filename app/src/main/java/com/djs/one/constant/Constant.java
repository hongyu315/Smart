package com.djs.one.constant;

public class Constant {

    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wxd930ea5d5a258f4f";

    public static final int MESSAGE = 1;//消息类型
    public static final int FAVORITE = 2;//我的收藏

    //我的订单状态
    public static final int COMPLETED = 3; //已完成
    public static final int WAIT4PAY  = 4; //待付款
    public static final int WAIT4DELIVER = 5;//待发货
    public static final int WAIT4TAKEDELIVER = 6;//待收货

    //网络请求成功
    public static final int SUCCESSFUL = 1000;

    //商品列表数量
    public static final String PRODUCT_SIZE = "20";

    //排序类型 1 倒序 2 升序，默认 1
    public static final String SORT_TYPE_INVERTED = "1" ;
    public static final String SORT_TYPE_ASCENDING = "2" ;

    public static final String KEY_SEX = "SEX";
    public static final String KEY_NICKNAME = "Nickname";

    //界面
    public static final String PAGE = "PAGE";


}
