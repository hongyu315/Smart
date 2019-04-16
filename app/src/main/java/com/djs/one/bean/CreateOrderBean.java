package com.djs.one.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/4/3<p>
 * <p>更改时间：2019/4/3<p>
 * <p>版本号：1<p>
 */
public class CreateOrderBean {

    /**
     * code : 1000
     * message : success
     * data : {"trade_no":"201904031956213740"}
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
         * trade_no : 201904031956213740
         */

        private String trade_no;

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }
    }
}
