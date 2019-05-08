package com.djs.one.bean;

import java.util.List;

public class LogisticsJson {


    /**
     * code : 1000
     * message : success
     * data : {"id":36,"waybill_no":"306234610302","logistic_com":"顺丰快递","name":"张三","mobile":"13721042453","area":"上海","address":"捡垃圾垃圾咯哦","logistics":[{"id":17,"status":3,"context":"[上海浦东博文营业点]已签收,感谢使用顺丰,期待再次为您服务","time":"04-14 13:33"},{"id":16,"status":5,"context":"[博文速运营业点]快件交给樊其荣，正在派送途中（联系电话：15121109790）","time":"04-14 13:10"},{"id":15,"status":0,"context":"[上海浦江中转场]快件已发车","time":"04-14 11:28"},{"id":14,"status":0,"context":"[上海浦江中转场]快件在【上海浦江集散中心】已装车,准备发往 【上海浦东博文营业点】","time":"04-14 11:03"},{"id":13,"status":0,"context":"[上海浦江中转场]快件到达 【上海浦江集散中心】","time":"04-14 08:39"},{"id":12,"status":0,"context":"[芜湖鸠江中转场]快件到达 【芜湖鸠江集散中心】","time":"04-13 22:30"},{"id":11,"status":0,"context":"[杏花村经营分部]快件已发车","time":"04-13 20:10"},{"id":10,"status":0,"context":"[杏花村经营分部]快件在【池州】已装车,准备发往 【芜湖鸠江集散中心】","time":"04-13 19:12"},{"id":9,"status":0,"context":"[杏花村经营分部]快件到达 【池州】","time":"04-13 19:12"},{"id":8,"status":0,"context":"[庙前镇十字路速运营业点]快件已发车","time":"04-13 18:19"},{"id":7,"status":0,"context":"快件在【池州市青阳县庙前镇十字路速运营业点】已装车,准备发往 【池州】","time":"04-13 17:31"},{"id":6,"status":1,"context":"[杜村乡合作点]顺丰速运 已收取快件","time":"04-13 11:44"}]}
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
         * id : 36
         * waybill_no : 306234610302
         * logistic_com : 顺丰快递
         * name : 张三
         * mobile : 13721042453
         * area : 上海
         * address : 捡垃圾垃圾咯哦
         * logistics : [{"id":17,"status":3,"context":"[上海浦东博文营业点]已签收,感谢使用顺丰,期待再次为您服务","time":"04-14 13:33"},{"id":16,"status":5,"context":"[博文速运营业点]快件交给樊其荣，正在派送途中（联系电话：15121109790）","time":"04-14 13:10"},{"id":15,"status":0,"context":"[上海浦江中转场]快件已发车","time":"04-14 11:28"},{"id":14,"status":0,"context":"[上海浦江中转场]快件在【上海浦江集散中心】已装车,准备发往 【上海浦东博文营业点】","time":"04-14 11:03"},{"id":13,"status":0,"context":"[上海浦江中转场]快件到达 【上海浦江集散中心】","time":"04-14 08:39"},{"id":12,"status":0,"context":"[芜湖鸠江中转场]快件到达 【芜湖鸠江集散中心】","time":"04-13 22:30"},{"id":11,"status":0,"context":"[杏花村经营分部]快件已发车","time":"04-13 20:10"},{"id":10,"status":0,"context":"[杏花村经营分部]快件在【池州】已装车,准备发往 【芜湖鸠江集散中心】","time":"04-13 19:12"},{"id":9,"status":0,"context":"[杏花村经营分部]快件到达 【池州】","time":"04-13 19:12"},{"id":8,"status":0,"context":"[庙前镇十字路速运营业点]快件已发车","time":"04-13 18:19"},{"id":7,"status":0,"context":"快件在【池州市青阳县庙前镇十字路速运营业点】已装车,准备发往 【池州】","time":"04-13 17:31"},{"id":6,"status":1,"context":"[杜村乡合作点]顺丰速运 已收取快件","time":"04-13 11:44"}]
         */

        private int id;
        private String waybill_no;
        private String logistic_com;
        private String name;
        private String mobile;
        private String area;
        private String address;
        private List<LogisticsBean> logistics;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWaybill_no() {
            return waybill_no;
        }

        public void setWaybill_no(String waybill_no) {
            this.waybill_no = waybill_no;
        }

        public String getLogistic_com() {
            return logistic_com;
        }

        public void setLogistic_com(String logistic_com) {
            this.logistic_com = logistic_com;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<LogisticsBean> getLogistics() {
            return logistics;
        }

        public void setLogistics(List<LogisticsBean> logistics) {
            this.logistics = logistics;
        }

        public static class LogisticsBean {
            /**
             * id : 17
             * status : 3
             * context : [上海浦东博文营业点]已签收,感谢使用顺丰,期待再次为您服务
             * time : 04-14 13:33
             */

            private int id;
            private int status;
            private String context;
            private String time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
