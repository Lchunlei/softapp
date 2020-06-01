package com.chun.lei.service.impl;

import com.chun.lei.entity.SysBanner;
import com.chun.lei.mapper.SysBannerMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.SysBannerService;
import com.chun.lei.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created by lcl on 2020/6/1 0001
 */
@Service
public class SysBannerServiceImpl implements SysBannerService {

    @Autowired
    private SysBannerMapper sysBannerMapper;

    @Override
    public void getBannerInfo(Integer id, ApiResp resp) {
        sysBannerMapper.addRead(id);
        SysBanner b = sysBannerMapper.getById(id);
        if(b==null|| StringTool.isBlank(b.getAdUrl())){
            resp.respErr("资源已过期");
        }else {
            resp.setRespData(b);
        }
    }

}
