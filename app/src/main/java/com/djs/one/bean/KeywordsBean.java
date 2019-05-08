package com.djs.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/4/23<p>
 * <p>更改时间：2019/4/23<p>
 * <p>版本号：1<p>
 */
public class KeywordsBean {

    /**
     * code : 1000
     * message : success
     * data : ["连衣裙","帐篷","iphone","小米","包包"]
     */

    private int code;
    private String message;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
