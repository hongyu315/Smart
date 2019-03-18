package hongyu315.com.smart2.bean;

import android.text.TextUtils;

public class User {

    public String userIcon;
    public String userNick;
    public String userSex;
    public String userBirthday;


    private static class InnerUserStancetonClass{
        private static final User user = new User();
    }

    private User(){
    }

    public static final User getInstance(){
        return InnerUserStancetonClass.user;
    }

    public Boolean isLogin(){
        return !TextUtils.isEmpty(getUserNick());
    }

    public void logout(){

    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }
}
