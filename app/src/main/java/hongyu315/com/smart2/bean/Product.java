package hongyu315.com.smart2.bean;

import java.io.Serializable;

public class Product implements Serializable {
    public String url;
    public String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
