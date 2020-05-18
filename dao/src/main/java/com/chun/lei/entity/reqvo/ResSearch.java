package com.chun.lei.entity.reqvo;

/**
 * @Created by lcl on 2020/5/7 0007
 */
public class ResSearch {
    private Integer cid;
    private String key;
    private Integer pageNum;

    @Override
    public String toString() {
        return "ResSearch{" +
                "cid=" + cid +
                ", key='" + key + '\'' +
                ", pageNum=" + pageNum +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
