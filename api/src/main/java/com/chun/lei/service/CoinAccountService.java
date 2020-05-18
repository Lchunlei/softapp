package com.chun.lei.service;

import com.chun.lei.model.ApiResp;

/**
 * @Created by lcl on 2020/4/29 0029
 */
public interface CoinAccountService {

    public void sysSendCoin(String uCode, Integer num, ApiResp resp);

    public void getMine(String mToken,ApiResp resp);

    public void getPage(ApiResp resp);


}
