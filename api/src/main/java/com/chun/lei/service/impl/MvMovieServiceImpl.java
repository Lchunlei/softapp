package com.chun.lei.service.impl;

import com.chun.lei.entity.MvMovie;
import com.chun.lei.entity.SysResource;
import com.chun.lei.entity.UserResource;
import com.chun.lei.mapper.MvMovieMapper;
import com.chun.lei.mapper.SysResourceMapper;
import com.chun.lei.mapper.UserResourceMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.MvMovieService;
import com.chun.lei.sysconfig.MsgConstant;
import com.chun.lei.utils.Reqclient;
import com.chun.lei.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Service
public class MvMovieServiceImpl implements MvMovieService {
    private static final Logger log = LoggerFactory.getLogger(MvMovieServiceImpl.class);
    @Autowired
    private MvMovieMapper mvMovieMapper;
    @Autowired
    private UserResourceMapper userResourceMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public void getAppointMv(Integer resId, Integer sort, ApiResp resp) {
        MvMovie mvMovie = mvMovieMapper.getAppointMv(resId,sort);
        if(mvMovie==null){
            resp.respErr(MsgConstant.FIND_DATA_NULL);
        }else {
            resp.setRespData(mvMovie);
        }
    }

    @Override
    public void getH5Mv(String token, ApiResp resp) {
        String[] ss = token.split("!");
        Integer uid = TokenUtil.getUidByToken(ss[0]);
        if(uid==null){
            resp.respErr(MsgConstant.OUT_TIME);
        }else {
            Integer resId = Integer.parseInt(ss[1]);
            SysResource sr = sysResourceMapper.getById(resId);
            if(sr==null){
                resp.respErr(MsgConstant.PARAMS_ERR);
            }else {
                if(sr.getBuyCoin().equals(0)){
                    //免费
                    MvMovie mvMovie = mvMovieMapper.getAppointMv(resId,Integer.parseInt(ss[2]));
                    resp.setRespData(mvMovie);
                }else {
                    //付积分
                    UserResource ur = userResourceMapper.getByUidRsId(uid+"_"+resId);
                    if(ur==null){
                        resp.respErr(MsgConstant.PARAMS_ERR);
                    }else {
                        MvMovie mvMovie = mvMovieMapper.getAppointMv(resId,Integer.parseInt(ss[2]));
                        resp.setRespData(mvMovie);
                    }
                }
            }
        }
    }

    @Override
    public void saveErr(String token, ApiResp resp) {
        String[] ss = token.split("!");
        Integer resId = Integer.parseInt(ss[1]);
        mvMovieMapper.saveErr(resId,Integer.parseInt(ss[2]));
    }

    @Override
    public void findErr() {
        List<MvMovie> mvs = mvMovieMapper.getAllMvs();
        for(MvMovie v:mvs){
            Boolean re = Reqclient.testUrl(v.getvUrl());
            if(re){
                log.info("\n正常-->"+v.getvTitle());
            }else {
                mvMovieMapper.saveErrById(v.getId());
                log.info("\n异常-->"+v.getId()+"--"+v.getvTitle());
            }
        }
    }

}
