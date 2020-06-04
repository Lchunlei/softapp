package com.chun.lei.service.impl;

import com.chun.lei.entity.*;
import com.chun.lei.entity.reqvo.ResSearch;
import com.chun.lei.entity.vo.AppIndex;
import com.chun.lei.mapper.*;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.ResourceService;
import com.chun.lei.sysconfig.MsgConstant;
import com.chun.lei.utils.DateTools;
import com.chun.lei.utils.StringTool;
import com.chun.lei.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Created by lcl on 2020/4/30 0030
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SysChannelMapper sysChannelMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private UserResourceMapper userResourceMapper;
    @Autowired
    private SysBannerMapper sysBannerMapper;
    @Autowired
    private CoinAccountMapper coinAccountMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserSearchMapper userSearchMapper;
    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public void appIndexTime(ApiResp resp) {
        String today = DateTools.getIsSortDate(new Date(),true);
        Holiday holiday = holidayMapper.getDay(today);
        List<SysChannel> channels;
        List<SysBanner> banners;
        AppIndex appIndex = new AppIndex();
        if(holiday==null){
            //工作日
            if(DateTools.isShowMv()){
                channels = sysChannelMapper.getAll();
            }else {
                channels = sysChannelMapper.getNotMv();
            }
            for(SysChannel sc:channels){
                List<SysResource> srs = sysResourceMapper.getIndexResource(sc.getId());
                sc.setResources(srs);
            }
            banners = sysBannerMapper.getAllByType(1);
        }else {
            //节假日
            channels = sysChannelMapper.getAll();
            for(SysChannel sc:channels){
                List<SysResource> srs = sysResourceMapper.getIndexResource(sc.getId());
                sc.setResources(srs);
            }
            banners = sysBannerMapper.getAllByType(1);
        }
        appIndex.setChannels(channels);
        appIndex.setBanners(banners);
        resp.setRespData(appIndex);
    }

    @Override
    public void appIndex(ApiResp resp) {
        //全部
        List<SysChannel>channels = sysChannelMapper.getAll();
        for(SysChannel sc:channels){
            List<SysResource> srs = sysResourceMapper.getIndexResource(sc.getId());
            sc.setResources(srs);
        }
        List<SysBanner> banners = sysBannerMapper.getAllByType(1);
        AppIndex appIndex = new AppIndex();
        appIndex.setChannels(channels);
        appIndex.setBanners(banners);
        resp.setRespData(appIndex);
    }

    @Override
    public void getResInfo(String mToken,Integer resId, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        SysResource sr = sysResourceMapper.getById(resId);
        if(sr==null){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else if(sr.getBuyCoin().equals(0)){
            sr.setHaveAuth(1);
            sr.setSeedPre();
            resp.setRespData(sr);
        }else {
            UserResource ur = userResourceMapper.getByUidRsId(uid+"_"+sr.getId());
            if(ur==null){
                sr.setHaveAuth(0);
            }else {
                sr.setHaveAuth(1);
                sr.setSeedPre();
            }
            resp.setRespData(sr);
        }
    }

    @Override
    @Transactional
    public void buyRes(String mToken, Integer resId, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        SysResource sr = sysResourceMapper.getById(resId);
        if(sr==null){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            UserResource ur = userResourceMapper.getByUidRsId(uid+"_"+sr.getId());
            UserInfo info = userInfoMapper.getUserById(uid);
            if(info.getSeeCoinNow()<sr.getBuyCoin()){
                resp.respErr("阅豆不足！");
            }else if(ur!=null){
                resp.respErr("重复订阅");
            }else {
                CoinAccount ca = new CoinAccount(uid,4,"订阅消费",0-sr.getBuyCoin());
                coinAccountMapper.insertOne(ca);
                ur = new UserResource(uid,resId,uid+"_"+resId);
                userResourceMapper.insertOne(ur);
                userInfoMapper.minusCoin(uid,sr.getBuyCoin());
                sr.setHaveAuth(1);
                sr.setSeedPre();
                resp.setRespData(sr);
            }
        }
    }

    @Override
    public void searchMv(String key, ApiResp resp) {
        userSearchMapper.insertOne(0,key);
        List<SysResource> srs = sysResourceMapper.searchKey(key);
        if(srs.isEmpty()){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            resp.setRespMsg(srs.size()+"");
            resp.setRespData(srs);
        }
    }

    @Override
    public void searchCid(ResSearch resSearch, ApiResp resp) {
        if(StringTool.isBlank(resSearch.getKey())){
            //全部启用分页
            resSearch.setKey(null);
        }else {
            userSearchMapper.insertOne(0,resSearch.getKey());
        }
        //搜索
        List<SysResource> srs = sysResourceMapper.searchCid(resSearch);
        if(srs.isEmpty()){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            resp.setRespMsg(srs.size()+"");
            resp.setRespData(srs);
        }
    }

    @Override
    public void getMine(String mToken,Integer pageNum, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        List<SysResource> srs = sysResourceMapper.getMinePage(uid,(pageNum-1)*10);
        if(srs.isEmpty()){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            int total = userResourceMapper.getMineTotalNum(uid);
            resp.setRespMsg(total+"");
            resp.setRespData(srs);
        }
    }

    @Override
    public void delMyRes(String mToken, Integer mid, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        int i = userResourceMapper.delOneById(mid,uid);
        if(i!=1){
            resp.respErr(MsgConstant.INVALID_OPERATION);
        }
    }


}
