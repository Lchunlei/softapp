package com.chun.lei.service.impl;

import com.chun.lei.entity.CoinAccount;
import com.chun.lei.entity.Tip;
import com.chun.lei.mapper.CoinAccountMapper;
import com.chun.lei.mapper.TipMapper;
import com.chun.lei.mapper.UserInfoMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.TipService;
import com.chun.lei.sysconfig.MsgConstant;
import com.chun.lei.utils.StringTool;
import com.chun.lei.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Created by lcl on 2020/4/29 0029
 */
@Service
public class TipServiceImpl implements TipService {

    @Autowired
    private TipMapper tipMapper;
    @Autowired
    private CoinAccountMapper coinAccountMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void getDealTips(ApiResp resp) {
        List<Tip> tips = tipMapper.getWillDealTips();
        if(tips.size()==0){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            resp.setRespMsg(tips.size()+"");
            resp.setRespData(tips);
        }
    }

    @Override
    public void dealTip(Tip tip, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(tip.getmToken());
        Tip t = tipMapper.getTipById(tip.getId());
        if(uid==null||t==null||!t.getDealStatu().equals(0)){
            resp.respErr(MsgConstant.INVALID_OPERATION);
        }else if(tip.getDealStatu().equals(2)){
            tip.setDealId(uid);
            int i = tipMapper.updateStatu(tip);
            if(i!=1){
                resp.respErr(MsgConstant.FIND_DATA_NULL);
            }
        }else {
            tip.setDealId(uid);
            Integer num = getCoinByTip(tip.getTipNumStr());
            tip.setTipNum(Integer.parseInt(StringTool.yuanTurnFen(tip.getTipNumStr())));
            int i = tipMapper.updateStatu(tip);
            if(i==1){
                CoinAccount ca = new CoinAccount(t.getUid(),1,"打赏充值",num);
                coinAccountMapper.insertOne(ca);
                userInfoMapper.addCoin(t.getUid(),num);
            }else {
                resp.respErr(MsgConstant.INVALID_OPERATION);
            }
        }
    }

    @Override
    public void addTip(Tip tip, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(tip.getmToken());
        if(uid==null){
            resp.authErr();
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            int i = tipMapper.getUserTodayFalseTip(uid,format.format(new Date()));
            if(i>8){
                resp.respErr("休息一会，稍后再试！");
            }else {
                Tip oldT = tipMapper.getTipBykeyNo(tip.getKeyNo());
                if(oldT==null){
                    tip.setUid(uid);
                    tipMapper.insertOne(tip);
                    List<Tip> ps = tipMapper.getMyTips(uid);
                    resp.setRespData(ps);
                }else {
                    resp.respErr("请勿重复提交");
                }
            }
        }
    }

    @Override
    public void getMine(String mToken, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        if(uid==null){
            resp.authErr();
        }else {
            List<Tip> ps = tipMapper.getMyTips(uid);
            if(ps.isEmpty()){
                resp.respErr(MsgConstant.FIND_DATA_NULL);
            }else {
                resp.setRespData(ps);
            }
        }
    }


    private Integer getCoinByTip(String tipNumStr){
        Integer tipNum = Integer.parseInt(StringTool.yuanTurnFen(tipNumStr));
        if (tipNum<100){
            return 0;
        }else if(tipNum<200){
            return 10;
        }else if(tipNum<300){
            return 23;
        }else if(tipNum<400){
            return 35;
        }else if(tipNum<500){
            return 50;
        }else {
            return 100;
        }
    }

}
