package com.chun.lei.service.impl;

import com.chun.lei.entity.MvMovie;
import com.chun.lei.entity.UserResource;
import com.chun.lei.mapper.MvMovieMapper;
import com.chun.lei.mapper.UserResourceMapper;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.MvMovieService;
import com.chun.lei.sysconfig.MsgConstant;
import com.chun.lei.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@Service
public class MvMovieServiceImpl implements MvMovieService {

    @Autowired
    private MvMovieMapper mvMovieMapper;
    @Autowired
    private UserResourceMapper userResourceMapper;

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
            UserResource ur = userResourceMapper.getByUidRsId(uid+"_"+resId);
            if(ur==null){
                resp.respErr(MsgConstant.PARAMS_ERR);
            }else {
                MvMovie mvMovie = mvMovieMapper.getAppointMv(resId,Integer.parseInt(ss[2]));
                resp.setRespData(mvMovie);
            }
        }
    }

    @Override
    public void saveErr(String token, ApiResp resp) {
        String[] ss = token.split("!");
        Integer resId = Integer.parseInt(ss[1]);
        mvMovieMapper.saveErr(resId,Integer.parseInt(ss[2]));
    }

}
