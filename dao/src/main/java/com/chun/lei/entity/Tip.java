package com.chun.lei.entity;

import java.util.Date;

/**
 * @Created by lcl on 2020/4/29 0029
 */
public class Tip {
    private Integer id;
    private Integer uid;
    private Integer tipNum;
    private String keyNo;
    //0未处理1有效打赏2无效打赏
    private Integer dealStatu;
    private Integer dealId;
    private Date uTime;
    private Date cTime;

    private String mToken;
    private String tipNumStr;

    public String getTipNumStr() {
        return tipNumStr;
    }

    public void setTipNumStr(String tipNumStr) {
        this.tipNumStr = tipNumStr;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
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

    public Integer getTipNum() {
        return tipNum;
    }

    public void setTipNum(Integer tipNum) {
        this.tipNum = tipNum;
    }

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public Integer getDealStatu() {
        return dealStatu;
    }

    public void setDealStatu(Integer dealStatu) {
        this.dealStatu = dealStatu;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
