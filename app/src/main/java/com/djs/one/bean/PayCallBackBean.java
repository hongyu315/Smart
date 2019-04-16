package com.djs.one.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/4/13<p>
 * <p>更改时间：2019/4/13<p>
 * <p>版本号：1<p>
 */
public class PayCallBackBean {
    /**
     * code : 1000
     * message : success
     * data : {"trade_no":"201904131728589544","pay":"app_id=2016092000556857&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&notify_url=https%3A%2F%2Fapi.tianyi41.com%2Fapi%2Fpaynotify%2Fali&timestamp=2019-04-13+17%3A37%3A58&biz_content=%7B%22out_trade_no%22%3A%22201904131728589544%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%5Cu51711%5Cu4ef6+%5Cu5408%5Cu8ba1%5Cuff1a0.00%5Cu5143%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&method=alipay.trade.app.pay&sign=kRtD9%2BRNqqg6yo6614PAoty02QHDtx%2FZcORF0Yg6YQYC0HgEpl7G9JyiGR9faLytgKyawKQkh5BueC%2BKzt%2BxOCzeyxEGSbMfmZvLbph4C9y3geoEqNx82amJFOjAHz2t1%2FjUbIZORMvP0R%2FLynMBktBrKlZsBXf8zJsUw%2BsVKLUxBGWNuWCuElcR%2FTPR6i1UJzRmRbitjLnnizh15JRxJQ%2FWePcf7Hy4Aq0qXwh5fETyyBq75QsITdfJ9AJVkuFVgD78rF3LuKXo14XZF1DAAMhI2519LP1XbivN86v5peiDab5%2BqoYN17OOJHsJSfameAL%2BYzAuUSMK3%2FpREjhnOw%3D%3D"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * trade_no : 201904131728589544
         * pay : app_id=2016092000556857&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&notify_url=https%3A%2F%2Fapi.tianyi41.com%2Fapi%2Fpaynotify%2Fali&timestamp=2019-04-13+17%3A37%3A58&biz_content=%7B%22out_trade_no%22%3A%22201904131728589544%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%5Cu51711%5Cu4ef6+%5Cu5408%5Cu8ba1%5Cuff1a0.00%5Cu5143%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&method=alipay.trade.app.pay&sign=kRtD9%2BRNqqg6yo6614PAoty02QHDtx%2FZcORF0Yg6YQYC0HgEpl7G9JyiGR9faLytgKyawKQkh5BueC%2BKzt%2BxOCzeyxEGSbMfmZvLbph4C9y3geoEqNx82amJFOjAHz2t1%2FjUbIZORMvP0R%2FLynMBktBrKlZsBXf8zJsUw%2BsVKLUxBGWNuWCuElcR%2FTPR6i1UJzRmRbitjLnnizh15JRxJQ%2FWePcf7Hy4Aq0qXwh5fETyyBq75QsITdfJ9AJVkuFVgD78rF3LuKXo14XZF1DAAMhI2519LP1XbivN86v5peiDab5%2BqoYN17OOJHsJSfameAL%2BYzAuUSMK3%2FpREjhnOw%3D%3D
         */

        private String trade_no;
        private String pay;

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }
    }
}
