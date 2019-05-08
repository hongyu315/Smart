package com.djs.one.bean;

import com.google.gson.annotations.SerializedName;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/4/17<p>
 * <p>更改时间：2019/4/17<p>
 * <p>版本号：1<p>
 */
public class WXCallback {

    /**
     * code : 1000
     * message : success
     * data : {"trade_no":"201904180900380295","pay":{"appid":"wx2961ba75dca2dccf","partnerid":"1523274601","prepayid":"wx18090709670598d42ee104942551549093","timestamp":"1555549629","noncestr":"2egO3zIqcFugUMHR","package":"Sign=WXPay","sign":"4E81E541947AB46004E439228969E450"}}
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
         * trade_no : 201904180900380295
         * pay : {"appid":"wx2961ba75dca2dccf","partnerid":"1523274601","prepayid":"wx18090709670598d42ee104942551549093","timestamp":"1555549629","noncestr":"2egO3zIqcFugUMHR","package":"Sign=WXPay","sign":"4E81E541947AB46004E439228969E450"}
         */

        private String trade_no;
        private PayBean pay;

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public PayBean getPay() {
            return pay;
        }

        public void setPay(PayBean pay) {
            this.pay = pay;
        }

        public static class PayBean {
            /**
             * appid : wx2961ba75dca2dccf
             * partnerid : 1523274601
             * prepayid : wx18090709670598d42ee104942551549093
             * timestamp : 1555549629
             * noncestr : 2egO3zIqcFugUMHR
             * package : Sign=WXPay
             * sign : 4E81E541947AB46004E439228969E450
             */

            private String appid;
            private String partnerid;
            private String prepayid;
            private String timestamp;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
