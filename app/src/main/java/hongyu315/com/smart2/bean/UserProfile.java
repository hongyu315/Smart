package hongyu315.com.smart2.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/14<p>
 * <p>更改时间：2019/2/14<p>
 * <p>版本号：1<p>
 */
public class UserProfile {

    /**
     * code : 1000
     * message : success
     * data : {"id":13,"nickname":null,"mobile":"13721042453","avatar":null,"gender":0,"birthday":null,"status":1,"created_at":"2019-02-14 17:10:49"}
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
         * id : 13
         * nickname : null
         * mobile : 13721042453
         * avatar : null
         * gender : 0
         * birthday : null
         * status : 1
         * created_at : 2019-02-14 17:10:49
         */

        private int id;
        private Object nickname;
        private String mobile;
        private Object avatar;
        private int gender;
        private Object birthday;
        private int status;
        private String created_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
