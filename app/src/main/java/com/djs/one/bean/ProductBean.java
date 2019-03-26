package com.djs.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/16<p>
 * <p>更改时间：2019/2/16<p>
 * <p>版本号：1<p>
 */
public class ProductBean {

    /**
     * code : 1000
     * message : success
     * data : {"list":[{"id":1,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:12"},{"id":2,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:12"},{"id":3,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":4,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":5,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":6,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":7,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":8,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":9,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":10,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"}],"pagination":{"current_page":1,"last_page":1,"per_page":20,"total":10}}
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
         * list : [{"id":1,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:12"},{"id":2,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:12"},{"id":3,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":4,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":5,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":6,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":7,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":8,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":9,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"},{"id":10,"title":"男士印花里布丝绒睡袍","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"is_promote":0,"thumb_url":"https://lorempixel.com/750/750/?65607","is_on_sale":1,"created_at":"2019-02-12 20:27:13"}]
         * pagination : {"current_page":1,"last_page":1,"per_page":20,"total":10}
         */

        private PaginationBean pagination;
        private List<Product> list;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public List<Product> getList() {
            return list;
        }

        public void setList(List<Product> list) {
            this.list = list;
        }

        public static class PaginationBean {
            /**
             * current_page : 1
             * last_page : 1
             * per_page : 20
             * total : 10
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
    }
}
