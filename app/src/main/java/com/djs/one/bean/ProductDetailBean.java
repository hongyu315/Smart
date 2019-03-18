package com.com.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/18<p>
 * <p>更改时间：2019/2/18<p>
 * <p>版本号：1<p>
 */
public class ProductDetailBean {

    /**
     * code : 1000
     * message : success
     * data : {"id":1,"title":"男士印花里布丝绒睡袍","composition":"面料：100%棉，辅料除外\n                        尺寸-上衣XL XXL\n                        衣长 74 75\n                        肩宽 48 49\n                        胸围半围 56 58\n                        ","content":"        <!DOCTYPE html>\n        <html>\n        <head>\n            <meta charset=\"utf-8\">\n            <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\">\n            <title>产品详情<\/title>\n        <\/head>\n        <body>\n            <img src=\"https://pro.modao.cc/uploads3/images/2967/29678726/v2_pky5oy.jpg\" width=\"100%\" />\n        <\/body>\n        <\/html>","story":"野兽派获得法国Le Petit Prince原作授权推广小王子系列产品，远伴你度过美好的每一天","market_price":96000,"promote_price":96000,"on_sale_time":1549974432,"attributes":[{"attr_id":2,"attr_name":"颜色","weight":2,"values":[{"id":1,"value":"蓝色","weight":1},{"id":2,"value":"绿色","weight":2},{"id":3,"value":"红色","weight":3},{"id":4,"value":"白色","weight":4}]},{"attr_id":1,"attr_name":"尺寸","weight":1,"values":[{"id":13,"value":"L 睡衣180/96A 睡裤180/84A","weight":8},{"id":12,"value":"M 睡衣175/92A 睡裤175/80A","weight":9}]}],"skus":[{"id":81,"specs":"{\"1\":1,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":82,"specs":"{\"1\":2,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":83,"specs":"{\"1\":3,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":84,"specs":"{\"1\":4,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":85,"specs":"{\"1\":1,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":86,"specs":"{\"1\":2,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":87,"specs":"{\"1\":3,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":88,"specs":"{\"1\":4,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null}]}
     */

    private int code;
    private String message;
    private ProductDetail data;

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

    public ProductDetail getData() {
        return data;
    }

    public void setData(ProductDetail data) {
        this.data = data;
    }

    public static class ProductDetail {
        /**
         * id : 1
         * title : 男士印花里布丝绒睡袍
         * composition : 面料：100%棉，辅料除外
         尺寸-上衣XL XXL
         衣长 74 75
         肩宽 48 49
         胸围半围 56 58

         * content :         <!DOCTYPE html>
         <html>
         <head>
         <meta charset="utf-8">
         <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
         <title>产品详情</title>
         </head>
         <body>
         <img src="https://pro.modao.cc/uploads3/images/2967/29678726/v2_pky5oy.jpg" width="100%" />
         </body>
         </html>
         * story : 野兽派获得法国Le Petit Prince原作授权推广小王子系列产品，远伴你度过美好的每一天
         * market_price : 96000
         * promote_price : 96000
         * on_sale_time : 1549974432
         * attributes : [{"attr_id":2,"attr_name":"颜色","weight":2,"values":[{"id":1,"value":"蓝色","weight":1},{"id":2,"value":"绿色","weight":2},{"id":3,"value":"红色","weight":3},{"id":4,"value":"白色","weight":4}]},{"attr_id":1,"attr_name":"尺寸","weight":1,"values":[{"id":13,"value":"L 睡衣180/96A 睡裤180/84A","weight":8},{"id":12,"value":"M 睡衣175/92A 睡裤175/80A","weight":9}]}]
         * skus : [{"id":81,"specs":"{\"1\":1,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":82,"specs":"{\"1\":2,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":83,"specs":"{\"1\":3,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":84,"specs":"{\"1\":4,\"2\":12}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":85,"specs":"{\"1\":1,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":86,"specs":"{\"1\":2,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":87,"specs":"{\"1\":3,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null},{"id":88,"specs":"{\"1\":4,\"2\":13}","stock":10,"market_price":96000,"promote_price":96000,"created_at":null}]
         */

        private int id;
        private String title;
        private String composition;
        private String content;
        private String story;
        private int market_price;
        private int promote_price;
        private int on_sale_time;
        private List<AttributesBean> attributes;
        private List<SkusBean> skus;

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

        public String getComposition() {
            return composition;
        }

        public void setComposition(String composition) {
            this.composition = composition;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
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

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public List<SkusBean> getSkus() {
            return skus;
        }

        public void setSkus(List<SkusBean> skus) {
            this.skus = skus;
        }

        public static class AttributesBean {
            /**
             * attr_id : 2
             * attr_name : 颜色
             * weight : 2
             * values : [{"id":1,"value":"蓝色","weight":1},{"id":2,"value":"绿色","weight":2},{"id":3,"value":"红色","weight":3},{"id":4,"value":"白色","weight":4}]
             */

            private int attr_id;
            private String attr_name;
            private int weight;
            private List<ValuesBean> values;

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

            public List<ValuesBean> getValues() {
                return values;
            }

            public void setValues(List<ValuesBean> values) {
                this.values = values;
            }

            public static class ValuesBean {
                /**
                 * id : 1
                 * value : 蓝色
                 * weight : 1
                 */

                private int id;
                private String value;
                private int weight;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }
            }
        }

        public static class SkusBean {
            /**
             * id : 81
             * specs : {"1":1,"2":12}
             * stock : 10
             * market_price : 96000
             * promote_price : 96000
             * created_at : null
             */

            private int id;
            private String specs;
            private int stock;
            private int market_price;
            private int promote_price;
            private Object created_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
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

            public Object getCreated_at() {
                return created_at;
            }

            public void setCreated_at(Object created_at) {
                this.created_at = created_at;
            }
        }
    }
}
