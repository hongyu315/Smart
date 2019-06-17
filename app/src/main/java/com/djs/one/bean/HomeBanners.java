package com.djs.one.bean;

import java.util.List;

public class HomeBanners {


    private int code;
    private String message;
    private List<Data> data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public class Data {

        private int id;
        private String platform;
        private int event_type;
        private String event_value;
        private String video_url;
        private String images_url;
        private String name;
        private String bg_color;
        private int sort;
        private long add_time;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }
        public String getPlatform() {
            return platform;
        }

        public void setEvent_type(int event_type) {
            this.event_type = event_type;
        }
        public int getEvent_type() {
            return event_type;
        }

        public void setEvent_value(String event_value) {
            this.event_value = event_value;
        }
        public String getEvent_value() {
            return event_value;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }
        public String getVideo_url() {
            return video_url;
        }

        public void setImages_url(String images_url) {
            this.images_url = images_url;
        }
        public String getImages_url() {
            return images_url;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setBg_color(String bg_color) {
            this.bg_color = bg_color;
        }
        public String getBg_color() {
            return bg_color;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
        public int getSort() {
            return sort;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }
        public long getAdd_time() {
            return add_time;
        }

    }
}


