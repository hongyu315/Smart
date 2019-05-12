package com.djs.one.bean;

/**
 * "uid": 13,
 *             "title": "订单已发货",
 *             "detail": "您的订单尾号[5235]已发货",
 *             "business_id": "201904291117085235",
 *             "business_type": 1,
 *             "business_extra": {
 *                 "item_title": "测试服装",
 *                 "thumb_url": "https://img.tianyi41.com/static/upload/images/goods/2019/01/14/1547455266234658.jpg",
 *                 "waybill_no": "306234610302",
 *                 "logistic_no": "shunfeng",
 *                 "logistic_com": "顺丰快递"
 *             },
 *             "type": 0,
 *             "is_read": 0,
 *             "user_is_delete_time": 0,
 *             "created_at": "2019-05-06 22:48:14"
 */
public class Message {

    private String title;
    private String detail;
    private String business_id;
    private int business_type;
    private int type;
    private int is_read;
    private String user_is_delete_time;
    private String created_at;
    private BusinessExtra business_extra;

    public BusinessExtra getBusiness_extra() {
        return business_extra;
    }

    public void setBusiness_extra(BusinessExtra business_extra) {
        this.business_extra = business_extra;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public int getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(int business_type) {
        this.business_type = business_type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getUser_is_delete_time() {
        return user_is_delete_time;
    }

    public void setUser_is_delete_time(String user_is_delete_time) {
        this.user_is_delete_time = user_is_delete_time;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class BusinessExtra {
        private String item_title;
        private String thumb_url;
        private String waybill_no;
        private String created_at;
        private String logistic_no;
        private String logistic_com;

        public String getItem_title() {
            return item_title;
        }

        public void setItem_title(String item_title) {
            this.item_title = item_title;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getWaybill_no() {
            return waybill_no;
        }

        public void setWaybill_no(String waybill_no) {
            this.waybill_no = waybill_no;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getLogistic_no() {
            return logistic_no;
        }

        public void setLogistic_no(String logistic_no) {
            this.logistic_no = logistic_no;
        }

        public String getLogistic_com() {
            return logistic_com;
        }

        public void setLogistic_com(String logistic_com) {
            this.logistic_com = logistic_com;
        }
    }

}
