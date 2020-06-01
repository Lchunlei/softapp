package com.chun.lei.entity;

import java.util.Date;

/**
 * @Created by lcl on 2020/4/30 0030
 */
public class SysBanner {
    private Integer id;
    private Integer useType;
    private Integer lookState;
    private Integer sortIndex;
    private String imgUrl;
    private String adUrl;
    private String adTitle;
    private Integer adRead;
    private Date cTime;

    public Integer getAdRead() {
        return adRead;
    }

    public void setAdRead(Integer adRead) {
        this.adRead = adRead;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getLookState() {
        return lookState;
    }

    public void setLookState(Integer lookState) {
        this.lookState = lookState;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
