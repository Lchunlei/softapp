package com.chun.lei.service.impl;

import com.chun.lei.entity.CoinAccount;
import com.chun.lei.entity.UserInfo;
import com.chun.lei.mapper.CoinAccountMapper;
import com.chun.lei.mapper.GetCoinMapper;
import com.chun.lei.mapper.UserInfoMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.CoinAccountService;
import com.chun.lei.utils.StringTool;
import com.chun.lei.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by lcl on 2020/4/29 0029
 */
@Service
public class CoinAccountServiceImpl implements CoinAccountService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CoinAccountMapper coinAccountMapper;
    @Autowired
    private GetCoinMapper getCoinMapper;

    @Override
    public void sysSendCoin(String uCode, Integer num, ApiResp resp) {
        Integer uid = StringTool.getIdByInviteCode(uCode);
        int i = userInfoMapper.addCoin(uid,num);
        if(i==1){
            CoinAccount ca = new CoinAccount(uid,3,"客服赠送",num);
            coinAccountMapper.insertOne(ca);
        }else {
            resp.respErr("充值失败");
        }
    }

    @Override
    public void getMine(String mToken, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        UserInfo user = userInfoMapper.getUserById(uid);
        List<CoinAccount> cas = coinAccountMapper.getMine(uid);
        resp.setRespData(cas);
        resp.setRespMsg(user.getSeeCoinNow().toString());
    }

    @Override
    public void getPage(ApiResp resp) {
        resp.setRespData(getCoinMapper.getOne());
    }

}
