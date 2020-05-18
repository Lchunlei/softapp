package com.chun.lei.entity;

/**
 * @Created by lcl on 2020/5/10 0010
 */
public class GetCoin {
    private Integer id;
    private Integer useType;
    private String topImg;
    private String tabTitle;
    private String tabInfo;
    private String wxImg;
    private String zfbImg;

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

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(String tabInfo) {
        this.tabInfo = tabInfo;
    }

    public String getWxImg() {
        return wxImg;
    }

    public void setWxImg(String wxImg) {
        this.wxImg = wxImg;
    }

    public String getZfbImg() {
        return zfbImg;
    }

    public void setZfbImg(String zfbImg) {
        this.zfbImg = zfbImg;
    }
}
