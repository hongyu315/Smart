package com.djs.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/3/26<p>
 * <p>更改时间：2019/3/26<p>
 * <p>版本号：1<p>
 */
public class ShoppingCarItems {

    /**
     * code : 1000
     * message : success
     * data : {"list":[{"id":21,"uid":13,"item_id":4,"title":"男士印花里布丝绒睡袍","thumb_url":"https://lorempixel.com/750/750/?65607","quantity":6,"is_promote":0,"promote_start_date":0,"stock":10,"promote_end_date":0,"specs":[{"attr_id":1,"attr_name":"尺寸","weight":1,"value":"L 睡衣180/96A 睡裤180/84A"},{"attr_id":2,"attr_name":"颜色","weight":2,"value":"蓝色"}],"market_price":96000,"promote_price":96000,"is_on_sale":1,"on_sale_time":1549974432,"created_at":"2019-03-26 11:12:46","updated_at":"2019-03-26 11:49:11"}],"pagination":{"current_page":1,"last_page":1,"per_page":20,"total":1},"total_amount":"576000"}
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
         * list : [{"id":21,"uid":13,"item_id":4,"title":"男士印花里布丝绒睡袍","thumb_url":"https://lorempixel.com/750/750/?65607","quantity":6,"is_promote":0,"promote_start_date":0,"stock":10,"promote_end_date":0,"specs":[{"attr_id":1,"attr_name":"尺寸","weight":1,"value":"L 睡衣180/96A 睡裤180/84A"},{"attr_id":2,"attr_name":"颜色","weight":2,"value":"蓝色"}],"market_price":96000,"promote_price":96000,"is_on_sale":1,"on_sale_time":1549974432,"created_at":"2019-03-26 11:12:46","updated_at":"2019-03-26 11:49:11"}]
         * pagination : {"current_page":1,"last_page":1,"per_page":20,"total":1}
         * total_amount : 576000
         */

        private PaginationBean pagination;
        private String total_amount;
        private List<ListBean> list;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PaginationBean {
            /**
             * current_page : 1
             * last_page : 1
             * per_page : 20
             * total : 1
             */

            private int current_page;
            private int last_page;
            private int per_page;
            private int total;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class ListBean {
            /**
             * id : 21
             * uid : 13
             * item_id : 4
             * title : 男士印花里布丝绒睡袍
             * thumb_url : https://lorempixel.com/750/750/?65607
             * quantity : 6
             * is_promote : 0
             * promote_start_date : 0
             * stock : 10
             * promote_end_date : 0
             * specs : [{"attr_id":1,"attr_name":"尺寸","weight":1,"value":"L 睡衣180/96A 睡裤180/84A"},{"attr_id":2,"attr_name":"颜色","weight":2,"value":"蓝色"}]
             * market_price : 96000
             * promote_price : 96000
             * is_on_sale : 1
             * on_sale_time : 1549974432
             * created_at : 2019-03-26 11:12:46
             * updated_at : 2019-03-26 11:49:11
             */

            private int id;
            private int uid;
            private int item_id;
            private int sku_id;
            private String title;
            private String thumb_url;
            private int quantity;
            private int is_promote;
            private int promote_start_date;
            private int stock;
            private int promote_end_date;
            private int market_price;
            private int promote_price;
            private int is_on_sale;
            private int on_sale_time;
            private String created_at;
            private String updated_at;
            private List<SpecsBean> specs;
            private boolean isChoosed;
            private String size;

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
                this.sku_id = sku_id;
            }

            public boolean isChoosed() {
                return isChoosed;
            }

            public void setChoosed(boolean choosed) {
                isChoosed = choosed;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb_url() {
                return thumb_url;
            }

            public void setThumb_url(String thumb_url) {
                this.thumb_url = thumb_url;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(int is_promote) {
                this.is_promote = is_promote;
            }

            public int getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(int promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(int promote_end_date) {
                this.promote_end_date = promote_end_date;
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

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public int getOn_sale_time() {
                return on_sale_time;
            }

            public void setOn_sale_time(int on_sale_time) {
                this.on_sale_time = on_sale_time;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public List<SpecsBean> getSpecs() {
                return specs;
            }

            public void setSpecs(List<SpecsBean> specs) {
                this.specs = specs;
            }

            public static class SpecsBean {
                /**
                 * attr_id : 1
                 * attr_name : 尺寸
                 * weight : 1
                 * value : L 睡衣180/96A 睡裤180/84A
                 */

                private int attr_id;
                private String attr_name;
                private int weight;
                private String value;

                public int getAttr_id() {
                    return attr_id;
                }

                public void setAttr_id(int attr_id) {
                    this.attr_id = attr_id;
                }

                public String getAttr_name() {
                    return attr_name;
                }

                public void setAttr_name(String attr_name) {
                    this.attr_name = attr_name;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
