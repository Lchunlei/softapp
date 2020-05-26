package com.chun.lei.service;

import com.chun.lei.model.ApiResp;

/**
 * @Created by lcl on 2020/5/3 0003
 */
public interface MvMovieService {

    public void getAppointMv(Integer resId, Integer sort, ApiResp resp);

    public void getH5Mv(String token,ApiResp resp);

    public void saveErr(String token,ApiResp resp);

    public void findErr();

}
