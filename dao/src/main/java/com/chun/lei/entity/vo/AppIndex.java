package com.chun.lei.entity.vo;

import com.chun.lei.entity.SysBanner;
import com.chun.lei.entity.SysChannel;

import java.util.List;

/**
 * @Created by lcl on 2020/4/30 0030
 */
public class AppIndex {
    private List<SysBanner> banners;
    private List<SysChannel> channels;

    public List<SysBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<SysBanner> banners) {
        this.banners = banners;
    }

    public List<SysChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<SysChannel> channels) {
        this.channels = channels;
    }
}
