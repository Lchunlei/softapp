package com.chun.lei.utils;

import com.vdurmont.emoji.EmojiParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Created by lcl on 2020/4/28 0028
 */
public class StringTool {

    //将字符串分，转为单位是元的字符串
    public static String FenTurnYuan(String fenNum){
        String yuanNum;
        if(isBlank(fenNum)){
            return null;
        }else if(fenNum.length()==1){
            yuanNum="0.0"+fenNum;
        }else if(fenNum.length()==2){
            yuanNum="0."+fenNum;
        }else {
            yuanNum=fenNum.substring(0,fenNum.length()-2)+"."+fenNum.substring(fenNum.length()-2,fenNum.length());
        }
        return yuanNum;
    }

    //将字符串，转为单位是分的字符串
    public static String yuanTurnFen(String yuanNum){
        if(isBlank(yuanNum)){
            return null;
        }
        int index = yuanNum.indexOf(".");
        int length = yuanNum.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(yuanNum+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((yuanNum.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((yuanNum.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((yuanNum.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }

    //判断字符串是否为空
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    //过滤表情并转为字符串
    public static String filterEmoji(String source) {
        return EmojiParser.parseToAliases(source);
    }

    //获取随机字符串
    public static String getRandomNum(){
        char[] yan = new char[]{'1','2','3','4','5','6','7','8','9','0'};
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int a = new Random().nextInt(10);
            str.append(yan[a]);
        }
        return str.toString();
    }

    //获取邀请码
    public static String getInviteCode(Integer uid) {
        StringBuffer buffer = new StringBuffer();
        char[] cs ={'a','s','d','f','t','h','w','k','y','m'};
        if(uid<10000){
            //生成4位邀请码
           int g = (uid / 1) % 10;
            buffer.append(cs[g]);
           int s = (uid / 10) % 10;
            buffer.append(cs[s]);
           int b = (uid / 100) % 10;
            buffer.append(cs[b]);
           int q = (uid / 1000) % 10;
            buffer.append(cs[q]);
            return buffer.toString();
        }else {
            return null;
        }
    }

    //获取邀请码对应的ID
    public static Integer getIdByInviteCode(String inviteCode) {
        StringBuffer buffer = new StringBuffer();

        char[] ics = inviteCode.toCharArray();
        if(ics.length<5){
            for(int i=0;i<ics.length;i++){
                String s = CODE_MAP.get(ics[ics.length-1-i]);
                if(s==null){
                    return null;
                }else {
                    buffer.append(s);
                }
            }
            return Integer.parseInt(buffer.toString());
        }else {
            return 0;
        }
    }

    private static final Map<Character,String> CODE_MAP = new HashMap();

    static {
        CODE_MAP.put('a',"0");
        CODE_MAP.put('s',"1");
        CODE_MAP.put('d',"2");
        CODE_MAP.put('f',"3");
        CODE_MAP.put('t',"4");
        CODE_MAP.put('h',"5");
        CODE_MAP.put('w',"6");
        CODE_MAP.put('k',"7");
        CODE_MAP.put('y',"8");
        CODE_MAP.put('m',"9");
    }

}
