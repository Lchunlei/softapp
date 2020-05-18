package com.chun.lei.entity;

import java.util.Date;

/**
 * @Created by lcl on 2020/4/29 0029
 */
public class UserResource {
    private Integer id;
    private Integer uid;
    private Integer resId;
    private String uidRsId;
    private Date cTime;

    public UserResource() {
    }

    public UserResource(Integer uid, Integer resId, String uidRsId) {
        this.uid = uid;
        this.resId = resId;
        this.uidRsId = uidRsId;
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

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getUidRsId() {
        return uidRsId;
    }

    public void setUidRsId(String uidRsId) {
        this.uidRsId = uidRsId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
