package com.chun.lei.controller;

import com.chun.lei.model.ApiResp;
import com.chun.lei.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by lcl on 2020/5/13 0013
 */
@RestController
@RequestMapping("/notice")
@Api(value = "notice",tags = "公告")
public class NoticeController {
    private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="公告列表", notes="公告列表")
    public ApiResp sysSend(@RequestParam("mToken")String mToken){
        ApiResp resp = new ApiResp();
        log.info("\n公告列表-->"+mToken);
        noticeService.getNotices(mToken,resp);
        return resp;
    }


}
