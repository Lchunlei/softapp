package com.chun.lei.sysconfig;

/**
 * @Created by lcl on 2020/4/28 0028
 */
public class WxConfig {

    public static final String WX_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=wx9626fa3bd7582cf5&secret=b20784c76f8fae621508915d86e3df7d&js_code=CODE&grant_type=authorization_code";

    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx9626fa3bd7582cf5&secret=b20784c76f8fae621508915d86e3df7d";

    public static final String WX_PUSH_URL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";


}
