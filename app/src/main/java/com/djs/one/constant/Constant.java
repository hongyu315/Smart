package com.djs.one.constant;

public class Constant {

    // APP_ID 替换为你的应用从官方网站申请到的合法appId
//    微信的APPID wx2961ba75dca2dccf
//    AppSecret: 8c9c933056821f102de8c937e86a6cb2
    public static final String APP_ID = "wx2961ba75dca2dccf";

    public static final int MESSAGE = 0;//消息类型
    public static final int FAVORITE = 2;//我的收藏

    //我的订单状态 订单状态： 1 待支付, 2 已支付/待发货, 3 已发货/待收货, 4 已完成， 默认4
    public static final int COMPLETED = 4; //已完成
    public static final int WAIT4PAY  = 1; //待付款
    public static final int WAIT4DELIVER = 2;//待发货
    public static final int WAIT4TAKEDELIVER = 3;//待收货

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

    //支付方式
    public static final String PAY_METHOD = "PAY_METHOD";

    //APPID
    public static final String APPID = "2016092000556857";

    //支付宝 密钥
    public static final String AliPayPrivate = "MIIEpQIBAAKCAQEAmROsoeXmc23VKQ8cnyFctFAo0bBgkXbX37Tsw4UztKgD1umxwkAUpFWhuZ9qXYQhHeZyMd+oJfRHMo0hP2Q/b4+abFawtzqKH3oSR8jlf+QmOmREmGHluZ7mIn2kUgu7cudl/yMGYwRaK4vLsc+jm1vjeB0BTxhTS2GCIzZEkd1v74uIGmXlxperk5Mh5czjH3fyiJkk28BCuUCPyMYhlTaQVQdvdPoEhkpF7pZmBhRHxhjS0Dc2iyCxd+SZT1khfQx/NzZmYSs7JxjI07LhlnEP7AZ/WL+y7C839osTVRIAKsFW04I7t4danGB9Y9HubBa755DMrDf5QVriS9sOjwIDAQABAoIBAGx+Dj+cws2SzZ5RMJb0PVEq8xNTL85Jbc0G3TviP3hOEJKUhUFIy5iBfKMkAm4PRK19c/g3LWThEkkYz7SvJ2q5W9hnRM5IdjbC/QvVrhsLp7CA+kEKOZhqr29In1rrJ8rTiG3g9Bjfr8znQtJeePWWlcy+ufvGINrvXB36y3SZZaBn2PZeQf41QjjL3IkYGYfbvklMx8/VU9k0uAMYeqsxumU+X7Xre3zpzCaPffGKD6fmRYoJn0evZ8hp6BGFRCT9qG3TtqmKOgSPi0KA3s+P0+9tSmEIJtAp/KR6MvMJw24saqsTxPwi+shQUUZYdxaLk+p5Hq+lzuZj9CtCPTkCgYEAycf07nxTGBZ4TfsPQ68SGIUGvo9aLJDK4WQ2xPvr1EOy3NDyBknnU5LxdOuaUN/CwiJ46W7d0JK2KVZ2+TolVTE4hxmAtnW4Els4hDeo2o3uDGk0PR+3/yvc0mEljr8pQMaUKz3cm0+wsE534okh5SCOfucw7ratSWJiOjMNZ2sCgYEAwjV4DFj4VILk+EBe2JIBnYDpatbudsViCLuvcqEnzg++lmefucbUiWMteAF4lTUWyo9IS041CD85HSCZA5sP1+be8hV5kN9bv9DmPOSt0dWqVyl2oeNkBPGbKN9qcsKlnxbXTZ1nlvWlyO9ow6VpALJ58dNNSBlwv0p0svhBkm0CgYEAxuyC8g3M2WVlivMkNETG4Tdb08d9TYwdBqEGQaJd9vAayAiRYVPAYyrtMagHhQ1jcoILk44BzVqljRE5zy/cmCUjJkUlGDvrkOUvTWGV7IiZktCweEXp3fz/AMRXA2g/oiVi81JURyUD+nwRQlqQ+NLkyAThv6SGJ19/mQK1hysCgYEAnVCwmNfs+JA7J3kFG0tSMaJ98YR/EAide9OEsEcotjt8t9riwJr2CQAkkcQnSD4D4zsjZZTJUo/cFOMV75zIvawPRmvs2FSFvqBaEFNxFbuNSyOULFjE6VhIxlgLo0BW5sKazw1FHzyG+XgtFeZEY0MvkpsGS/QHp3yJgXulqhUCgYEAxK/+dScGu5A1qn1f4ZeeP2c9cLMZ1PoMOhG+CZZcRi5299FXcMXNBhbQkq44AzVO0uB9wfRVdTw1by0R/b6dAyiDFGbU3FkYiIH/5Tk7BJI763Lxj0u9o1gmrLdoEL1eArUJx6eKZj9CaKOmaHSB1q+9Zyal6Hlch+Fb6yVx+/A=";

    //订单界面
    public static final int From_Order = 1;

    //产品界面
    public static final int From_Product = 2;

    public static String DeviceToken = "";
}
