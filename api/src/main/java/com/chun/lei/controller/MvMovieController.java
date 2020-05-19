package com.chun.lei.controller;

import com.chun.lei.model.ApiResp;
import com.chun.lei.model.HtmlReq;
import com.chun.lei.service.MvMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Created by lcl on 2020/5/3 0003
 */
@RestController
@RequestMapping("/movie")
@Api(value = "movie",tags = "电影")
public class MvMovieController {
    private static final Logger log = LoggerFactory.getLogger(MvMovieController.class);
    @Autowired
    private MvMovieService mvMovieService;

    @RequestMapping(value = "/ji",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="查看指定集", notes="查看指定集")
    public ApiResp getAppointMv(@RequestParam Integer resId, @RequestParam Integer sort){
        ApiResp resp = new ApiResp();
        log.info("电影--"+resId+"***"+sort);
        mvMovieService.getAppointMv(resId,sort,resp);
        return resp;
    }

    @RequestMapping(value = "/h5",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="h5查看视频源", notes="h5查看视频源")
    public ApiResp getH5Mv(@RequestBody HtmlReq htmlReq){
        ApiResp resp = new ApiResp();
        log.info("h5查看视频源--"+ htmlReq.getToken());
        mvMovieService.getH5Mv(htmlReq.getToken(),resp);
        return resp;
    }

    @RequestMapping(value = "/err",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="看视频异常上报", notes="看视频异常上报")
    public ApiResp saveErr(@RequestBody HtmlReq htmlReq){
        ApiResp resp = new ApiResp();
        log.info("看视频异常上报--"+ htmlReq.getToken());
        mvMovieService.saveErr(htmlReq.getToken(),resp);
        return resp;
    }
}
