package com.chun.lei.service;

import com.chun.lei.entity.UserInfo;
import com.chun.lei.model.ApiResp;

/**
 * @Created by lcl on 2020/4/24 0024
 */
public interface UserInfoService {

    public void getUser(String goodId,ApiResp resp);

    public void rapidLogin(String openId,ApiResp resp);

    public void bindUser(String mToken,String tokenOrCode,ApiResp resp);

    public void activeLogin(UserInfo userInfo, ApiResp resp);

    public void getUserInfo(String mToken,ApiResp resp);

    public void signIn(String mToken,ApiResp resp);

    public void myInvite(String mToken,ApiResp resp);



}
