package com.chun.lei.service;

import com.chun.lei.entity.Tip;
import com.chun.lei.model.ApiResp;

/**
 * @Created by lcl on 2020/4/29 0029
 */
public interface TipService {

    public void getDealTips(ApiResp resp);

    public void dealTip(Tip tip,ApiResp resp);

    public void addTip(Tip tip,ApiResp resp);

    public void getMine(String mToken,ApiResp resp);


}
