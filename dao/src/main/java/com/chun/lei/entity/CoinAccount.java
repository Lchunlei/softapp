package com.chun.lei.entity;

import java.util.Date;

/**
 * @Created by lcl on 2020/4/29 0029
 */
public class CoinAccount {
    private Integer id;
    private Integer uid;
    //1邀请奖励2打赏充值3客服赠送4订阅消费5登录奖励6签到奖励
    private Integer getType;
    private String getMsg;
    private Integer coinNum;
    private Date cTime;

    public CoinAccount() {
    }

    public CoinAccount(Integer uid, Integer getType, String getMsg, Integer coinNum) {
        this.uid = uid;
        this.getType = getType;
        this.getMsg = getMsg;
        this.coinNum = coinNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGetType() {
        return getType;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public String getGetMsg() {
        return getMsg;
    }

    public void setGetMsg(String getMsg) {
        this.getMsg = getMsg;
    }

    public Integer getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        this.coinNum = coinNum;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
