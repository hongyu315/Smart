package com.djs.one.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/4/7<p>
 * <p>更改时间：2019/4/7<p>
 * <p>版本号：1<p>
 */
public class OSSBean {

    /**
     * code : 1000
     * message : success
     * data : {"AccessKeyId":"STS.NHVScgtdNrqjbNLq9qJeM1jti","AccessKeySecret":"GEK7eVCgxfe3UChqQr2Yjzf1LhEoSGxjt1DNZ4CsY6Mw","Expiration":"2019-04-07T07:13:10Z","SecurityToken":"CAISmQN1q6Ft5B2yfSjIr4vjGNnTmbtvxbOBYGj9lTkkRuph3q/fizz2IHtJfnNrBO0fsfQ2nGhZ5/salqN9TIVCQ0HzatBrtmicCt5WJtivgde8yJBZoivMewHKea6SvqL7Z+H+U6k3E5XJQlvYlyh17KLnfDG5JTKMOoGIjpgVbLZyWRKjPxVLGPBcJAZptK1/MmDKZ86wLjnggGfbECgQvRFn209y7amjz+qW6BfVkD+fzfQO9aDwOYScZtNwJ/UPVMyujsV8cbDd9TNU9xlS/b1qsbRA/j7L3LaaGEIDxxSdL83e8NBkMBQDRMpcIaNfq+XmnvBVo/Hak5+NqyxAJuZISS/SNsbCvsLPA7GuLc1rN+S5aWjM29aeN8Ws9EFGHXsQL1FNYMFzaC0yWwAtQzfLK/P+2iiTOFbyFfTbjfFqiccqnguxx7fQeQjTGYf++D0DJ5oxY3kvMxMrxmH7escECVcXLgk9XOvIFNojNUgG+PK15RehXylh32xRuOble/TVt6YQZIPyRJta1pAHY5BLo+qiwNtH433EGoABCmuWSzOk8ZOBWMvfVo0AE1/cOAcksN/YYGj4X6aZq+2LyFl9k6YWv2NGyKlUldFJXIIQcjq5U4BhPLEBcqFH/vAuy55iqWE3bcHk6H7XObdLnOgIx0eF+5UK9vt3IHHNaGX8HB4WvpwNmHBc7bl0kEd4dS9mxfD7pyByUUghreA=","endpoint":"oss-cn-shanghai.aliyuncs.com","bucket":"tianyi41","dir":"201904/07/","baseUrl":"https://tianyi41.oss-cn-shanghai.aliyuncs.com/"}
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
         * AccessKeyId : STS.NHVScgtdNrqjbNLq9qJeM1jti
         * AccessKeySecret : GEK7eVCgxfe3UChqQr2Yjzf1LhEoSGxjt1DNZ4CsY6Mw
         * Expiration : 2019-04-07T07:13:10Z
         * SecurityToken : CAISmQN1q6Ft5B2yfSjIr4vjGNnTmbtvxbOBYGj9lTkkRuph3q/fizz2IHtJfnNrBO0fsfQ2nGhZ5/salqN9TIVCQ0HzatBrtmicCt5WJtivgde8yJBZoivMewHKea6SvqL7Z+H+U6k3E5XJQlvYlyh17KLnfDG5JTKMOoGIjpgVbLZyWRKjPxVLGPBcJAZptK1/MmDKZ86wLjnggGfbECgQvRFn209y7amjz+qW6BfVkD+fzfQO9aDwOYScZtNwJ/UPVMyujsV8cbDd9TNU9xlS/b1qsbRA/j7L3LaaGEIDxxSdL83e8NBkMBQDRMpcIaNfq+XmnvBVo/Hak5+NqyxAJuZISS/SNsbCvsLPA7GuLc1rN+S5aWjM29aeN8Ws9EFGHXsQL1FNYMFzaC0yWwAtQzfLK/P+2iiTOFbyFfTbjfFqiccqnguxx7fQeQjTGYf++D0DJ5oxY3kvMxMrxmH7escECVcXLgk9XOvIFNojNUgG+PK15RehXylh32xRuOble/TVt6YQZIPyRJta1pAHY5BLo+qiwNtH433EGoABCmuWSzOk8ZOBWMvfVo0AE1/cOAcksN/YYGj4X6aZq+2LyFl9k6YWv2NGyKlUldFJXIIQcjq5U4BhPLEBcqFH/vAuy55iqWE3bcHk6H7XObdLnOgIx0eF+5UK9vt3IHHNaGX8HB4WvpwNmHBc7bl0kEd4dS9mxfD7pyByUUghreA=
         * endpoint : oss-cn-shanghai.aliyuncs.com
         * bucket : tianyi41
         * dir : 201904/07/
         * baseUrl : https://tianyi41.oss-cn-shanghai.aliyuncs.com/
         */

        private String AccessKeyId;
        private String AccessKeySecret;
        private String Expiration;
        private String SecurityToken;
        private String endpoint;
        private String bucket;
        private String dir;
        private String baseUrl;

        public String getAccessKeyId() {
            return AccessKeyId;
        }

        public void setAccessKeyId(String AccessKeyId) {
            this.AccessKeyId = AccessKeyId;
        }

        public String getAccessKeySecret() {
            return AccessKeySecret;
        }

        public void setAccessKeySecret(String AccessKeySecret) {
            this.AccessKeySecret = AccessKeySecret;
        }

        public String getExpiration() {
            return Expiration;
        }

        public void setExpiration(String Expiration) {
            this.Expiration = Expiration;
        }

        public String getSecurityToken() {
            return SecurityToken;
        }

        public void setSecurityToken(String SecurityToken) {
            this.SecurityToken = SecurityToken;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }
}
