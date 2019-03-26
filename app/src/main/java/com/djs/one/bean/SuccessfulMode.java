package com.djs.one.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/14<p>
 * <p>更改时间：2019/2/14<p>
 * <p>版本号：1<p>
 */
public class SuccessfulMode {

    /**
     * code : 2000
     * message : 您当前的会话已超时，请重新登录
     * data : []
     */

    private int code;
    private String message;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
