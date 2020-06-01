package com.chun.lei.controller;

import com.chun.lei.model.ApiResp;
import com.chun.lei.service.SysBannerService;
import com.chun.lei.sysconfig.MsgConstant;
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
 * @Created by lcl on 2020/6/1 0001
 */
@RestController
@RequestMapping("/banner")
@Api(value = "banner",tags = "轮播图")
public class BannerController {
    private static final Logger log = LoggerFactory.getLogger(BannerController.class);
    @Autowired
    private SysBannerService sysBannerService;

    @RequestMapping(value = "/info",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="轮播图详情", notes="轮播图详情")
    public ApiResp sysSend(@RequestParam("id")String id){
        ApiResp resp = new ApiResp();
        log.info("\n轮播图详情-->"+id);
        if(id.length()>4){
            resp.respErr(MsgConstant.PARAMS_ERR);
        }else {
            Integer bid = Integer.parseInt(id);
            sysBannerService.getBannerInfo(bid,resp);
        }
        return resp;
    }

}
