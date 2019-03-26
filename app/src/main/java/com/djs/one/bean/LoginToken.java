package com.djs.one.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/14<p>
 * <p>更改时间：2019/2/14<p>
 * <p>版本号：1<p>
 */
public class LoginToken {
    /**
     * code : 1000
     * message : success
     * data : {"token":"bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMDEuMTMyLjEyOC42M1wvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU1MDEzNTQ1MCwiZXhwIjoxNTUwMTM5MDUwLCJuYmYiOjE1NTAxMzU0NTAsImp0aSI6InI2MFE4RWdlQkFIUjM0NjgiLCJzdWIiOjEzLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.I_lElgFAFjEMX9Kr53V14cpf4eughAqhOJ70ZxK0znk","expires_in":3600}
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
         * token : bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMDEuMTMyLjEyOC42M1wvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU1MDEzNTQ1MCwiZXhwIjoxNTUwMTM5MDUwLCJuYmYiOjE1NTAxMzU0NTAsImp0aSI6InI2MFE4RWdlQkFIUjM0NjgiLCJzdWIiOjEzLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.I_lElgFAFjEMX9Kr53V14cpf4eughAqhOJ70ZxK0znk
         * expires_in : 3600
         */

        private String token;
        private int expires_in;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }
}
