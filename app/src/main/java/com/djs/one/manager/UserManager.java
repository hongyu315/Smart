package com.djs.one.manager;

import android.text.TextUtils;

import com.djs.one.bean.UserProfile;

public class UserManager {

    private UserProfile mUser;

    private static class SingletonClassInstance{
        private static final UserManager instance = new UserManager();
    }

    private UserManager(){
    }

    public static UserManager getInstance(){
        return SingletonClassInstance.instance;
    }

    public Boolean isLogin(){
        if (mUser != null){
            return !TextUtils.isEmpty(mUser.getData().getMobile());
        }
        return false;
    }

    public void logout(){
        mUser = null;
    }

    public UserProfile getUser(){
        return mUser;
    }

    public void setUser(UserProfile user){
        mUser = user;
    }

}
