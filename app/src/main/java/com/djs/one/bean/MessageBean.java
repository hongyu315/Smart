package com.djs.one.bean;

import java.util.List;

/**
 * {
 *     "code": 1000,
 *     "message": "success",
 *     "data": {
 *         "list": [],
 *         "pagination": {
 *             "current_page": 1,
 *             "last_page": 1,
 *             "per_page": "200",
 *             "total": 0
 *         }
 *     }
 * }
 */
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
        private List<Message> list;

        public List<Message> getList() {
            return list;
        }

        public void setList(List<Message> list) {
            this.list = list;
        }
    }

    public static class Pagination{

        private int currentPage;
        private int lastPage;
        private int perPage;
        private int total;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
