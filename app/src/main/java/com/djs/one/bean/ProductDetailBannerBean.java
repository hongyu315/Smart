package com.com.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/18<p>
 * <p>更改时间：2019/2/18<p>
 * <p>版本号：1<p>
 */
public class ProductDetailBannerBean {

    /**
     * code : 1000
     * message : success
     * data : [{"id":2,"media_url":"https://lorempixel.com/750/750/?36175","media_type":1,"weight":1},{"id":4,"media_url":"https://lorempixel.com/750/750/?48063","media_type":1,"weight":2},{"id":5,"media_url":"https://lorempixel.com/750/750/?13624","media_type":1,"weight":3},{"id":1,"media_url":"https://lorempixel.com/750/750/?65607","media_type":1,"weight":4},{"id":3,"media_url":"https://lorempixel.com/750/750/?55608","media_type":1,"weight":5}]
     */

    private int code;
    private String message;
    private List<ProductBanner> data;

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

    public List<ProductBanner> getData() {
        return data;
    }

    public void setData(List<ProductBanner> data) {
        this.data = data;
    }

    public static class ProductBanner {
        /**
         * id : 2
         * media_url : https://lorempixel.com/750/750/?36175
         * media_type : 1
         * weight : 1
         */

        private int id;
        private String media_url;
        private int media_type;
        private int weight;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
