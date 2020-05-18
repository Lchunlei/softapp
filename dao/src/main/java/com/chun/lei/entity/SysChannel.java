package com.chun.lei.entity;

import java.util.Date;
import java.util.List;

/**
 * @Created by lcl on 2020/4/30 0030
 */
public class SysChannel {
    private Integer id;
    private String channelName;
    private Integer channelSort;
    private Integer lookState;
    private Date cTime;

    private List<SysResource> resources;

    public List<SysResource> getResources() {
        return resources;
    }

    public void setResources(List<SysResource> resources) {
        this.resources = resources;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getChannelSort() {
        return channelSort;
    }

    public void setChannelSort(Integer channelSort) {
        this.channelSort = channelSort;
    }

    public Integer getLookState() {
        return lookState;
    }

    public void setLookState(Integer lookState) {
        this.lookState = lookState;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
