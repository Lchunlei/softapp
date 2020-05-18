package com.chun.lei.controller;

import com.chun.lei.model.ApiResp;
import com.chun.lei.service.ImgTextService;
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
 * @Created by lcl on 2020/5/3 0003
 */
@RestController
@RequestMapping("/imgText")
@Api(value = "imgText",tags = "图文详情")
public class ImgTextController {

    @Autowired
    private ImgTextService imgTextService;

    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="查看指定页", notes="查看指定页")
    public ApiResp getAppointPage(@RequestParam Integer resId,@RequestParam Integer sort){
        ApiResp resp = new ApiResp();
        imgTextService.getAppointPage(resId,sort,resp);
        return resp;
    }

}
