package com.chun.lei.sql;

import com.chun.lei.entity.UserInfo;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Created by lcl on 2020/4/28 0028
 */
public class UserSql {

    public String updateSql(UserInfo userInfo) {
        return new SQL(){{
            UPDATE("mv_user");
            if(userInfo.getNickName()!=null){
                SET("nickName=#{nickName}");
            }
            if(userInfo.getHeadUrl()!=null){
                SET("headUrl=#{headUrl}");
            }
            if(userInfo.getuGender()!=null){
                SET("uGender=#{uGender}");
            }
            if(userInfo.getCountry()!=null){
                SET("country=#{country}");
            }
            if(userInfo.getProvince()!=null){
                SET("province=#{province}");
            }
            if(userInfo.getCity()!=null){
                SET("city=#{city}");
            }
            SET("uTime=NOW()");
            WHERE("uid=#{uid}");
        }}.toString();
    }

}
