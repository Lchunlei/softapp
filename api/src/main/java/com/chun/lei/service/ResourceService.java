package com.chun.lei.service;

import com.chun.lei.entity.reqvo.ResSearch;
import com.chun.lei.model.ApiResp;

/**
 * @Created by lcl on 2020/4/30 0030
 */
public interface ResourceService {

    public void appIndex(ApiResp resp);

    public void appIndexTime(ApiResp resp);

    public void getResInfo(String mToken,Integer resId,ApiResp resp);

    public void buyRes(String mToken,Integer resId,ApiResp resp);

    public void searchMv(String key,ApiResp resp);

    public void searchCid(ResSearch resSearch, ApiResp resp);

    public void getMine(String mToken,Integer pageNum,ApiResp resp);

    public void delMyRes(String mToken,Integer mid,ApiResp resp);

}
