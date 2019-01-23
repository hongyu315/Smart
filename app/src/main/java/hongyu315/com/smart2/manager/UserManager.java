package hongyu315.com.smart2.manager;

import hongyu315.com.smart2.bean.User;

public class UserManager {

    private static class SingletonClassInstance{
        private static final UserManager instance = new UserManager();
    }

    private UserManager(){
    }

    public static UserManager getInstance(){
        return SingletonClassInstance.instance;
    }

    public Boolean isLogin(){
        return User.getInstance().isLogin();
    }

    public void logout(){
        User.getInstance().logout();
    }
}
