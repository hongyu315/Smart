package com.djs.one.bean;

import java.util.List;

public class MessageBean {

    private int code;
    private String message;
    private DataBean  data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        private List<Message> data;

        public List<Message> getData() {
            return data;
        }

        public void setData(List<Message> data) {
            this.data = data;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
