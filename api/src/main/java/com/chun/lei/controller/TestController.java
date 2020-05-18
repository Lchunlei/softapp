package com.chun.lei.controller;

import com.chun.lei.model.ApiResp;
import com.chun.lei.service.UserInfoService;
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
 * @Created by lcl on 2020/4/24 0024
 */
@RestController
@RequestMapping("/test")
@Api(value = "test",tags = "测试")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/go",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="测试接口", notes="测试接口")
    public ApiResp testGo(@RequestParam("goodsId")String goodsId){
        ApiResp resp = new ApiResp();
        userInfoService.getUser(goodsId,resp);
        return resp;
    }

}
