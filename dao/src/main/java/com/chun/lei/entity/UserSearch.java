package com.chun.lei.entity;

import java.util.Date;

/**
 * @Created by lcl on 2020/5/3 0003
 */
public class UserSearch {
    private Integer id;
    private Integer uid;
    private String keyWord;
    private Date cTime;

    public UserSearch() {

    }

    public UserSearch(Integer uid, String keyWord) {
        this.uid = uid;
        this.keyWord = keyWord;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
