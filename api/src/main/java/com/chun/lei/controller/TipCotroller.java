package com.chun.lei.controller;

import com.chun.lei.entity.Tip;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.TipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by lcl on 2020/4/29 0029
 */
@RestController
@RequestMapping("/tip")
@Api(value = "tip",tags = "打赏")
public class TipCotroller {
    private static final Logger log = LoggerFactory.getLogger(TipCotroller.class);
    @Autowired
    private TipService tipService;

    @RequestMapping(value = "/willDeal",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="查未处理打赏", notes="查未处理打赏")
    public ApiResp dealWillTips(){
        ApiResp resp = new ApiResp();
        log.info("\n查未处理打赏-->");
        tipService.getDealTips(resp);
        return resp;
    }

    @RequestMapping(value = "/deal",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="处理打赏", notes="处理打赏")
    public ApiResp dealTip(@RequestBody Tip tip){
        ApiResp resp = new ApiResp();
        log.info("\n处理打赏-->"+tip);
        tipService.dealTip(tip,resp);
        return resp;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="发起", notes="发起")
    public ApiResp addTip(@RequestBody Tip tip){
        ApiResp resp = new ApiResp();
        log.info("\n发起打赏-->"+tip.getKeyNo());
        tipService.addTip(tip,resp);
        return resp;
    }

    @RequestMapping(value = "/mine",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="我的打赏记录", notes="我的打赏记录")
    public ApiResp myTips(@RequestParam String mToken){
        ApiResp resp = new ApiResp();
        log.info("\n我的打赏记录-->"+mToken);
        tipService.getMine(mToken,resp);
        return resp;
    }

}
