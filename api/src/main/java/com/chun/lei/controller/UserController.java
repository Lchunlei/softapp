package com.chun.lei.controller;

import com.alibaba.fastjson.JSONObject;
import com.chun.lei.entity.UserInfo;
import com.chun.lei.model.ApiResp;
import com.chun.lei.service.UserInfoService;
import com.chun.lei.sysconfig.MsgConstant;
import com.chun.lei.utils.Reqclient;
import com.chun.lei.utils.StringTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by lcl on 2020/4/28 0028
 */
@RestController
@RequestMapping("/user")
@Api(value = "user",tags = "用户操作")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/rapidLogin",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="微信快捷登录", notes="微信快捷登录")
    public ApiResp rapidLogin(@RequestParam String code){
        ApiResp<String> resp = new ApiResp();
        log.info("\n-----微信快捷登录---->"+code);
        if(StringTool.isBlank(code)){
            resp.respErr(MsgConstant.PARAMS_NULL_ERR);
        }else {
            //获取微信的openid
            String respJson = Reqclient.getWxOpenId(code);
            if(StringTool.isBlank(respJson)){
                resp.respErr(MsgConstant.SYS_ERR);
            }else {
                // 解析相应内容（转换成json对象）
                JSONObject json = JSONObject.parseObject(respJson);
                // 用户的唯一标识（openid）
                //{"openid":"oYmsa0VexWjPUPA_k1qy5JiXHWAg","session_key":"t5EjTGmnmfJfR0YNMsjV5w==","expires_in":7200}
                String openId = json.getString("openid");
//                String openId = "oYmsa0VexWjPUPA_k1qy5JiXHWAg";
                if(StringTool.isBlank(openId)){
                    log.error("\n获取openid失败-->"+json.toJSONString());
                    resp.respErr(MsgConstant.SYS_ERR);
                }else {
                    userInfoService.rapidLogin(openId,resp);
                }
            }
        }
        log.info("\n-----微信快捷登录resp---->"+resp);
        return resp;
    }

    @RequestMapping(value = "/activeLogin",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="主动登录", notes="主动登录")
    public ApiResp activeLogin(@RequestBody UserInfo userInfo){
        ApiResp<String> resp = new ApiResp();
        log.info("\n-----主动登录---->"+userInfo.getNickName());
        userInfoService.activeLogin(userInfo,resp);
        return resp;
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="签到", notes="签到")
    public ApiResp signIn(@RequestParam String mToken){
        ApiResp<String> resp = new ApiResp();
        log.info("\n-----签到---->"+mToken);
        userInfoService.signIn(mToken,resp);
        return resp;
    }

    @RequestMapping(value = "/bindUser",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="绑定用户", notes="绑定用户")
    public ApiResp bindUser(@RequestParam String mToken,@RequestParam String tokenOrCode){
        ApiResp<String> resp = new ApiResp();
        log.info("\n-----绑定用户---->"+mToken+"**>"+tokenOrCode);
        userInfoService.bindUser(mToken,tokenOrCode,resp);
        return resp;
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="个人信息", notes="个人信息")
    public ApiResp getUserInfo(@RequestParam String mToken){
        ApiResp resp = new ApiResp();
        log.info("\n-----个人信息---->"+mToken);
        userInfoService.getUserInfo(mToken,resp);
        return resp;
    }

    @RequestMapping(value = "/invite",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value="我的邀请", notes="我的邀请")
    public ApiResp myInvite(@RequestParam String mToken){
        ApiResp resp = new ApiResp();
        log.info("\n-----我的邀请---->"+mToken);
        userInfoService.myInvite(mToken,resp);
        return resp;
    }


}
