package com.djs.one.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/14<p>
 * <p>更改时间：2019/2/14<p>
 * <p>版本号：1<p>
 */
public class OSSAuth {

    /**
     * code : 1000
     * message : success
     * data : {
     * "AccessKeyId":"STS.NKWcuEnk6PvKeL6tszysW7zkt",
     * "AccessKeySecret":"A8mWSqLino4HFfMREy9DMRmccWpnwTtSJqRkGHB66FEU",
     * "Expiration":"2019-02-14T11:14:15Z",
     * "SecurityToken":"CAISmQN1q6Ft5B2yfSjIr4jiKM/xg7QX57SgZ2qHkHMvdfx72L/Aljz2IHtJfnNrBO0fsfQ2nGhZ5/salqN9TIVCQ0HzatBrtiaKQrZHJtivgde8yJBZoivMewHKea6SvqL7Z+H+U6k3E5XJQlvYlyh17KLnfDG5JTKMOoGIjpgVbLZyWRKjPxVLGPBcJAZptK1/MmDKZ86wLjnggGfbECgQvRFn209y7amjz+qW6BfVkD+fzfQO9aDwOYScZtNwJ/UPVMyujsV8cbDd9TNU9xlS/b1qsbRA/j7L3LaaGEIDxxSdL83e8NBkMBQDRMpcIaNfq+XmnvBVo/Hak5+NqyxAJuZISS/SNsbCvsLPA7GuLc1rN+S5aWjM29aeN8Ws9EFGHXsQL1FNYMFzaC0yWwAtQzfLK/P+2iiTOFbyFfTbjfFqiccqnguxx7fQeQjTGYf++D0DJ5oxY3kvMxMrxmH7escECVcXLgk9XOvIFNojNUgG+PK15RehXylh32xRuOble/TVt6YQZIPyRJta1pAHY5BLo+qiwNtH433EGoABlfl1a9B99U5qK/hoTkm9qb3WDgnXiqaHLS7aXejC6WQjTPKXSWZVQ3xvr1Dz7LzfvxnGJEOrOaHUxAUlkNyDXK5aMVvfZaKQwVUxnhY4R/CvV1SsRk+3piTY37MTOerwIfmeHj09B3NRdzW9hJSZzUgErKZjAXB+83OMoMrfDGg=",
     * "endpoint":"oss-cn-shanghai.aliyuncs.com",
     * "bucket":"tianyi41",
     * "dir":"201902/14/",
     * "baseUrl":"https://tianyi41.oss-cn-shanghai.aliyuncs.com/"}
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
         * AccessKeyId : STS.NKWcuEnk6PvKeL6tszysW7zkt
         * AccessKeySecret : A8mWSqLino4HFfMREy9DMRmccWpnwTtSJqRkGHB66FEU
         * Expiration : 2019-02-14T11:14:15Z
         * SecurityToken : CAISmQN1q6Ft5B2yfSjIr4jiKM/xg7QX57SgZ2qHkHMvdfx72L/Aljz2IHtJfnNrBO0fsfQ2nGhZ5/salqN9TIVCQ0HzatBrtiaKQrZHJtivgde8yJBZoivMewHKea6SvqL7Z+H+U6k3E5XJQlvYlyh17KLnfDG5JTKMOoGIjpgVbLZyWRKjPxVLGPBcJAZptK1/MmDKZ86wLjnggGfbECgQvRFn209y7amjz+qW6BfVkD+fzfQO9aDwOYScZtNwJ/UPVMyujsV8cbDd9TNU9xlS/b1qsbRA/j7L3LaaGEIDxxSdL83e8NBkMBQDRMpcIaNfq+XmnvBVo/Hak5+NqyxAJuZISS/SNsbCvsLPA7GuLc1rN+S5aWjM29aeN8Ws9EFGHXsQL1FNYMFzaC0yWwAtQzfLK/P+2iiTOFbyFfTbjfFqiccqnguxx7fQeQjTGYf++D0DJ5oxY3kvMxMrxmH7escECVcXLgk9XOvIFNojNUgG+PK15RehXylh32xRuOble/TVt6YQZIPyRJta1pAHY5BLo+qiwNtH433EGoABlfl1a9B99U5qK/hoTkm9qb3WDgnXiqaHLS7aXejC6WQjTPKXSWZVQ3xvr1Dz7LzfvxnGJEOrOaHUxAUlkNyDXK5aMVvfZaKQwVUxnhY4R/CvV1SsRk+3piTY37MTOerwIfmeHj09B3NRdzW9hJSZzUgErKZjAXB+83OMoMrfDGg=
         * endpoint : oss-cn-shanghai.aliyuncs.com
         * bucket : tianyi41
         * dir : 201902/14/
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
