package com.chun.lei.service.impl;

import com.chun.lei.entity.ImgText;
import com.chun.lei.mapper.ImgTextMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.ImgTextService;
import com.chun.lei.sysconfig.MsgConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Service
public class ImgTextServiceImpl implements ImgTextService {

    @Autowired
    private ImgTextMapper imgTextMapper;

    @Override
    public void getAppointPage(Integer resId, Integer sort, ApiResp resp) {
        ImgText imgText = imgTextMapper.getAppointPage(resId,sort);
        if(imgText==null){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            resp.setRespData(imgText);
        }
    }

}
