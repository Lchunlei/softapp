package com.chun.lei.entity;

import java.util.Date;

/**
 * @Created by lcl on 2020/4/30 0030
 */
public class SysResource {
    private Integer id;
    private String rTitle;
    //频道ID
    private Integer rType;
    private String coverImg;
    private String author;
    private String tags;
    private Integer reward;
    private String summary;
    private Integer buyCoin;
    private Integer sortIndex;
    private Integer aiqing;
    //0禁止1允许
    private Integer lookState;
    //首页0不展示1展示
    private Integer isPop;
    private Date uTime;
    private Date cTime;

    private Integer haveAuth;
    private String seedPre;

    private static final String PRE_SEED="https://xiai.51yuxian.com/img/look.html?";
    private static final String SEE_SEED="https://xiai.51yuxian.com/img/see.html?";

    public String getSeedPre() {
        return seedPre;
    }

    public void setSeedPre() {
        if(this.aiqing.equals(0)){
            //原资源地址
            this.seedPre = this.PRE_SEED;
        }else {
            //外链地址
            this.seedPre = this.SEE_SEED;
        }
    }

    public Integer getAiqing() {
        return aiqing;
    }

    public void setAiqing(Integer aiqing) {
        this.aiqing = aiqing;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getBuyCoin() {
        return buyCoin;
    }

    public void setBuyCoin(Integer buyCoin) {
        this.buyCoin = buyCoin;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getHaveAuth() {
        return haveAuth;
    }

    public void setHaveAuth(Integer haveAuth) {
        this.haveAuth = haveAuth;
    }

    public Integer getIsPop() {
        return isPop;
    }

    public void setIsPop(Integer isPop) {
        this.isPop = isPop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getrTitle() {
        return rTitle;
    }

    public void setrTitle(String rTitle) {
        this.rTitle = rTitle;
    }

    public Integer getrType() {
        return rType;
    }

    public void setrType(Integer rType) {
        this.rType = rType;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getLookState() {
        return lookState;
    }

    public void setLookState(Integer lookState) {
        this.lookState = lookState;
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
