package com.chun.lei.util;

import com.chun.lei.utils.DateTools;
import com.chun.lei.utils.StringTool;
import com.chun.lei.utils.TokenUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created by lcl on 2020/4/28 0028
 */
public class UtilTest {

    @Test
    public void test4(){
        System.out.println(DateTools.isShowMv());

    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        System.out.println(format.parse("2020-04-29 00:00:00").getTime()-format.parse("2020-04-24 00:00:00").getTime());
        System.out.println(format.format(new Date()));
        System.out.println(new Date().getTime());
    }

    @Test
    public void test2(){
        String t = TokenUtil.getToken(5089);
        System.out.println(t);
        System.out.println(TokenUtil.getUidByToken(t));
    }

    @Test
    public void test1(){
        String s = StringTool.getInviteCode(605);
        System.out.println(s);
        System.out.println(StringTool.getIdByInviteCode(s));
    }


}
