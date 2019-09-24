package com.djs.one.bean;

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
     * data : {"id":14,"title":"雅戈尔 T恤男士 2019春季青年男休闲短袖T恤(发货2天）","composition":"品牌： 雅戈尔（YOUNGOR）\n商品名称：雅戈尔YSPC520064BFA\n商品编号：100004490784\n商品毛重：500.00g\n商品产地：浙江宁波\n货号：YSPC520064BFA\n袖长：短袖\n版型：标准型\n厚度：常规基础\n风格：商务绅士\n领型：翻领\n上市时间：2019夏季","content":"<p style=\"text-align: center;\"><img title=\"1560263129161518.jpg\" alt=\"9de5f928ef3f3f736b068d189f2764c9.jpg\" src=\"/static/upload/images/goods/2019/06/11/1560263129161518.jpg\"/><\/p><p><br/><\/p>","story":"雅戈尔集团（600177）创建于1979年，经过36年的发展，逐步确立了以品牌服装为主业，涉足地产开发、金融投资领域，多元并进、专业化发展的经营格局，成为拥有员工5万余人的大型跨国集团公司，旗下的雅戈尔集团股份有限公司为上市公司。雅戈尔在全国拥有772家自营专卖店，各品牌商业网点2632家。主打产品衬衫为全国衬衫行业第一个国家出口免验产品，连续17年获得市场综合占有率第一位，西服连续12年保持市场综合占有率第一位。","thumb_url":"https://img.tianyi41.com/static/upload/images/goods/2019/01/14/1547455907486857.jpg","video_url":"https://img.tianyi41.com/static/upload/video/goods/2019/01/14/1547458876723311.mp4","market_price":"299","promote_price":"289","on_sale_time":1568684308,"is_collected":0,"attributes":[{"attr_id":1,"attr_name":"尺寸","is_color":0,"weight":1,"values":[{"id":7,"value":"M","weight":3},{"id":8,"value":"L","weight":4}]},{"attr_id":2,"attr_name":"颜色","is_color":1,"weight":2,"values":[{"id":3,"value":"红色","weight":3},{"id":4,"value":"白色","weight":4}]}],"sku":[{"attr_id":2,"attr_name":"颜色","value":"红色","color_id":3,"sku":[{"id":89,"stock":999,"delivery_cycle":"2天","size":"L","size_id":8,"market_price":299}]},{"attr_id":2,"attr_name":"颜色","value":"白色","color_id":4,"sku":[{"id":84,"stock":999,"delivery_cycle":"2天","size":"M","size_id":7,"market_price":299},{"id":85,"stock":999,"delivery_cycle":"2天","size":"L","size_id":8,"market_price":299}]}]}
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
         * id : 14
         * title : 雅戈尔 T恤男士 2019春季青年男休闲短袖T恤(发货2天）
         * composition : 品牌： 雅戈尔（YOUNGOR）
         商品名称：雅戈尔YSPC520064BFA
         商品编号：100004490784
         商品毛重：500.00g
         商品产地：浙江宁波
         货号：YSPC520064BFA
         袖长：短袖
         版型：标准型
         厚度：常规基础
         风格：商务绅士
         领型：翻领
         上市时间：2019夏季
         * content : <p style="text-align: center;"><img title="1560263129161518.jpg" alt="9de5f928ef3f3f736b068d189f2764c9.jpg" src="/static/upload/images/goods/2019/06/11/1560263129161518.jpg"/></p><p><br/></p>
         * story : 雅戈尔集团（600177）创建于1979年，经过36年的发展，逐步确立了以品牌服装为主业，涉足地产开发、金融投资领域，多元并进、专业化发展的经营格局，成为拥有员工5万余人的大型跨国集团公司，旗下的雅戈尔集团股份有限公司为上市公司。雅戈尔在全国拥有772家自营专卖店，各品牌商业网点2632家。主打产品衬衫为全国衬衫行业第一个国家出口免验产品，连续17年获得市场综合占有率第一位，西服连续12年保持市场综合占有率第一位。
         * thumb_url : https://img.tianyi41.com/static/upload/images/goods/2019/01/14/1547455907486857.jpg
         * video_url : https://img.tianyi41.com/static/upload/video/goods/2019/01/14/1547458876723311.mp4
         * market_price : 299
         * promote_price : 289
         * on_sale_time : 1568684308
         * is_collected : 0
         * attributes : [{"attr_id":1,"attr_name":"尺寸","is_color":0,"weight":1,"values":[{"id":7,"value":"M","weight":3},{"id":8,"value":"L","weight":4}]},{"attr_id":2,"attr_name":"颜色","is_color":1,"weight":2,"values":[{"id":3,"value":"红色","weight":3},{"id":4,"value":"白色","weight":4}]}]
         * sku : [{"attr_id":2,"attr_name":"颜色","value":"红色","color_id":3,"sku":[{"id":89,"stock":999,"delivery_cycle":"2天","size":"L","size_id":8,"market_price":299}]},{"attr_id":2,"attr_name":"颜色","value":"白色","color_id":4,"sku":[{"id":84,"stock":999,"delivery_cycle":"2天","size":"M","size_id":7,"market_price":299},{"id":85,"stock":999,"delivery_cycle":"2天","size":"L","size_id":8,"market_price":299}]}]
         */

        private int id;
        private String title;
        private String composition;
        private String content;
        private String story;
        private String thumb_url;
        private String video_url;
        private String market_price;
        private String promote_price;
        private int on_sale_time;
        private int is_collected;
        private List<AttributesBean> attributes;
        private List<SkuBeanX> sku;

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

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getPromote_price() {
            return promote_price;
        }

        public void setPromote_price(String promote_price) {
            this.promote_price = promote_price;
        }

        public int getOn_sale_time() {
            return on_sale_time;
        }

        public void setOn_sale_time(int on_sale_time) {
            this.on_sale_time = on_sale_time;
        }

        public int getIs_collected() {
            return is_collected;
        }

        public void setIs_collected(int is_collected) {
            this.is_collected = is_collected;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public List<SkuBeanX> getSku() {
            return sku;
        }

        public void setSku(List<SkuBeanX> sku) {
            this.sku = sku;
        }

        public static class AttributesBean {
            /**
             * attr_id : 1
             * attr_name : 尺寸
             * is_color : 0
             * weight : 1
             * values : [{"id":7,"value":"M","weight":3},{"id":8,"value":"L","weight":4}]
             */

            private int attr_id;
            private String attr_name;
            private int is_color;
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

            public int getIs_color() {
                return is_color;
            }

            public void setIs_color(int is_color) {
                this.is_color = is_color;
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
                 * id : 7
                 * value : M
                 * weight : 3
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

        public static class SkuBeanX {
            /**
             * attr_id : 2
             * attr_name : 颜色
             * value : 红色
             * color_id : 3
             * sku : [{"id":89,"stock":999,"delivery_cycle":"2天","size":"L","size_id":8,"market_price":299}]
             */

            private int attr_id;
            private String attr_name;
            private String value;
            private int color_id;
            private List<SkuBean> sku;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getColor_id() {
                return color_id;
            }

            public void setColor_id(int color_id) {
                this.color_id = color_id;
            }

            public List<SkuBean> getSku() {
                return sku;
            }

            public void setSku(List<SkuBean> sku) {
                this.sku = sku;
            }

            public static class SkuBean {
                /**
                 * id : 89
                 * stock : 999
                 * delivery_cycle : 2天
                 * size : L
                 * size_id : 8
                 * market_price : 299
                 */

                private int id;
                private int stock;
                private String delivery_cycle;
                private String size;
                private int size_id;
                private int market_price;
                private int amount;

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public String getDelivery_cycle() {
                    return delivery_cycle;
                }

                public void setDelivery_cycle(String delivery_cycle) {
                    this.delivery_cycle = delivery_cycle;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public int getSize_id() {
                    return size_id;
                }

                public void setSize_id(int size_id) {
                    this.size_id = size_id;
                }

                public int getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(int market_price) {
                    this.market_price = market_price;
                }
            }
        }
    }
}