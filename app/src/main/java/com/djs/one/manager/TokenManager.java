package com.com.one.manager;

import com.com.one.bean.LoginToken;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/14<p>
 * <p>更改时间：2019/2/14<p>
 * <p>版本号：1<p>
 */
public class TokenManager {

    private LoginToken mLoginToken;

    private static final class TokenManagerInnerClass{
        private static final TokenManager tokenManager = new TokenManager();
    }

    private TokenManager(){
    }

    public static TokenManager getInstance(){
        return TokenManagerInnerClass.tokenManager;
    }


    public void setLoginToken(LoginToken loginToken){
        mLoginToken = loginToken;
    }

    public LoginToken getLoginToken(){
        return mLoginToken;
    }
}
