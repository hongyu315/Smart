package hongyu315.com.smart2.bean;

import java.io.Serializable;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/12<p>
 * <p>更改时间：2019/1/12<p>
 * <p>版本号：1<p>
 */
public class Address implements Serializable {
    public String userName;
    public String userPhone;
//    public String userAddress;
    public String userLocate;
    public String userDetailAddress;

    public String getUserLocate() {
        return userLocate;
    }

    public void setUserLocate(String userLocate) {
        this.userLocate = userLocate;
    }

    public String getUserDetailAddress() {
        return userDetailAddress;
    }

    public void setUserDetailAddress(String userDetailAddress) {
        this.userDetailAddress = userDetailAddress;
    }

    public boolean isDefault;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

//    public String getUserAddress() {
//        return userAddress;
//    }
//
//    public void setUserAddress(String userAddress) {
//        this.userAddress = userAddress;
//    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
