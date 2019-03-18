package com.djs.one.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductList implements Serializable {
    public int code;
    public String msg;
    public ArrayList<Product> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Product> getData() {
        return data;
    }

    public void setData(ArrayList<Product> data) {
        this.data = data;
    }
}
