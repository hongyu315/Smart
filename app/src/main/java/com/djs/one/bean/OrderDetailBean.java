package com.djs.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/4/12<p>
 * <p>更改时间：2019/4/12<p>
 * <p>版本号：1<p>
 */
public class OrderDetailBean {

    /**
     * code : 1000
     * message : success
     * data : {"pay_amount":0,"trade_no":"201904121447027646","desc":"共1件 合计：0.00元","pay_type":0,"expire_time":"08:27:58","pay_time":null,"refund_time":null,"status":0,"items":[{"item_id":9,"item_title":"男士印花里布丝绒睡袍","thumb_url":"https://img.tianyi41.com/static/upload/images/goods/2019/01/14/1547454702272215.jpg","sku_id":69,"sku_title":"L 睡衣180/96A 睡裤180/84A 颜色蓝色","specs":[{"attr_id":1,"attr_name":"尺寸","weight":1,"value":"L 睡衣180/96A 睡裤180/84A"},{"attr_id":2,"attr_name":"颜色","weight":2,"value":"蓝色"}],"quantity":0,"price":960}]}
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
         * pay_amount : 0
         * trade_no : 201904121447027646
         * desc : 共1件 合计：0.00元
         * pay_type : 0
         * expire_time : 08:27:58
         * pay_time : null
         * refund_time : null
         * status : 0
         * items : [{"item_id":9,"item_title":"男士印花里布丝绒睡袍","thumb_url":"https://img.tianyi41.com/static/upload/images/goods/2019/01/14/1547454702272215.jpg","sku_id":69,"sku_title":"L 睡衣180/96A 睡裤180/84A 颜色蓝色","specs":[{"attr_id":1,"attr_name":"尺寸","weight":1,"value":"L 睡衣180/96A 睡裤180/84A"},{"attr_id":2,"attr_name":"颜色","weight":2,"value":"蓝色"}],"quantity":0,"price":960}]
         */

        private int pay_amount;
        private String trade_no;
        private String desc;
        private int pay_type;
        private String expire_time;
        private Object pay_time;
        private Object refund_time;
        private int status;
        private List<ItemsBean> items;

        public int getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(int pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public Object getPay_time() {
            return pay_time;
        }

        public void setPay_time(Object pay_time) {
            this.pay_time = pay_time;
        }

        public Object getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(Object refund_time) {
            this.refund_time = refund_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * item_id : 9
             * item_title : 男士印花里布丝绒睡袍
             * thumb_url : https://img.tianyi41.com/static/upload/images/goods/2019/01/14/1547454702272215.jpg
             * sku_id : 69
             * sku_title : L 睡衣180/96A 睡裤180/84A 颜色蓝色
             * specs : [{"attr_id":1,"attr_name":"尺寸","weight":1,"value":"L 睡衣180/96A 睡裤180/84A"},{"attr_id":2,"attr_name":"颜色","weight":2,"value":"蓝色"}]
             * quantity : 0
             * price : 960
             */

            private int item_id;
            private String item_title;
            private String thumb_url;
            private int sku_id;
            private String sku_title;
            private int quantity;
            private int price;
            private List<SpecsBean> specs;

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

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

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
                this.sku_id = sku_id;
            }

            public String getSku_title() {
                return sku_title;
            }

            public void setSku_title(String sku_title) {
                this.sku_title = sku_title;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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
