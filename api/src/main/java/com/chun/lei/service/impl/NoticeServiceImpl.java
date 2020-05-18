package com.chun.lei.service.impl;

import com.chun.lei.mapper.NoticeMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.NoticeService;
import com.chun.lei.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created by lcl on 2020/5/13 0013
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void getNotices(String mToken, ApiResp resp) {
        Integer uid = TokenUtil.getUidByToken(mToken);
        resp.setRespData(noticeMapper.getNotices(uid));
    }

}
