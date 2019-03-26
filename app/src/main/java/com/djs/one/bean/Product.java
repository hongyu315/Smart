package com.djs.one.bean;

import java.io.Serializable;

public class Product implements Serializable {

    /**
     * id : 1
     * title : 男士印花里布丝绒睡袍
     * market_price : 96000
     * promote_price : 96000
     * on_sale_time : 1549974432
     * is_promote : 0
     * thumb_url : https://lorempixel.com/750/750/?65607
     * is_on_sale : 1
     * created_at : 2019-02-12 20:27:12
     */

    private int id;
    private String title;
    private int market_price;
    private int promote_price;
    private int on_sale_time;
    private int is_promote;
    private String thumb_url;
    private int is_on_sale;
    private String created_at;
    private int uid;
    private int item_id;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMarket_price() {
        return market_price;
    }

    public void setMarket_price(int market_price) {
        this.market_price = market_price;
    }

    public int getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(int promote_price) {
        this.promote_price = promote_price;
    }

    public int getOn_sale_time() {
        return on_sale_time;
    }

    public void setOn_sale_time(int on_sale_time) {
        this.on_sale_time = on_sale_time;
    }

    public int getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(int is_promote) {
        this.is_promote = is_promote;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public int getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(int is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
