package com.chun.lei.controller;

import com.chun.lei.model.ApiResp;
import com.chun.lei.service.CoinAccountService;
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
 * @Created by lcl on 2020/4/29 0029
 */
@RestController
@RequestMapping("/coin")
@Api(value = "coin",tags = "系统管理")
public class CoinController {
    private static final Logger log = LoggerFactory.getLogger(CoinController.class);
    @Autowired
    private CoinAccountService coinAccountService;

    @RequestMapping(value = "/sysSend",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="系统赠送", notes="系统赠送")
    public ApiResp sysSend(@RequestParam("uCode")String uCode,@RequestParam("num")Integer num,@RequestParam("pwd")String pwd){
        ApiResp resp = new ApiResp();
        log.info("\n系统赠送-->"+uCode+"**>"+num);
        if("199322yanyanzhu".equalsIgnoreCase(pwd)){
            coinAccountService.sysSendCoin(uCode,num,resp);
        }else {
            resp.respErr("密码错误");
        }
        return resp;
    }

    @RequestMapping(value = "/mine",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="我的积分", notes="我的积分")
    public ApiResp getMine(@RequestParam String mToken){
        ApiResp resp = new ApiResp();
        log.info("\n我的积分-->"+mToken);
        coinAccountService.getMine(mToken,resp);
        return resp;
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="页面信息", notes="页面信息")
    public ApiResp getPage(){
        ApiResp resp = new ApiResp();
        log.info("\n页面信息-->");
        coinAccountService.getPage(resp);
        return resp;
    }


}
