package com.chun.lei.controller;

import com.chun.lei.entity.reqvo.ResSearch;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by lcl on 2020/4/30 0030
 */
@RestController
@RequestMapping("/res")
@Api(value = "res",tags = "平台资源")
public class ResourceController {
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/index",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="系统首页", notes="系统首页")
    public ApiResp appIndex(){
        ApiResp resp = new ApiResp();
        log.info("\n-----系统首页----");
        resourceService.appIndex(resp);
        return resp;
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="资源详情", notes="资源详情")
    public ApiResp getInfo(@RequestParam String mToken,@RequestParam Integer resId){
        ApiResp resp = new ApiResp();
        log.info("\n-----资源详情----"+mToken+"--"+resId);
        resourceService.getResInfo(mToken,resId,resp);
        return resp;
    }

    @RequestMapping(value = "/buy",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="订阅", notes="订阅")
    public ApiResp buyRes(@RequestParam String mToken,@RequestParam Integer resId){
        ApiResp resp = new ApiResp();
        log.info("\n-----订阅----");
        resourceService.buyRes(mToken,resId,resp);
        return resp;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="搜索", notes="搜索")
    public ApiResp searchMv(@RequestParam String key){
        ApiResp resp = new ApiResp();
        log.info("\n-----搜索----"+key);
        resourceService.searchMv(key,resp);
        return resp;
    }

    @RequestMapping(value = "/mine",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="我的", notes="我的")
    public ApiResp getMine(@RequestParam String mToken,@RequestParam Integer pageNum){
        ApiResp resp = new ApiResp();
        log.info("\n-----我的----"+mToken+"**"+pageNum);
        resourceService.getMine(mToken,pageNum,resp);
        return resp;
    }

    @RequestMapping(value = "/cid",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="类目搜索", notes="类目搜索")
    public ApiResp searchCid(@RequestBody ResSearch resSearch){
        ApiResp resp = new ApiResp();
        log.info("\n-----类目搜索----"+resSearch);
        resourceService.searchCid(resSearch,resp);
        return resp;
    }

    @RequestMapping(value = "/delMyRes",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="删除我的订阅", notes="删除我的订阅")
    public ApiResp delMyRes(@RequestParam String mToken,@RequestParam Integer mid){
        ApiResp resp = new ApiResp();
        log.info("\n-----删除我的订阅----"+mToken+"***"+mid);
        resourceService.delMyRes(mToken,mid,resp);
        return resp;
    }


}
