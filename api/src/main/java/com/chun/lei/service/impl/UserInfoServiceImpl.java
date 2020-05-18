package com.chun.lei.service.impl;

import com.chun.lei.entity.CoinAccount;
import com.chun.lei.entity.UserInfo;
import com.chun.lei.mapper.CoinAccountMapper;
import com.chun.lei.mapper.UserInfoMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.UserInfoService;
import com.chun.lei.sysconfig.MsgConstant;
import com.chun.lei.utils.DateTools;
import com.chun.lei.utils.StringTool;
import com.chun.lei.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Created by lcl on 2020/4/24 0024
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CoinAccountMapper coinAccountMapper;

    @Override
    public void getUser(String goodId, ApiResp resp) {
        System.out.println("--------"+goodId);
        List<UserInfo> userInfos =  userInfoMapper.getUsers();
        resp.setRespData(userInfos);
    }

    @Override
    public void rapidLogin(String openId, ApiResp resp) {
        UserInfo ou = userInfoMapper.getUserByOpenId(openId);
        if(ou==null){
            userInfoMapper.insertOne(openId);
            int newId = userInfoMapper.selectMaxSeq();
            //首次主动登录绑定信息，奖励豆
            int reward = 5;
            CoinAccount ca = new CoinAccount(newId,5,"首登奖励",reward);
            coinAccountMapper.insertOne(ca);
            userInfoMapper.addCoin(newId,reward);
            String mToken = TokenUtil.getToken(newId);
            resp.setRespData(mToken);
        }else {
            userInfoMapper.updateLoginTime(ou.getUid());
            String mToken = TokenUtil.getToken(ou.getUid());
            if(StringTool.isBlank(ou.getNickName())){
                resp.setRespMsg(ou.getUid().toString());
            }else {
                resp.setRespMsg(ou.getNickName());
            }
            resp.setRespData(mToken);
        }
    }

    @Override
    public void bindUser(String mToken, String tokenOrCode, ApiResp resp) {
        //邀请好友奖励3个豆
        int reward = 3;
        boolean isAdd = false;
        Integer oUid = 0;
        Integer uid = TokenUtil.getUidByToken(mToken);
        if("undefined".equals(tokenOrCode)){
            resp.respErr(MsgConstant.INVALID_OPERATION);
        }else if(tokenOrCode.length()<10){
            //邀请码绑定
            oUid = StringTool.getIdByInviteCode(tokenOrCode);
            if(oUid==null||uid==null){
                resp.respErr(MsgConstant.PARAMS_ERR);
            }else {
                isAdd = true;
            }
        }else {
            //链接token绑定
            oUid = TokenUtil.getUidByToken(tokenOrCode);
            if(oUid==null||uid==null){
                resp.respErr(MsgConstant.PARAMS_ERR);
            }else {
                isAdd = true;
            }
        }
        if(isAdd){
            UserInfo isOldUser = userInfoMapper.getUserById(uid);
            Long threeDay = new Date().getTime()-isOldUser.getcTime().getTime();

            if(isOldUser.getInviteId()!=null||threeDay>432000000L){
                resp.respErr(MsgConstant.INVALID_OPERATION);
            }else {
                CoinAccount ca = new CoinAccount(oUid,1,"邀请奖励",reward);
                coinAccountMapper.insertOne(ca);
                userInfoMapper.addCoin(oUid,reward);
                userInfoMapper.addInviteId(uid,oUid);
            }
        }
    }

    @Override
    public void activeLogin(UserInfo userInfo, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(userInfo.getmToken());
        userInfo.setUid(uid);
        userInfoMapper.updateBath(userInfo);
    }

    @Override
    public void getUserInfo(String mToken, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        UserInfo user =userInfoMapper.getUserById(uid);
        if(DateTools.getIsSortDate(new Date(),true).equals(user.getSignIn())){
            user.setSignIn("sign");
        }else {
            user.setSignIn(null);
        }
        resp.setRespData(user);
    }

    @Override
    public void signIn(String mToken, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        UserInfo user =userInfoMapper.getUserById(uid);
        String nowStr = DateTools.getIsSortDate(new Date(),true);
        if(nowStr.equals(user.getSignIn())){
            resp.respErr("您已签到");
        }else {
            int reward = 1;
            CoinAccount ca = new CoinAccount(uid,6,"签到奖励",reward);
            coinAccountMapper.insertOne(ca);
            userInfoMapper.sigIn(uid,reward,nowStr);
            resp.setRespMsg("喜提阅豆 +"+reward);
        }
    }

    @Override
    public void myInvite(String mToken, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        UserInfo user =userInfoMapper.getUserById(uid);
        String code = user.getMyCode();
        //补全邀请码
        if(StringTool.isBlank(code)){
            code = StringTool.getInviteCode(uid);
            userInfoMapper.addMyCode(code,uid);
            resp.setRespMsg(code);
        }else {
            List<UserInfo> us = userInfoMapper.getMyInviteUsers(uid);
            if(!us.isEmpty()){
                resp.setRespData(us);
            }
            resp.setRespMsg(code);
        }
    }


}
