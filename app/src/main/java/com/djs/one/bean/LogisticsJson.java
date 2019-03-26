package com.djs.one.bean;

import java.util.List;

public class LogisticsJson {


    /**
     * code : 1
     * message : Success
     * data : {"id":"1","state":"3","num":"71265042088396","time":"2018-03-11 12:55:09","com_name":"汇通","list":[{"time":"2018-03-02 08:46:40","context":"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收"},{"time":"2018-03-02 08:46:40","context":"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收"}]}
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
         * id : 1
         * state : 3
         * num : 71265042088396
         * time : 2018-03-11 12:55:09
         * com_name : 汇通
         * list : [{"time":"2018-03-02 08:46:40","context":"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收"},{"time":"2018-03-02 08:46:40","context":"秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收"}]
         */

        private String id;
        private String state;
        private String num;
        private String time;
        private String com_name;
        private List<ListBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCom_name() {
            return com_name;
        }

        public void setCom_name(String com_name) {
            this.com_name = com_name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * time : 2018-03-02 08:46:40
             * context : 秦皇岛市|秦皇岛市【秦皇岛市区五部】，八栋西霞超市 已签收
             */

            private String time;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
