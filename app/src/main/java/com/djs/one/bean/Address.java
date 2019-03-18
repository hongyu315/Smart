package com.com.one.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/12<p>
 * <p>更改时间：2019/1/12<p>
 * <p>版本号：1<p>
 */
public class Address implements Serializable {
    /**
     * code : 1000
     * message : success
     * data : {"list":[{"id":11,"name":"张三","mobile":"13721042453","area":"上海","address":"捡垃圾垃圾咯哦","default":1,"created_at":"2019-02-15 09:00:37"}],"pagination":{"current_page":1,"last_page":1,"per_page":20,"total":1}}
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
         * list : [{"id":11,"name":"张三","mobile":"13721042453","area":"上海","address":"捡垃圾垃圾咯哦","default":1,"created_at":"2019-02-15 09:00:37"}]
         * pagination : {"current_page":1,"last_page":1,"per_page":20,"total":1}
         */

        private PaginationBean pagination;
        private List<AddressBean> list;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public List<AddressBean> getList() {
            return list;
        }

        public void setList(List<AddressBean> list) {
            this.list = list;
        }

        public static class PaginationBean {
            /**
             * current_page : 1
             * last_page : 1
             * per_page : 20
             * total : 1
             */

            private int current_page;
            private int last_page;
            private int per_page;
            private int total;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class AddressBean implements Serializable{
            /**
             * id : 11
             * name : 张三
             * mobile : 13721042453
             * area : 上海
             * address : 捡垃圾垃圾咯哦
             * default : 1
             * created_at : 2019-02-15 09:00:37
             */

            private int id;
            private String name;
            private String mobile;
            private String area;
            private String address;
            @SerializedName("default")
            private int defaultX;
            private String created_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(int defaultX) {
                this.defaultX = defaultX;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }

//    public String userName;
//    public String userPhone;
////    public String userAddress;
//    public String userLocate;
//    public String userDetailAddress;
//
//    public String getUserLocate() {
//        return userLocate;
//    }
//
//    public void setUserLocate(String userLocate) {
//        this.userLocate = userLocate;
//    }
//
//    public String getUserDetailAddress() {
//        return userDetailAddress;
//    }
//
//    public void setUserDetailAddress(String userDetailAddress) {
//        this.userDetailAddress = userDetailAddress;
//    }
//
//    public boolean isDefault;
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserPhone() {
//        return userPhone;
//    }
//
//    public void setUserPhone(String userPhone) {
//        this.userPhone = userPhone;
//    }
//
////    public String getUserAddress() {
////        return userAddress;
////    }
////
////    public void setUserAddress(String userAddress) {
////        this.userAddress = userAddress;
////    }
//
//    public boolean isDefault() {
//        return isDefault;
//    }
//
//    public void setDefault(boolean aDefault) {
//        isDefault = aDefault;
//    }
}
